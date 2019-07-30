package cn.cityworks.cms.domain;

import java.sql.Timestamp;

/**
 * 数据库实体类
 * 表:sys_dissertation
 */
public class SysDissertation {
    private String id;      //id
    private String name;        //专题名称
    private String section_id;       //所属专栏，必须是专题专栏
    private int sort;         //排序
    private int status;     //状态：0正常；-1逻辑删；-2物理删除
    private int record_status;      //预留状态
    private Timestamp create_date;      //创建时间
    private Timestamp update_date;      //更新时间
    private String roles;       //权限
    private String image;       //专题图片

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

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
