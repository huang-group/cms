package cn.cityworks.cms.dao;

import cn.cityworks.cms.domain.SysDissertation;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 专题操作-持久层
 */
@Mapper
public interface DissertationDao {

    /**
     * 查询专栏
     */
    @Select("select id from sys_section where name = '专题专栏' and status = 0")
    String hasDissertationSection();

    /**
     * 添加专题
     */
    @Insert("insert into sys_dissertation values(#{dissertation.id}, #{dissertation.name}, #{dissertation.section_id}, " +
            "#{dissertation.sort}, " +
            "#{dissertation.status}, #{dissertation.record_status}, #{dissertation.create_date}, " +
            "#{dissertation.update_date}, #{dissertation.roles}, #{dissertation.image})")
    Integer insertDissertation(@Param("dissertation") SysDissertation dissertation);

    /**
     * 删除专题
     */
    @Update("update sys_dissertation set status = #{status} where id = #{id}")
    Integer deleteDissertation(@Param("id") String id, @Param("status") int status);

    /**
     * 获取所有未删除专题信息
     */
    @Select("select * from sys_dissertation where status = 0 order by sort")
    List<Map<String, Object>> getAllDissertation();

    /**
     * 更新专题信息
     */
    @Update("update sys_dissertation set name = #{dissertation.name}, sort = #{dissertation.sort}, " +
            "roles = #{dissertation.roles}, image = #{dissertation.image}, " +
            "update_date = #{dissertation.update_date} where id = #{dissertation.id} and status = 0")
    Integer updateDissertation(@Param("dissertation") SysDissertation dissertation);
}
