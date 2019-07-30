package cn.cityworks.cms.service;

import cn.cityworks.cms.domain.SysArticleType;

import java.util.List;
import java.util.Map;

/**
 * 内容类型操作-逻辑层-接口
 */
public interface ArticleTypeService {
    /**
     * 添加内容类型
     */
    boolean handleAddArticleType(Map<String, Object> params);

    /**
     * 删除内容类型
     */
    boolean handleDeleteArticleType(Map<String, Object> params);

    /**
     * 获取内容类型
     */
    List<SysArticleType> handleGetArticleType();
}
