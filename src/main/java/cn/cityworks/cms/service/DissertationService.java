package cn.cityworks.cms.service;

import cn.cityworks.cms.domain.Node;

import java.util.List;
import java.util.Map;

/**
 * 专题操作-逻辑层-接口
 */
public interface DissertationService {
    /**
     * 添加专题
     */
    boolean handleAddDissertation(Map<String, Object> params);

    /**
     * 删除专题
     */
    boolean handleDeleteDissertation(Map<String, Object> params);

    /**
     * 获取专题
     */
    List<Map<String, Object>> handleGetDissertation();

    /**
     * 更新专题
     */
    boolean handleUpdateDissertation(Map<String, Object> params);
}
