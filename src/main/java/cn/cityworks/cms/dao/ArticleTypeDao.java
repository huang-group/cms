package cn.cityworks.cms.dao;

import cn.cityworks.cms.domain.SysArticleType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleTypeDao {
    /**
     * 添加内容类型
     */
    @Insert("insert into sys_article_type values(#{articleType.id}, #{articleType.name}, #{articleType.sort}, #{articleType.status}, " +
            "#{articleType.record_status}, #{articleType.create_date}, #{articleType.update_date}, #{articleType.roles})")
    Integer insertArticleType(@Param("articleType") SysArticleType articleType);

    /**
     * 删除内容类型
     */
    @Update("update sys_article_type set status = #{status} where id = #{id}")
    Integer deleteArticleType(@Param("id") String id, @Param("status") int status);

    /**
     * 获取所有未删除内容类型信息
     */
    @Select("select * from sys_article_type where status = 0 order by sort")
    List<SysArticleType> getAllArticleType();
}
