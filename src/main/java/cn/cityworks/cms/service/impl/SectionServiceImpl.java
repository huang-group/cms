package cn.cityworks.cms.service.impl;

import cn.cityworks.cms.base.Definition;
import cn.cityworks.cms.dao.SectionDao;
import cn.cityworks.cms.domain.Node;
import cn.cityworks.cms.domain.SysSection;
import cn.cityworks.cms.exception.BizException;
import cn.cityworks.cms.service.SectionService;
import cn.cityworks.cms.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 专栏操作-逻辑层-实现类
 */
@Service
public class SectionServiceImpl implements SectionService {

    private SectionDao sectionDao;
    @Autowired(required = false)
    public void setSectionDao(SectionDao sectionDao){
        this.sectionDao = sectionDao;
    }

    /** 添加专栏 */
    @Override
    public boolean handleAddSection(Map<String, Object> params){
        //获取参数
        String name = (String)params.get("name");       //专栏名称
        String channel_id = (String)params.get("channel_id");       //所属频道id
        String roles = (String)params.get("roles");     //权限
        int sort = null == params.get("sort") ? 0 : (int)params.get("sort");      //排序

        if(Utils.isEmpty(name)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "专栏名称不能为空!");
        }
        if(Utils.isEmpty(sectionDao.hasChannelId(channel_id))){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "专栏必须存在某个频道下!");
        }

        //插入数据
        Timestamp time = new Timestamp(System.currentTimeMillis());
        SysSection sysSection = new SysSection();
        sysSection.setId(Utils.randomUUID());
        sysSection.setName(name);
        sysSection.setChannel_id(channel_id);
        sysSection.setSort(sort);
        sysSection.setStatus(Definition.DATA_STATUS_NORMAL);
        sysSection.setRecord_status(Definition.DATA_STATUS_NORMAL);
        sysSection.setCreate_date(time);
        sysSection.setUpdate_date(time);
        sysSection.setRoles(roles);

        return 1 == sectionDao.insertSection(sysSection);
    }

    /** 删除专栏 */
    @Override
    public boolean handleDeleteSection(Map<String, Object> params){
        String id = (String)params.get("id");
        int status = null == params.get("status") ? 0 : (int)params.get("status");

        //判断参数是否是删除，不是则抛出异常
        if(status != Definition.DATA_STATUS_LOGIC_DELETE && status != Definition.DATA_STATUS_PHYSICS_DELETE){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请按照文档传入指定删除参数");
        }
        if(Utils.isEmpty(id)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要删除的模块id");
        }

        return 1 == sectionDao.deleteSection(id, status);
    }

    /** 获取专栏 */
    @Override
    public List<Map<String, Object>> handleGetSection(Map<String, Object> params){
        String channel_id = (String)params.get("channel_id");       //所属频道id

        return sectionDao.getAllSection(channel_id);
    }

    /** 更新专栏 */
    @Override
    public boolean handleUpdateSection(Map<String, Object> params){
        //获取参数
        String id = (String)params.get("id");
        String name = (String)params.get("name");       //专栏名称
        String channel_id = (String)params.get("channel_id");       //所属频道id
        String roles = (String)params.get("roles");     //权限
        int sort = null == params.get("sort") ? 0 : (int)params.get("sort");      //排序

        if(Utils.isEmpty(id)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "专栏id不能为空!");
        }
        if(Utils.isEmpty(name)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "专栏名称不能为空!");
        }

        SysSection sysSection = new SysSection();
        sysSection.setId(id);
        sysSection.setName(name);
        sysSection.setChannel_id(channel_id);
        sysSection.setSort(sort);
        sysSection.setRoles(roles);
        sysSection.setUpdate_date(new Timestamp(System.currentTimeMillis()));

        if(!Utils.isEmpty(channel_id)){
            if(Utils.isEmpty(sectionDao.hasChannelId(channel_id))){
                throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "专栏必须存在某个频道下!");
            }
        }

        return 1 == sectionDao.updateSection(sysSection);
    }

}
