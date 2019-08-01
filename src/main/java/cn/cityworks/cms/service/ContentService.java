package cn.cityworks.cms.service;

import cn.cityworks.cms.domain.SysContent;

import java.util.List;
import java.util.Map;

/**
 * 内容操作-逻辑层-接口
 */
public interface ContentService {

    /** 添加内容 */
    boolean handleAddContent(Map<String, Object> params);

    /** 删除内容 */
    boolean handleDeleteContent(Map<String, Object> params);

    /** 更新内容 */
    boolean handleUpdateContent(Map<String, Object> params);

    /** 审核内容 */
    boolean handleAuditContent(Map<String, Object> params);

    /** 获取内容列表 */
    List<Map<String, Object>> handleGetContentList(Map<String, Object> params);

    /** 根据id获取内容信息 */
    SysContent handleGetContentById(Map<String, Object> params);
}
