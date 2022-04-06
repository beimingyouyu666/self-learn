package com.yangk.selflearn.idempotent.bytoken;

import com.yangk.selflearn.idempotent.ResponseMsg;
import com.yangk.selflearn.idempotent.byserialnumber.BusinessRuntimeException;
import com.yangk.selflearn.idempotent.byserialnumber.EnumCommomSysErrorCode;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Description
 * @Author yangkun
 * @Date 2020/5/12
 * @Version 1.0
 */
//@Service
public class TokenServiceImpl implements TokenService {

    private static final String PREFIX_TOKEN = "idempotent:";

    private static final String TOKEN_NAME = "token";

    @Resource
    private JedisUtil jedisUtil;

    @Override
    public ResponseMsg createToken() {
        // 生成uuid加前缀存到redis
        String uuid = UUID.randomUUID().toString();
        String token = new StringBuilder().append(PREFIX_TOKEN).append(uuid).toString();
        jedisUtil.set(token, token, 60);
        return ResponseMsg.buildSuccessMsg(token);
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        // 从request中取出token，如果request中没携带token则直接抛异常不允许请求
        // 根据token值去redis中查找数据
        // 如果不存在则校验失败，抛出异常；如果存在说明校验通过，并删除token
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {
                throw BusinessRuntimeException.buildBusyException(EnumCommomSysErrorCode.VALIDA_ERROR);
            }
        }
//        if (!jedisUtil.exists(token)) {
//            throw BusinessRuntimeException.buildBusyException(EnumCommomSysErrorCode.VALIDA_ERROR);
//        }
        // 1、代码无任何线程安全性问题, 用jmeter跑50、100、200、1000、5000并发量, 均通过测试
        //2、这里跟redis原子性无关, 并不需要保证exists和del的原子性, 关键是校验de操作成功与否
        //3、甚至exists方法都不需要, 一个del<=0就保证线程安全性, 也测试通过
        //4、其实跟数据库乐观锁是一个道理, 都是最终校验数据合法性
        Long del = jedisUtil.del(token);
        if (del <= 0) {
            throw BusinessRuntimeException.buildBusyException(EnumCommomSysErrorCode.VALIDA_ERROR);
        }
    }
}
