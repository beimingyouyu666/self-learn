package com.yangk.selflearn.idempotent.bytoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description 接口幂等性拦截器
 * @Author yangkun
 * @Date 2020/5/12
 * @Version 1.0
 */
public class ApiIdempotentInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        ApiIdempotent methodAnnotation = method.getAnnotation(ApiIdempotent.class);
        if (methodAnnotation != null) {
            // 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
            check(request);
        }

        return true;
    }

    /****
     * 拦截器的那部分 为什么请求一进来就直接校验token并删除？
     * 那执行业务代码的时候，报了个业务异常，下一次请求应该还是原来的token 是不是直接报请勿重复提交
     *
     *  处理：
     * 客户端根据服务端响应状态码进行处理
     * 如果是普通业务异常, 比如状态码为10001, 则客户端重新获取token, 不用原来的token, 因为已经使用了, 客户端刷新token, 提交时带上新的token, 就行了
     * 如果是重复请求异常, 比如状态码为10002, 则不再请求, 并提示不能重复操作
     * @param request
     */
    private void check(HttpServletRequest request) {
        tokenService.checkToken(request);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
