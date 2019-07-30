package cn.cityworks.cms.service;

import cn.cityworks.cms.domain.SysSection;

import java.util.List;
import java.util.Map;

public interface SectionService {
    /**
     * 添加专栏
     */
    boolean handleAddSection(Map<String, Object> params);

    /**
     * 删除专栏
     */
    boolean handleDeleteSection(Map<String, Object> params);

    /**
     * 获取专栏
     */
    List<SysSection> handleGetSection();

    /**
     * 更新专栏
     */
    boolean handleUpdateSection(Map<String, Object> params);
}
