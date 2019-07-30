package cn.cityworks.cms.service;

<<<<<<< HEAD
import cn.cityworks.cms.domain.Node;
=======
import cn.cityworks.cms.domain.SysSection;
>>>>>>> 4584c6186f9064a85245150722eb3a17a72f000c

import java.util.List;
import java.util.Map;

<<<<<<< HEAD
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
=======
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
>>>>>>> 4584c6186f9064a85245150722eb3a17a72f000c
    boolean handleUpdateSection(Map<String, Object> params);
}
