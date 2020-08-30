package cn.xzxy.lewy.dscross.common.exception;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message = "业务异常";
    private int status = 400;

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
