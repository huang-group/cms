package cn.cityworks.cms.service.impl;

import cn.cityworks.cms.base.Definition;
import cn.cityworks.cms.dao.ChannelDao;
import cn.cityworks.cms.domain.Node;
import cn.cityworks.cms.domain.SysChannel;
import cn.cityworks.cms.exception.BizException;
import cn.cityworks.cms.service.ChannelService;
import cn.cityworks.cms.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 频道操作-逻辑层-实现类
 */
@Service
@Log4j2
public class ChannelServiceImpl implements ChannelService {
    private ChannelDao channelDao;

    @Autowired(required = false)
    public void setModuleDao(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }

    /**
     * 添加频道
     */
    @Override
    public boolean handleAddChannel(Map<String, Object> params) {
        //获取参数
        String name = null == params.get("name") ? null : (String) params.get("name");       //频道名称
        String fatherId = null == params.get("father_id") ? "0" : (String) params.get("father_id");      //父频道id
        int sort = null == params.get("sort") ? 0 : (int) params.get("sort");      //排序
        String roles = null == params.get("roles") ? null : (String) params.get("roles");     //权限

        if (null == name || "".equals(name)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "频道名称不能为空!");
        }

        //插入数据
        Timestamp time = new Timestamp(System.currentTimeMillis());
        SysChannel sysChannel = new SysChannel();
        sysChannel.setId(Utils.randomUUID());
        sysChannel.setFather_id(fatherId);
        sysChannel.setName(name);
        sysChannel.setSort(sort);
        sysChannel.setStatus(Definition.DATA_STATUS_NORMAL);
        sysChannel.setRecord_status(Definition.DATA_STATUS_NORMAL);
        sysChannel.setCreate_date(time);
        sysChannel.setUpdate_date(time);
        sysChannel.setRoles(roles);

        return 1 == channelDao.insertChannel(sysChannel);
    }

    /**
     * 删除频道
     */
    @Override
    public boolean handleDeleteChannel(Map<String, Object> params) {
        String id = null == params.get("id") ? null : (String) params.get("id");
        int status = null == params.get("status") ? 0 : (int) params.get("status");

        //判断参数是否是删除，不是则抛出异常
        if (status != Definition.DATA_STATUS_LOGIC_DELETE && status != Definition.DATA_STATUS_PHYSICS_DELETE) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请按照文档传入指定删除参数");
        }
        if (null == id || "".equals(id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要删除的频道id");
        }

        return 1 == channelDao.deleteChannel(id, status);
    }

    /**
     * 获取频道
     */
    @Override
    public List<Node> handleGetChannel() {
        List<Map<String, Object>> channel = channelDao.getAllChannel();

        if (null == channel) {
            return new ArrayList<>();
        }

        return Utils.getTree(channel, "0");
    }

    /**
     * 更新频道
     */
    @Override
    public boolean handleUpdateChannel(Map<String, Object> params) {
        //获取参数
        String id = null == params.get("id") ? null : (String) params.get("id");
        String name = null == params.get("name") ? null : (String) params.get("name");       //频道名称
        String fatherId = null == params.get("father_id") ? null : (String) params.get("father_id");      //父模块id
        int sort = null == params.get("sort") ? 0 : (int) params.get("sort");      //排序
        String roles = null == params.get("roles") ? null : (String) params.get("roles");     //权限

        if (null == id || "".equals(id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要更新的频道id");
        }
        if (null == name || "".equals(name)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "频道名称不能为空!");
        }

        SysChannel channel = new SysChannel();
        channel.setId(id);
        channel.setName(name);
        channel.setFather_id(fatherId);
        channel.setSort(sort);
        channel.setRoles(roles);
        channel.setUpdate_date(new Timestamp(System.currentTimeMillis()));

        int result;
        if (null == fatherId) {
            result = channelDao.updateChannel(channel);
        } else {
            result = channelDao.updateChannelWithFatherId(channel);
        }

        return 1 == result;
    }
}
