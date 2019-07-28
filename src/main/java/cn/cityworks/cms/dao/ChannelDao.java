package cn.cityworks.cms.dao;

import cn.cityworks.cms.domain.SysChannel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChannelDao {
    /**
     * 添加频道
     */
    @Insert("insert into sys_channel values(#{channel.id}, #{channel.father_id}, #{channel.name}, #{channel.sort}, " +
            "#{channel.status}, #{channel.record_status}, #{channel.create_date}, #{channel.update_date}, #{channel.roles})")
    Integer insertChannel(@Param("channel") SysChannel channel);

    /**
     * 删除频道
     */
    @Delete("update sys_channel set status = #{status} where id = #{id}")
    Integer deleteChannel(@Param("id") String id, @Param("status") int status);

    /**
     * 获取所有未删除频道信息
     */
    @Select("select * from sys_channel where status = 0 order by father_id, sort")
    List<Map<String, Object>> getAllChannel();

    /**
     * 更新频道信息-变更father_id
     */
    @Update("update sys_channel set name = #{channel.name}, sort = #{channel.sort}, roles = #{channel.roles},  " +
            "father_id = #{channel.father_id}, update_date = #{channel.update_date} where id = #{channel.id} and status = 0")
    Integer updateChannelWithFatherId(@Param("channel") SysChannel channel);

    /**
     * 更新频道信息-不变更father_id
     */
    @Update("update sys_channel set name = #{channel.name}, sort = #{channel.sort}, roles = #{channel.roles}, " +
            "update_date = #{channel.update_date} where id = #{channel.id} and status = 0")
    Integer updateChannel(@Param("channel") SysChannel channel);
}
