package cn.cityworks.cms.service;


import cn.cityworks.cms.domain.Node;

import java.util.List;
import java.util.Map;

/**
 * 频道操作-逻辑层-接口
 */
public interface ChannelService {
    /**
     * 添加频道
     */
    boolean handleAddChannel(Map<String, Object> params);

    /**
     * 删除频道
     */
    boolean handleDeleteChannel(Map<String, Object> params);

    /**
     * 获取频道
     */
    List<Node> handleGetChannel();

    /**
     * 更新频道
     */
    boolean handleUpdateChannel(Map<String, Object> parems);
}
