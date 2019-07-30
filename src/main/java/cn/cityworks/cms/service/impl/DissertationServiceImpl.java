package cn.cityworks.cms.service.impl;

import cn.cityworks.cms.base.Definition;
import cn.cityworks.cms.dao.ChannelDao;
import cn.cityworks.cms.dao.DissertationDao;
import cn.cityworks.cms.dao.SectionDao;
import cn.cityworks.cms.domain.Node;
import cn.cityworks.cms.domain.SysDissertation;
import cn.cityworks.cms.domain.SysSection;
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

/**
 * 专题操作-逻辑层-实现类
 */
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
        String name = (String) params.get("name");      //专题名称
        int sort = null == params.get("sort") ? 0 : (int) params.get("sort");     //排序
        String roles = (String) params.get("roles");        //权限
        String image = (String) params.get("image");        //专题图片


        if (Utils.isEmpty(name)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "专题名称不能为空!");
        }

        String section_id = dissertationDao.hasDissertationSection();
        if(Utils.isEmpty(section_id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "业务异常",
                    "暂无专题专栏，需要将专题存放至专题专栏下。请建立专题专栏!");
        }

        //插入数据
        Timestamp time = new Timestamp(System.currentTimeMillis());
        SysDissertation sysDissertation = new SysDissertation();
        sysDissertation.setId(Utils.randomUUID());
        sysDissertation.setName(name);
        sysDissertation.setSection_id(section_id);
        sysDissertation.setSort(sort);
        sysDissertation.setStatus(Definition.DATA_STATUS_NORMAL);
        sysDissertation.setRecord_status(Definition.DATA_STATUS_NORMAL);
        sysDissertation.setCreate_date(time);
        sysDissertation.setUpdate_date(time);
        sysDissertation.setRoles(roles);
        sysDissertation.setImage(image);

        return 1 == dissertationDao.insertDissertation(sysDissertation);
    }

    /**
     * 删除专题
     */
    @Override
    public boolean handleDeleteDissertation(Map<String, Object> params) {
        String id = (String) params.get("id");
        int status = null == params.get("status") ? 0 : (int) params.get("status");

        //判断参数是否是删除，不是则抛出异常
        if (status != Definition.DATA_STATUS_LOGIC_DELETE && status != Definition.DATA_STATUS_PHYSICS_DELETE) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请按照文档传入指定删除参数");
        }
        if (Utils.isEmpty(id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要删除的频道id");
        }

        return 1 == dissertationDao.deleteDissertation(id, status);
    }

    /**
     * 获取专题
     */
    @Override
    public List<Map<String, Object>> handleGetDissertation() {
        return dissertationDao.getAllDissertation();
    }

    /**
     * 更新专题
     */
    @Override
    public boolean handleUpdateDissertation(Map<String, Object> params) {
        //获取参数
        String id = (String) params.get("id");
        String name = (String) params.get("name");       //专题名称
        int sort = null == params.get("sort") ? 0 : (int) params.get("sort");      //排序
        String roles = (String) params.get("roles");     //权限
        String image = (String) params.get("image");        //专题图片

        if (Utils.isEmpty(id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要更新的专题id");
        }
        if (Utils.isEmpty(name)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "专题名称不能为空!");
        }

        SysDissertation dissertation = new SysDissertation();
        dissertation.setId(id);
        dissertation.setName(name);
        dissertation.setSort(sort);
        dissertation.setRoles(roles);
        dissertation.setUpdate_date(new Timestamp(System.currentTimeMillis()));
        dissertation.setImage(image);

        return 1 == dissertationDao.updateDissertation(dissertation);
    }
}
