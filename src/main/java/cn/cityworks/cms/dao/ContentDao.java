package cn.cityworks.cms.dao;

import cn.cityworks.cms.domain.SysContent;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 内容操作-持久层
 */
@Mapper
public interface ContentDao {

    /**
     * 插入内容数据
     */
    @Insert("insert into sys_content(channel_id, section_id, dissertation_id, article_type_id, title, short_title, tag, " +
            "digest, auth_name, content, image, publish_date, archive_date, create_date, update_date, audit_status, " +
            "sort, status, record_status, roles) values " +
            "(#{sysContent.channel_id}, #{sysContent.section_id}, #{sysContent.dissertation_id}, #{sysContent.article_type_id}, "+
            "#{sysContent.title}, #{sysContent.short_title}, #{sysContent.tag}, #{sysContent.digest}, " +
            "#{sysContent.auth_name}, #{sysContent.content}, #{sysContent.image}, #{sysContent.publish_date}, " +
            "#{sysContent.archive_date}, #{sysContent.create_date}, #{sysContent.update_date}, #{sysContent.audit_status}, " +
            "#{sysContent.sort}, #{sysContent.status}, #{sysContent.record_status}, #{sysContent.roles})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertContent(@Param("sysContent") SysContent sysContent);

    /**
     * 删除内容数据
     */
    @Update("update sys_content set status = #{status} where id = #{id}")
    Integer deleteContent(@Param("id") long id, @Param("status") int status);

    /**
     * 更新内容
     */
    @Update("update sys_content set channel_id = #{sysContent.channel_id}, section_id = #{sysContent.section_id}, " +
            "dissertation_id = #{sysContent.dissertation_id}, article_type_id = #{sysContent.article_type_id}, " +
            "title = #{sysContent.title}, short_title = #{sysContent.short_title}, tag = #{sysContent.tag}, " +
            "digest = #{sysContent.digest}, auth_name = #{sysContent.auth_name}, content = #{sysContent.content}, " +
            "image = #{sysContent.image}, publish_date = #{sysContent.publish_date}, archive_date = #{sysContent.archive_date}, "+
            "update_date = #{sysContent.update_date}, audit_status = #{sysContent.audit_status}, " +
            "sort = #{sysContent.sort}, roles = #{sysContent.roles} " +
            "where id = #{sysContent.id}")
    Integer updateContent(@Param("sysContent") SysContent sysContent);

    /**
     * 审核内容数据
     */
    @Update("update sys_content set audit_status = #{audit_status} where id = #{id}")
    Integer auditContent(@Param("id") long id, @Param("audit_status") int audit_status);

    /**
     * 获取内容列表
     */
    @Select("<script>" +
            "select id, channel_id, section_id, article_type_id, title, auth_name, sort, audit_status, publish_date " +
            "from sys_content where status = 0 " +
            "and title like concat('%', #{map.title}, '%') " +
            "and auth_name like concat('%', #{map.auth}, '%') " +
            "<if test='map.channel_id != null and map.channel_id != &apos;&apos;'>" +
            "and channel_id = #{map.channel_id} " +
            "</if>" +
            "<if test='map.section_id != null and map.section_id != &apos;&apos;'>" +
            "and section_id = #{map.section_id} " +
            "</if>" +
            "<if test='map.article_type_id != null and map.article_type_id != &apos;&apos;'>" +
            "and article_type_id = #{map.article_type_id} " +
            "</if>" +
            "order by sort, publish_date, id limit #{map.rows} offset #{map.page}" +
            "</script>")
    List<Map<String, Object>> getContentList(@Param("map") Map<String, Object> map);

    /**
     * 根据id获取内容
     */
    @Select("select * from sys_content where id = #{id} and status = 0")
    SysContent getContentById(@Param("id")long id);

    /**
     * 根据id获取频道名称
     */
    @Select("select name from sys_channel where id = #{id} and status = 0")
    String getChannelNameById(@Param("id")String id);

    /**
     * 根据id获取专栏名称
     */
    @Select("select name from sys_section where id = #{id} and status = 0")
    String getSectionNameById(@Param("id")String id);

    /**
     * 根据id获取内容类型名称
     */
    @Select("select name from sys_article_type where id = #{id} and status = 0")
    String getArticleTypeNameById(@Param("id")String id);
}