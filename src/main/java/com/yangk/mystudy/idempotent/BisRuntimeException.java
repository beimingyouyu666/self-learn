package com.yangk.mystudy.idempotent;

import java.util.Locale;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/8/2
 * @Version 1.0
 */
public abstract class BisRuntimeException extends RuntimeException {
    protected static final long serialVersionUID = 746264109957384560L;
    protected String errCode;
    protected String errMsg;
    protected Object obj;
    protected Locale locale;

    public BisRuntimeException() {
    }

    public BisRuntimeException(Throwable cause) {
        super(cause);
    }

    public BisRuntimeException(String message) {
        super(message);
    }

    public BisRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public Object getObj() {
        return this.obj;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
