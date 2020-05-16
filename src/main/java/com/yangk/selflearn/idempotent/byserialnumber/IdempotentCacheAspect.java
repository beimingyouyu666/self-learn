package com.yangk.selflearn.idempotent.byserialnumber;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/8/2
 * @Version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.yangk.selflearn.idempotent.ResponseMsg;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

@Aspect
public class IdempotentCacheAspect {
    private static final Logger log = LoggerFactory.getLogger(IdempotentCacheAspect.class);
    public static final String RESULT = "result";
    @Autowired
    private JedisClusterUtil jedisClusterUtil;

    public IdempotentCacheAspect() {
    }

    @Pointcut("@annotation(com.yangk.selflearn.idempotent.byserialnumber.IdempotentCache)")
    public void idempotentCacheAnnotationPointcut() {
    }

    @Around("idempotentCacheAnnotationPointcut()")
    public Object invokeResourceWithAnnotation(ProceedingJoinPoint pjp) throws Throwable {
        Method originMethod = this.getMethod(pjp);
        IdempotentCache annotation = originMethod.getAnnotation(IdempotentCache.class);
        String[] uuidNames = annotation.uuidNames();
        String uuid = this.getUuidValue(pjp, uuidNames, annotation.useMD5());
        Class returnType = originMethod.getReturnType();
        return this.cacheUuid(pjp, uuid, annotation, returnType);
    }

    private String getUuidValue(ProceedingJoinPoint pjp, String[] uuidNames, boolean useMD5) throws IllegalAccessException, InvocationTargetException, UnsupportedEncodingException, NoSuchAlgorithmException {
        String uuid = "";
        if (uuidNames.length == 0) {
            Object[] args = pjp.getArgs();
            if (args != null && args.length > 0) {
                if (useMD5) {
                    uuid = Md5Util.encode2hex(args[0].toString());
                } else {
                    uuid = (String) args[0];
                }
            }

            return uuid;
        } else {
            Class objClazz = pjp.getArgs()[0].getClass();
            Method[] methods = objClazz.getMethods();
            String[] var8 = uuidNames;
            int var9 = uuidNames.length;

            for (int var10 = 0; var10 < var9; ++var10) {
                String uuidName = var8[var10];
                Method[] var12 = methods;
                int var13 = methods.length;

                for (int var14 = 0; var14 < var13; ++var14) {
                    Method method = var12[var14];
                    String[] split = uuidName.split(".");
                    if (("get" + uuidName).equalsIgnoreCase(method.getName())) {
                        Object obj = method.invoke(pjp.getArgs()[0]);
                        String tmp;
                        if (obj instanceof Date) {
                            tmp = String.valueOf(((Date) obj).getTime());
                        } else {
                            tmp = obj.toString();
                            if (StringUtils.isEmpty(tmp)) {
                                throw new IllegalStateException(uuidName + " cannot be null or empty");
                            }
                        }

                        uuid = uuid + tmp;
                    }
                }
            }

            return uuid;
        }
    }

    private Object cacheUuid(ProceedingJoinPoint pjp, String orderUuid, IdempotentCache annotation, Class returnType) throws Throwable {
        String threadRequestUuid = UUID.randomUUID().toString();
        String lockKey = annotation.cacheKey() + orderUuid;
        String resultKey = annotation.cacheKey() + orderUuid + "result";

        ResponseMsg var9 = ResponseMsg.buildSuccessMsg();
        try {
            try {
                String cacheResult = this.jedisClusterUtil.get(resultKey);
                if (StringUtils.isNotEmpty(cacheResult)) {
                    Object var22 = JSON.parseObject(cacheResult, returnType);
                    return var22;
                }

                boolean lockFlag = this.jedisClusterUtil.tryLock(lockKey, threadRequestUuid, annotation.blockTime(),
                        annotation.lockExpiredTime());
                if (!lockFlag) {
                    if (annotation.blockTime() == 0L) {
                        ResponseMsg var23 =
                                ResponseMsg.buildFailMsg(EnumCommomSysErrorCode.REQUEST_IS_BEING_EXECUTED.name(),
                                        "The request is being executed, please try again later!");
                        return var23;
                    }

                    throw BusinessRuntimeException.buildBusyException(EnumCommomSysErrorCode.WAIT_TIMEOUT_ERROR,
                            lockKey);
                }

//                cacheResult = this.jedisClusterUtil.get(resultKey);
                Object result;
                if (StringUtils.isEmpty(cacheResult)) {
                    result = pjp.proceed();
                    this.jedisClusterUtil.set(resultKey, JSON.toJSONString(result), annotation.cacheExpired());
                    Object var11 = result;
                    return var11;
                }

                result = JSON.parseObject(cacheResult, returnType);
                return result;
            } catch (BusinessRuntimeException var17) {
                if (ResponseMsg.class == returnType) {
                    log.error("服务调用异常\nmsg: {} \nobj: {}  ", new Object[]{var17.getErrMsg(),
                            JSON.toJSONString(var17.getObj()), var17});
                    var9 = ResponseMsg.buildFailMsg(var17);
                    return var9;
                }

                throw var17;
            } catch (SystemRuntimeException var18) {
                if (ResponseMsg.class != returnType) {
                    log.error("服务调用异常\nmsg: {} \nobj: {}  ", new Object[]{var18.getErrMsg(),
                            JSON.toJSONString(var18.getObj()), var18});
                    var9 = ResponseMsg.buildFailMsg(var18);
                    throw var18;
                }
            } catch (Exception var19) {
                if (ResponseMsg.class == returnType) {
                    log.error("缓存失败{}", var19);
                    var9 = ResponseMsg.buildUnknownFailMsg();
                    return var9;
                }

                throw var19;
            }
        } finally {
            this.jedisClusterUtil.unLock(lockKey, threadRequestUuid);
        }

        return var9;
    }

    /**
     * 获取连接点的方法签名对象(方法名称和参数列表一起构成方法签名)
     *
     * @param joinPoint
     * @return
     */
    private Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}

