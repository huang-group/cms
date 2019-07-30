package cn.cityworks.cms.dao;

import cn.cityworks.cms.domain.SysContent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContentDao {

    /**
     * 插入内容数据
     */
    @Insert("insert into sys_content(channel_id, section_id, dissertation_id, article_type_id, title, short_title, tag, " +
            "digest, auth_name, content, image, publish_date, archive_date, create_date, update_date, audit_status, " +
            "sort, status, record_status, roles) values " +
            "(#{sysContent.channel_id}, #{sysContent.section_id}, #{sysContent.dissertation_id}, #{sysContent.article_type_id}, " +
            "#{sysContent.title}, #{sysContent.short_title}, #{sysContent.tag}, #{sysContent.digest}, " +
            "#{sysContent.auth_name}, #{sysContent.content}, #{sysContent.image}, #{sysContent.publish_date}, " +
            "#{sysContent.archive_date}, #{sysContent.create_date}, #{sysContent.update_date}, #{sysContent.audit_status}, " +
            "#{sysContent.sort}, #{sysContent.status}, #{sysContent.record_status}, #{sysContent.roles})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertContent(@Param("sysContent")SysContent sysContent);





















}
