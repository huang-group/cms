package cn.cityworks.cms.service;

import cn.cityworks.cms.domain.Node;

import java.util.List;
import java.util.Map;

/**
 * 专栏操作-逻辑层-接口
 */
public interface SectionService {

    /** 添加专栏 */
    boolean handleAddSection(Map<String, Object> params);

    /** 删除专栏 */
    boolean handleDeleteSection(Map<String, Object> params);

    /** 获取专栏 */
    List<Map<String, Object>> handleGetSection(Map<String, Object> params);

    /** 更新专栏 */
    boolean handleUpdateSection(Map<String, Object> params);
}
