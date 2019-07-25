package cn.cityworks.cms.service;

import cn.cityworks.cms.model.Node;

import java.util.List;
import java.util.Map;

/**
 * 模块操作-逻辑层-接口
 */
public interface ModuleService {

    /** 添加模块 */
    boolean handleAddModule(Map<String, Object> params);

    /** 删除模块 */
    boolean handleDeleteModule(Map<String, Object> params);

    /** 获取模块 */
    List<Node> handleGetModule();

    /** 更新模块 */
    boolean handleUpdateModule(Map<String, Object> params);
}
