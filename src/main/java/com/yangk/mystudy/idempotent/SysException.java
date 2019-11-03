package com.yangk.mystudy.idempotent;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/8/2
 * @Version 1.0
 */
public class SysException extends Exception {
    protected static final long serialVersionUID = -2193150553300043406L;
    protected String errCode;
    protected String errMsg;
    protected Object obj;

    public SysException() {
    }

    public SysException(String message, Throwable cause) {
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

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
