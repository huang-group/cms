package cn.cityworks.cms.dao;

import cn.cityworks.cms.domain.SysDissertation;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface DissertationDao {
    /**
     * 添加专题
     */
    @Insert("insert into sys_dissertation values(#{dissertation.id}, #{dissertation.name}, #{dissertation.channel_id}, #{dissertation.sort}, " +
            "#{dissertation.status}, #{dissertation.record_status}, #{dissertation.create_date}, #{dissertation.update_date}, #{dissertation.roles})")
    Integer insertDissertation(@Param("dissertation") SysDissertation dissertation);

    /**
     * 删除专题
     */
    @Delete("update sys_dissertation set status = #{status} where id = #{id}")
    Integer deleteDissertation(String id, int status);

    /**
     * 获取所有未删除专题信息
     */
    @Select("select * from sys_dissertation where status = 0 group by id,channel_id order by channel_id, sort")
    List<SysDissertation> getAllDissertation();

    /**
     * 更新专题信息-变更channel_id
     */
    @Update("update sys_dissertation set name = #{dissertation.name}, sort = #{dissertation.sort}, roles = #{dissertation.roles},  " +
            "channel_id = #{dissertation.channel_id}, update_date = #{dissertation.update_date} where id = #{dissertation.id} and status = 0")
    Integer updateChannel(SysDissertation dissertation);

    /**
     * 更新专题信息-不变更channel_id
     */
    @Update("update sys_dissertation set name = #{dissertation.name}, sort = #{dissertation.sort}, roles = #{dissertation.roles}, " +
            "update_date = #{dissertation.update_date} where id = #{dissertation.id} and status = 0")
    Integer updateChannelWithFatherId(SysDissertation dissertation);
}
