package cn.cityworks.cms.service.impl;

import cn.cityworks.cms.base.Definition;
import cn.cityworks.cms.dao.DissertationDao;
import cn.cityworks.cms.domain.SysDissertation;
import cn.cityworks.cms.exception.BizException;
import cn.cityworks.cms.service.DissertationService;
import cn.cityworks.cms.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class DissertationServiceImpl implements DissertationService {
    private DissertationDao dissertationDao;

    @Autowired(required = false)
    public void setDissertationDao(DissertationDao dissertationDao) {
        this.dissertationDao = dissertationDao;
    }

    /**
     * 添加专题
     */
    @Override
    public boolean handleAddDissertation(Map<String, Object> params) {
        //获取参数
        String name = null == params.get("name") ? null : (String) params.get("name");      //专题名称
        String channel_id = null == params.get("channel_id") ? null : (String) params.get("channel_id");       //频道id
        int sort = null == params.get("sort") ? 0 : (int) params.get("sort");     //排序
        String roles = null == params.get("roles") ? null : (String) params.get("roles");        //权限

        if (null == name || "".equals(name)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "专题名称不能为空!");
        }
        if (null == channel_id || "".equals(channel_id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "频道id不能为空!");
        }

        //插入数据
        Timestamp time = new Timestamp(System.currentTimeMillis());
        SysDissertation sysDissertation = new SysDissertation();
        sysDissertation.setId(Utils.randomUUID());
        sysDissertation.setName(name);
        sysDissertation.setChannel_id(channel_id);
        sysDissertation.setSort(sort);
        sysDissertation.setStatus(Definition.DATA_STATUS_NORMAL);
        sysDissertation.setRecord_status(Definition.DATA_STATUS_NORMAL);
        sysDissertation.setCreate_date(time);
        sysDissertation.setUpdate_date(time);
        sysDissertation.setRoles(roles);

        return 1 == dissertationDao.insertDissertation(sysDissertation);
    }

    /**
     * 删除专题
     */
    @Override
    public boolean handleDeleteDissertation(Map<String, Object> params) {
        String id = null == params.get("id") ? null : (String) params.get("id");
        int status = null == params.get("status") ? 0 : (int) params.get("status");

        //判断参数是否是删除，不是则抛出异常
        if (status != Definition.DATA_STATUS_LOGIC_DELETE && status != Definition.DATA_STATUS_PHYSICS_DELETE) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请按照文档传入指定删除参数");
        }
        if (null == id || "".equals(id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要删除的频道id");
        }

        return 1 == dissertationDao.deleteDissertation(id, status);
    }

    /**
     * 获取专题
     */
    @Override
    public List<SysDissertation> handleGetDissertation() {
        List<SysDissertation> dissertation = dissertationDao.getAllDissertation();

        if (null == dissertation) {
            return new ArrayList<>();
        }

        return dissertation;
    }

    /**
     * 更新专题
     */
    @Override
    public boolean handleUpdateDissertation(Map<String, Object> params) {
        //获取参数
        String id = null == params.get("id") ? null : (String) params.get("id");
        String channel_id = null == params.get("channel_id") ? null : (String) params.get("channel_id");      //父频道id
        String name = null == params.get("name") ? null : (String) params.get("name");       //专题名称
        int sort = null == params.get("sort") ? 0 : (int) params.get("sort");      //排序
        String roles = null == params.get("roles") ? null : (String) params.get("roles");     //权限

        if (null == id || "".equals(id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要更新的专题id");
        }
        if (null == name || "".equals(name)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "专题名称不能为空!");
        }

        SysDissertation dissertation = new SysDissertation();
        dissertation.setId(id);
        dissertation.setName(name);
        dissertation.setChannel_id(channel_id);
        dissertation.setSort(sort);
        dissertation.setRoles(roles);
        dissertation.setUpdate_date(new Timestamp(System.currentTimeMillis()));

        int result;
        if (null == channel_id) {
            result = dissertationDao.updateChannel(dissertation);
        } else {
            result = dissertationDao.updateChannelWithFatherId(dissertation);
        }

        return 1 == result;
    }
}
