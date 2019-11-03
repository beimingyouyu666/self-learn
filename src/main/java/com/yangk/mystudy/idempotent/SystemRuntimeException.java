package com.yangk.mystudy.idempotent;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/8/2
 * @Version 1.0
 */
public class SystemRuntimeException extends SysRuntimeException {
    /**
     * @deprecated
     */
    @Deprecated
    public SystemRuntimeException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public SystemRuntimeException(EnumCommomSysErrorCode sysErrorCode) {
        this.errCode = sysErrorCode.getValue();
        this.errMsg = sysErrorCode.getDesc();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public SystemRuntimeException(EnumCommomSysErrorCode sysErrorCode, Object obj) {
        this.errCode = sysErrorCode.getValue();
        this.errMsg = sysErrorCode.getDesc();
        this.obj = obj;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public SystemRuntimeException(String errCode) {
        this.errCode = errCode;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public SystemRuntimeException(String errCode, String errMsg, Object obj) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.obj = obj;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public SystemRuntimeException(String errCode, String errMsg, Object obj, Throwable throwable) {
        super(errMsg, throwable);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.obj = obj;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static SystemRuntimeException buildUnknownException(Object parm) {
        return new SystemRuntimeException(EnumCommomSysErrorCode.UNKNOW_ERROR.getValue(),
                EnumCommomSysErrorCode.UNKNOW_ERROR.getDesc(), parm);
    }

    public static SystemRuntimeException buildUnknownException(Throwable throwable, Object parm) {
        return new SystemRuntimeException(EnumCommomSysErrorCode.UNKNOW_ERROR.getValue(),
                EnumCommomSysErrorCode.UNKNOW_ERROR.getDesc(), parm, throwable);
    }

    public static SystemRuntimeException buildSysException(EnumCommomSysErrorCode sysErrorCode, Throwable throwable,
                                                           Object parm) {
        return new SystemRuntimeException(sysErrorCode.getValue(), sysErrorCode.getDesc(), parm, throwable);
    }

    public static SystemRuntimeException buildSysException(EnumCommomSysErrorCode sysErrorCode, String desc,
                                                           Throwable throwable, Object parm) {
        return new SystemRuntimeException(sysErrorCode.getValue(), desc, parm, throwable);
    }

    public static SystemRuntimeException buildSysException(EnumCommomSysErrorCode sysErrorCode, Object parm) {
        return new SystemRuntimeException(sysErrorCode.getValue(), sysErrorCode.getDesc(), parm);
    }

    public static SystemRuntimeException buildSysException(String sysErrorCode, String desc, Object parm) {
        return new SystemRuntimeException(sysErrorCode, desc, parm);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static SystemRuntimeException buildSysException(String errMsg) {
        return new SystemRuntimeException(EnumCommomSysErrorCode.SERVICE_BUSY_ERROR.getValue(), errMsg);
    }
}
