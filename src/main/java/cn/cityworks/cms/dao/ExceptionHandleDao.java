package cn.cityworks.cms.dao;

import cn.cityworks.cms.domain.SysException;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExceptionHandleDao {

    @Insert("insert into sys_exception(name, type, location, text, description, status, record_status, create_date, " +
            "update_date, properties) " +
            "values(#{se.name}, #{se.type}, #{se.location}, #{se.text}, #{se.description}, #{se.status}, #{se.record_status}, " +
            "#{se.create_date}, #{se.update_date}, #{se.properties})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer addException(@Param("se") SysException se);
}
