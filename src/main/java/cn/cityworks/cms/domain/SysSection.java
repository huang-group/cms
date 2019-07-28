package cn.cityworks.cms.domain;

import java.sql.Timestamp;

/**
 * 数据库实体类
 * 表:sys_section
 */
public class SysSection {

    private String id;      //id
    private String name;        //专栏名称
    private String channel_id;      //所属频道
    private int sort;       //排序
    private int status;     //状态：0正常；-1逻辑删；-2物理删除
    private int record_status;      //预留状态
    private Timestamp create_date;      //创建时间
    private Timestamp update_date;      //更新时间
    private String roles;       //权限

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
