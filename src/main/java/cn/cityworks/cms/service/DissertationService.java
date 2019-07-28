package cn.cityworks.cms.service;

import cn.cityworks.cms.domain.SysDissertation;

import java.util.List;
import java.util.Map;

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
    List<SysDissertation> handleGetDissertation();

    /**
     * 更新专题
     */
    boolean handleUpdateDissertation(Map<String, Object> params);
}
