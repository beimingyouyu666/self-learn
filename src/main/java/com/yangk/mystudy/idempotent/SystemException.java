package com.yangk.mystudy.idempotent;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/8/2
 * @Version 1.0
 */
public class SystemException extends SysException {
    /**
     * @deprecated
     */
    @Deprecated
    public SystemException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public SystemException(EnumCommomSysErrorCode sysErrorCode) {
        this.errCode = sysErrorCode.getValue();
        this.errMsg = sysErrorCode.getDesc();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public SystemException(EnumCommomSysErrorCode sysErrorCode, Object obj) {
        this.errCode = sysErrorCode.getValue();
        this.errMsg = sysErrorCode.getDesc();
        this.obj = obj;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public SystemException(String errCode) {
        this.errCode = errCode;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public SystemException(String errCode, String errMsg, Object obj) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.obj = obj;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public SystemException(String errCode, String errMsg, Object obj, Throwable throwable) {
        super(errMsg, throwable);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.obj = obj;
    }

    public static SystemException buildUnknownException(Throwable throwable, Object parm) {
        return new SystemException(EnumCommomSysErrorCode.UNKNOW_ERROR.getValue(),
                EnumCommomSysErrorCode.UNKNOW_ERROR.getDesc(), parm, throwable);
    }

    public static SystemException buildBusyException(EnumCommomSysErrorCode sysErrorCode, Throwable throwable,
                                                     Object parm) {
        return new SystemException(sysErrorCode.getValue(), sysErrorCode.getDesc(), parm, throwable);
    }

    public static SystemException buildBusyException(EnumCommomSysErrorCode sysErrorCode, String desc,
                                                     Throwable throwable, Object parm) {
        return new SystemException(sysErrorCode.getValue(), desc, parm, throwable);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static SystemException buildBusyException(EnumCommomSysErrorCode sysErrorCode, Object parm) {
        return new SystemException(sysErrorCode.getValue(), sysErrorCode.getDesc(), parm);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static SystemException buildBusyException(String errMsg) {
        return new SystemException(EnumCommomSysErrorCode.SERVICE_BUSY_ERROR.getValue(), errMsg);
    }
}
