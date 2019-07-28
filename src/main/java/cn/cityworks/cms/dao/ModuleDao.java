package cn.cityworks.cms.dao;

import cn.cityworks.cms.domain.SysModule;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 模块操作-持久层
 */
@Mapper
public interface ModuleDao {

    /**
     * 添加模块
     */
    @Insert("insert into sys_module values (#{module.id}, #{module.father_id}, #{module.name}, #{module.sort}, " +
            "#{module.status}, #{module.record_status}, #{module.create_date}, #{module.update_date}, #{module.roles})")
    Integer insertModule(@Param("module") SysModule module);

    /**
     * 删除模块
     */
    @Update("update sys_module set status = #{status}" +
            "where id = #{id}")
    Integer deleteModule(@Param("id")String id, @Param("status")int status);

    /**
     * 获取所有未删除模块信息
     */
    @Select("select * from sys_module where status = 0 order by father_id, sort")
    List<Map<String, Object>> getAllModule();

    /**
     * 更新模块信息-变更father_id
     */
    @Update("update sys_module set name = #{module.name}, sort = #{module.sort}, roles = #{module.roles}, " +
            "father_id = #{module.father_id}, update_date = #{module.update_date} " +
            "where id = #{module.id} and status = 0")
    Integer updateModuleWithFatherId(@Param("module") SysModule module);

    /**
     * 更新模块信息-不变更father_id
     */
    @Update("update sys_module set name = #{module.name}, sort = #{module.sort}, roles = #{module.roles}, " +
            "update_date = #{module.update_date} " +
            "where id = #{module.id} and status = 0")
    Integer updateModule(@Param("module") SysModule module);
}
