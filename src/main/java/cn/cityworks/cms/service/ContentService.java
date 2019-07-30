package cn.cityworks.cms.service;

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
}
