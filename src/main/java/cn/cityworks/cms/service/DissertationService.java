package cn.cityworks.cms.service;

import cn.cityworks.cms.domain.SysDissertation;

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
<<<<<<< HEAD
    List<Map<String, Object>> handleGetDissertation();
=======
    List<SysDissertation> handleGetDissertation();
>>>>>>> 4584c6186f9064a85245150722eb3a17a72f000c

    /**
     * 更新专题
     */
    boolean handleUpdateDissertation(Map<String, Object> params);
}
