package cn.cityworks.cms.service;

import cn.cityworks.cms.domain.SysArticleType;

import java.util.List;
import java.util.Map;

public interface ArticleTypeService {
    /**
     * 添加文章类型
     */
    boolean handleAddArticleType(Map<String, Object> params);

    /**
     * 删除文章类型
     */
    boolean handleDeleteArticleType(Map<String, Object> params);

    /**
     * 获取文章类型
     */
    List<SysArticleType> handleGetArticleType();

    /**
     * 更新文章类型
     */
    boolean handleUpdateArticleType(Map<String, Object> params);
}
