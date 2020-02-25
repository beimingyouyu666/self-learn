package com.yangk.selflearn.frontinterceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Description 使用拦截器，对前端传入的参数进行校验
 * @Author yangkun
 * @Date 2019/12/2
 * @Version 1.0
 * @blame yangkun
 */
@Component
public class ValidaInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (StringUtils.isBlank(getParameter(request))) {
            returnJson(response, "传入信息为空，请重新请求!");
            return false;
        }

        return true;
    }

    private String getParameter(HttpServletRequest request) {
        if (request.getParameter("msg") != null) {
            return request.getParameter("msg");
        }
        return "";
    }

    private void returnJson(HttpServletResponse response, String msg) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSONObject.toJSON("校验不通过"));
        } catch (Exception e) {

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
