package cn.cityworks.cms.exception;

import cn.cityworks.cms.base.Definition;

import java.util.Map;

/**
 * 自定义业务异常类
 */
@SuppressWarnings("unused")
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int code;       //错误码
    private String name;        //异常名称
    private String description;     //错误描述
    private Object object;

    public BizException(String name, String description){
        this.name = name;
        this.code = Definition.RESPONSE_STATUS_FAIL;
        this.description = description;
    }

    public BizException(int code, String name, String description){
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public BizException(int code, String name, Object object){
        this.name = name;
        this.code = code;
        this.object = object;
    }

    public BizException( String name, String description, Object object){
        this.name = name;
        this.description = description;
        this.object = object;
    }

    public BizException(int code, String name, String description, Object object){
        this.code = code;
        this.name = name;
        this.description = description;
        this.object = object;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
