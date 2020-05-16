package com.yangk.selflearn.idempotent.bytoken;

import com.yangk.selflearn.idempotent.ResponseMsg;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 操作token
 * @Author yangkun
 * @Date 2020/5/12
 * @Version 1.0
 */
public interface TokenService {

    ResponseMsg createToken();

    void checkToken(HttpServletRequest request);
}
