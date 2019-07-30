package cn.cityworks.cms.domain;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 数据库实体类
 * 表：sys_content
 */
public class SysContent {
    private long id;        //id
    private String channel_id;      //所属频道id
    private String section_id;      //所属专栏id
    private String dissertation_id;     //所属专题id
    private String article_type_id;     //所属内容类型id
    private String title;       //标题
    private String short_title;     //短标题
    private String tag;     //标签
    private String digest;      //摘要
    private String auth_name;       //作者姓名
    private String content;     //内容
    private String image;       //图片
    private Date publish_date;     //发布日期
    private Date archive_date;     //归档日期
    private Timestamp create_date;      //创建日期
    private Timestamp update_date;      //更新日期
    private int audit_status;       //审核状态：0-未审核；1：已审核；2：已退回
    private int sort;       //排序
    private int status;     //状态：0正常；-1逻辑删；-2物理删除
    private int record_status;      //预留状态
    private String roles;       //权限

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getDissertation_id() {
        return dissertation_id;
    }

    public void setDissertation_id(String dissertation_id) {
        this.dissertation_id = dissertation_id;
    }

    public String getArticle_type_id() {
        return article_type_id;
    }

    public void setArticle_type_id(String article_type_id) {
        this.article_type_id = article_type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getAuth_name() {
        return auth_name;
    }

    public void setAuth_name(String auth_name) {
        this.auth_name = auth_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public Date getArchive_date() {
        return archive_date;
    }

    public void setArchive_date(Date archive_date) {
        this.archive_date = archive_date;
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

    public int getAudit_status() {
        return audit_status;
    }

    public void setAudit_status(int audit_status) {
        this.audit_status = audit_status;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
