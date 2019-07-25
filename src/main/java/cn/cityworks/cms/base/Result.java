package cn.cityworks.cms.base;


import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 返回结果
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    /**
     * is successful
     */
    private boolean success = true;

    /**
     * Returning code
     */
    private int code = 0;

    /**
     * Message
     */
    private String message;

    /**
     * Data
     */
    private Object data;

    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
        this.code = Definition.SUCCESS;
    }

    public Result(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public Result(boolean success, int code) {
        this.success = success;
        this.code = code;
    }

    public Result(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Result(boolean success, int code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
