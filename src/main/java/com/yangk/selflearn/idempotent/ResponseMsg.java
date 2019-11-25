package com.yangk.selflearn.idempotent;


import java.io.Serializable;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/8/2
 * @Version 1.0
 */
public class ResponseMsg<T> implements Serializable {
    public static final String RESPONSE_FLAG_SUCCESS = "success";
    public static final String RESPONSE_FLAG_FAILURE = "fail";
    public static final String VALIDA_EMPTY = "VALIDA_ERROR";
    public static final String UNKNOW_ERROR = "UNKNOW_ERROR";
    public static final String SYS_INNER_ERROR = "System inner error";
    private static final long serialVersionUID = -5596930346535993316L;
    private String flag;
    private String returnCode;
    private String message;
    private T obj;

    private ResponseMsg() {
    }

    private ResponseMsg(String flag, String returnCode, String message) {
        this.flag = flag;
        this.returnCode = returnCode;
        this.message = message;
    }

    private ResponseMsg(String flag, String returnCode, String message, T obj) {
        this.flag = flag;
        this.returnCode = returnCode;
        this.message = message;
        this.obj = obj;
    }

    public static ResponseMsg buildSuccessMsg() {
        return new ResponseMsg("success", "success", "success");
    }

    public static <T> ResponseMsg<T> buildSuccessMsg(T obj) {
        return new ResponseMsg("success", "success", "success", obj);
    }

    public static ResponseMsg buildFailMsg() {
        return new ResponseMsg("fail", "fail", "fail");
    }

    public static ResponseMsg buildFailMsg(String errorDesc) {
        return new ResponseMsg("fail", "fail", errorDesc);
    }

    public static ResponseMsg buildFailMsg(BisRuntimeException ex) {
        return new ResponseMsg("fail", ex.getErrCode(), ex.getErrMsg(), ex.getObj());
    }

    public static ResponseMsg buildFailMsg(SysRuntimeException ex) {
        return new ResponseMsg("fail", ex.getErrCode(), ex.getErrMsg(), ex.getObj());
    }

    public static ResponseMsg buildFailMsg(SystemException ex) {
        return new ResponseMsg("fail", ex.getErrCode(), ex.getErrMsg(), ex.getObj());
    }

    public static ResponseMsg buildFailMsg(String code, String msg) {
        return new ResponseMsg("fail", code, msg);
    }

    public static ResponseMsg buildValidEmptyFailMsg() {
        return new ResponseMsg("fail", "VALIDA_ERROR", "VALIDA_ERROR");
    }

    public static ResponseMsg buildValidEmptyFailMsg(String errorDesc) {
        return new ResponseMsg("fail", "VALIDA_ERROR", errorDesc);
    }

    public static <T> ResponseMsg<T> buildValidFailMsg(String errorDesc, T obj) {
        return new ResponseMsg("fail", "VALIDA_ERROR", errorDesc, obj);
    }

    public static ResponseMsg buildUnknownFailMsg() {
        return new ResponseMsg("fail", "UNKNOW_ERROR", "System inner error");
    }

    public boolean isSuccess() {
        return "success".equals(this.flag);
    }

    public String getFlag() {
        return this.flag;
    }

    public String getReturnCode() {
        return this.returnCode;
    }

    public String getMessage() {
        return this.message;
    }

    public T getObj() {
        return this.obj;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResponseMsg)) {
            return false;
        } else {
            ResponseMsg<?> other = (ResponseMsg) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59:
                {
                    Object this$flag = this.getFlag();
                    Object other$flag = other.getFlag();
                    if (this$flag == null) {
                        if (other$flag == null) {
                            break label59;
                        }
                    } else if (this$flag.equals(other$flag)) {
                        break label59;
                    }

                    return false;
                }

                Object this$returnCode = this.getReturnCode();
                Object other$returnCode = other.getReturnCode();
                if (this$returnCode == null) {
                    if (other$returnCode != null) {
                        return false;
                    }
                } else if (!this$returnCode.equals(other$returnCode)) {
                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                Object this$obj = this.getObj();
                Object other$obj = other.getObj();
                if (this$obj == null) {
                    if (other$obj != null) {
                        return false;
                    }
                } else if (!this$obj.equals(other$obj)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ResponseMsg;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $flag = this.getFlag();
        result = result * 59 + ($flag == null ? 43 : $flag.hashCode());
        Object $returnCode = this.getReturnCode();
        result = result * 59 + ($returnCode == null ? 43 : $returnCode.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $obj = this.getObj();
        result = result * 59 + ($obj == null ? 43 : $obj.hashCode());
        return result;
    }

    public String toString() {
        return "ResponseMsg(flag=" + this.getFlag() + ", returnCode=" + this.getReturnCode() + ", message=" + this.getMessage() + ", obj=" + this.getObj() + ")";
    }
}
