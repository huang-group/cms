package cn.cityworks.cms.domain;

import java.sql.Timestamp;

/**
 * 表-sys_exception
 */
public class SysException {

    private long id;        //id
    private String name;        //异常名称
    private int type;       //异常类型：1-运行异常；2-业务异常
    private String location;        //异常定位
    private String text;        //异常文本
    private String description;     //异常描述
    private int status;     //状态：0-存在；-1-逻辑删除；-2物理删除
    private int record_status;      //预留状态码
    private Timestamp create_date;      //创建时间
    private Timestamp update_date;      //更新时间
    private String properties;      //预留属性

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRecord_status() {
        return record_status;
    }

    public void setRecord_status(int record_status) {
        this.record_status = record_status;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public Timestamp getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Timestamp update_date) {
        this.update_date = update_date;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
