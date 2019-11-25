package com.yangk.selflearn.idempotent;

import java.util.Locale;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/8/2
 * @Version 1.0
 */
public class BusinessRuntimeException extends BisRuntimeException {

    public static BusinessRuntimeException buildBusyException(String exceptionCode, Locale locale) {
        return (new BusinessRuntimeException.Builder()).errCode(exceptionCode).locale(locale).build();
    }

    public static BusinessRuntimeException buildBusyException(EnumCommomSysErrorCode sysErrorCode) {
        return new BusinessRuntimeException(sysErrorCode);
    }

    public static BusinessRuntimeException buildBusyException(EnumCommomSysErrorCode sysErrorCode, String desc,
                                                              Object param) {
        return new BusinessRuntimeException(sysErrorCode.getValue(), desc, param);
    }

    public static BusinessRuntimeException buildBusyException(Object param, EnumCommomSysErrorCode sysErrorCode) {
        return new BusinessRuntimeException(sysErrorCode, param);
    }

    public static BusinessRuntimeException buildBusyException(String errMsg) {
        return new BusinessRuntimeException(EnumCommomSysErrorCode.BUSINESS_ERROR.getValue(), errMsg);
    }

    public static boolean exceptionEquals(Exception e, EnumCommomSysErrorCode errorCode) {
        return e instanceof BusinessRuntimeException && ((BusinessRuntimeException) e).errCode == errorCode.getValue();
    }

    public static BusinessRuntimeException buildBusyException(String errCode, String errMsg, Object obj) {
        return new BusinessRuntimeException(errCode, errMsg, obj);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public BusinessRuntimeException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public BusinessRuntimeException(EnumCommomSysErrorCode sysErrorCode) {
        this.errCode = sysErrorCode.getValue();
        this.errMsg = sysErrorCode.getDesc();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public BusinessRuntimeException(EnumCommomSysErrorCode sysErrorCode, Object obj) {
        this.errCode = sysErrorCode.getValue();
        this.errMsg = sysErrorCode.getDesc();
        this.obj = obj;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public BusinessRuntimeException(String errCode, String errMsg, Object obj) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.obj = obj;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public BusinessRuntimeException(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public BusinessRuntimeException(EnumCommomSysErrorCode sysErrorCode, Throwable throwable) {
        super(sysErrorCode.getDesc(), throwable);
        this.errCode = sysErrorCode.getValue();
        this.errMsg = sysErrorCode.getDesc();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public BusinessRuntimeException(String errCode, String errMsg, Throwable throwable) {
        super(errMsg, throwable);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public BusinessRuntimeException(String errCode, String errMsg, Object obj, Throwable throwable) {
        super(errMsg, throwable);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.obj = obj;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static BusinessRuntimeException buildBusyException(EnumCommomSysErrorCode sysErrorCode, String desc) {
        return new BusinessRuntimeException(sysErrorCode.getValue(), desc);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static BusinessRuntimeException buildUnknownException(Throwable throwable) {
        return new BusinessRuntimeException(EnumCommomSysErrorCode.UNKNOW_ERROR.getValue(),
                EnumCommomSysErrorCode.UNKNOW_ERROR.getDesc(), throwable);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static BusinessRuntimeException buildBusyException() {
        return new BusinessRuntimeException(EnumCommomSysErrorCode.SERVICE_BUSY_ERROR.getValue(),
                EnumCommomSysErrorCode.SERVICE_BUSY_ERROR.getDesc());
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static BusinessRuntimeException buildBusyException(EnumCommomSysErrorCode sysErrorCode,
                                                              Throwable throwable) {
        return new BusinessRuntimeException(sysErrorCode.getValue(), sysErrorCode.getDesc(), throwable);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static BusinessRuntimeException buildBusyException(String errCode, String errMsg, Throwable throwable) {
        return new BusinessRuntimeException(errCode, errMsg, throwable);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static BusinessRuntimeException buildBusyException(String errCode, String errMsg, Object obj,
                                                              Throwable throwable) {
        return new BusinessRuntimeException(errCode, errMsg, obj, throwable);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static BusinessRuntimeException buildUnknownException() {
        return new BusinessRuntimeException(EnumCommomSysErrorCode.UNKNOW_ERROR.getValue(),
                EnumCommomSysErrorCode.UNKNOW_ERROR.getDesc());
    }

    public BusinessRuntimeException() {
    }

    public BusinessRuntimeException(Throwable throwable) {
        super(throwable);
    }

    public BusinessRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public String toString() {
        return "BusinessRuntimeException()";
    }

    public static class Builder {
        private BusinessRuntimeException b;

        public Builder() {
            this.b = new BusinessRuntimeException();
        }

        public Builder(Throwable throwable) {
            this.b = new BusinessRuntimeException(throwable);
        }

        public Builder(String message, Throwable throwable) {
            this.b = new BusinessRuntimeException(message, throwable);
        }

        public BusinessRuntimeException.Builder errCode(String errCode) {
            this.b.errCode = errCode;
            return this;
        }

        public BusinessRuntimeException.Builder errMsg(String errMsg) {
            this.b.errMsg = errMsg;
            return this;
        }

        public BusinessRuntimeException.Builder obj(Object obj) {
            this.b.obj = obj;
            return this;
        }

        public BusinessRuntimeException.Builder locale(Locale locale) {
            this.b.locale = locale;
            return this;
        }

        public BusinessRuntimeException build() {
            return this.b;
        }

        public BusinessRuntimeException getB() {
            return this.b;
        }

        public void setB(BusinessRuntimeException b) {
            this.b = b;
        }
    }
}
