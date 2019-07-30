package cn.cityworks.cms.dao;

import cn.cityworks.cms.domain.SysSection;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SectionDao {
    /**
     * 添加专栏
     */
    @Insert("insert into sys_section values(#{section.id}, #{section.name}, #{section.channel_id}, #{section.sort}, " +
            "#{section.status}, #{section.record_status}, #{section.create_date}, #{section.update_date}, #{section.roles})")
    Integer insertSection(@Param("section") SysSection section);

    /**
     * 删除专栏
     */
    @Update("update sys_section set status = #{status} where id = #{id}")
    Integer deleteSection(@Param("id") String id, @Param("status") int status);

    /**
     * 获取所有未删除专栏信息
     */
    @Select("select * from sys_section where status = 0 group by id,channel_id order by channel_id, sort")
    List<SysSection> getAllSection();

    /**
     * 更新专栏信息-变更channel_id
     */
    @Update("update sys_section set name = #{section.name}, sort = #{section.sort}, roles = #{section.roles},  " +
            "channel_id = #{section.channel_id}, update_date = #{section.update_date} where id = #{section.id} and status = 0")
    Integer updateSectionWithChannelId(@Param("section") SysSection section);

    /**
     * 更新专栏信息-不变更channel_id
     */
    @Update("update sys_section set name = #{section.name}, sort = #{section.sort}, roles = #{section.roles},  " +
            "update_date = #{section.update_date} where id = #{section.id} and status = 0")
    Integer updateSection(@Param("section") SysSection section);
}

