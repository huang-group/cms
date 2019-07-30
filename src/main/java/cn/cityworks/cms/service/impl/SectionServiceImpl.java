package cn.cityworks.cms.service.impl;

import cn.cityworks.cms.base.Definition;
import cn.cityworks.cms.dao.SectionDao;
import cn.cityworks.cms.domain.SysSection;
import cn.cityworks.cms.exception.BizException;
import cn.cityworks.cms.service.SectionService;
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
public class SectionServiceImpl implements SectionService {
    private SectionDao sectionDao;

    @Autowired(required = false)
    public void setSectionDao(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }

    /**
     * 添加专栏
     */
    @Override
    public boolean handleAddSection(Map<String, Object> params) {
        //获取参数
        String name = null == params.get("name") ? null : (String) params.get("name");        //专栏名称
        String channel_id = null == params.get("channel_id") ? null : (String) params.get("channel_id");      //频道id
        int sort = null == params.get("sort") ? 0 : (int) params.get("sort");     //排序
        String rolse = null == params.get("rolse") ? null : (String) params.get("rolse");      //权限

        if (null == name || "".equals(name)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "专栏名称不能为空!");
        }
        if (null == channel_id || "".equals(channel_id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "频道id不能为空!");
        }

        //插入数据
        Timestamp time = new Timestamp(System.currentTimeMillis());
        SysSection section = new SysSection();
        section.setId(Utils.randomUUID());
        section.setName(name);
        section.setChannel_id(channel_id);
        section.setSort(sort);
        section.setStatus(Definition.DATA_STATUS_NORMAL);
        section.setRecord_status(Definition.DATA_STATUS_NORMAL);
        section.setCreate_date(time);
        section.setUpdate_date(time);
        section.setRoles(rolse);

        return 1 == sectionDao.insertSection(section);
    }

    /**
     * 删除专栏
     */
    @Override
    public boolean handleDeleteSection(Map<String, Object> params) {
        String id = null == params.get("id") ? null : (String) params.get("id");
        int status = null == params.get("status") ? 0 : (int) params.get("status");

        if (status != Definition.DATA_STATUS_LOGIC_DELETE && status != Definition.DATA_STATUS_PHYSICS_DELETE) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请按照文档传入指定删除参数");
        }
        if (null == id || "".equals(id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要删除的频道id");
        }

        return 1 == sectionDao.deleteSection(id, status);
    }

    /**
     * 获取专栏
     */
    @Override
    public List<SysSection> handleGetSection() {
        List<SysSection> section = sectionDao.getAllSection();

        if (null == section) {
            return new ArrayList<>();
        }

        return section;
    }

    /**
     * 更新专栏
     */
    @Override
    public boolean handleUpdateSection(Map<String, Object> params) {
        //获取参数
        String id = null == params.get("id") ? null : (String) params.get("id");
        String channel_id = null == params.get("channel_id") ? null : (String) params.get("channel_id");
        String name = null == params.get("name") ? null : (String) params.get("name");
        int sort = null == params.get("sort") ? 0 : (int) params.get("sort");
        String roles = null == params.get("roles") ? null : (String) params.get("roles");

        if (null == id || "".equals(id)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要更新的专栏id");
        }
        if (null == name || "".equals(name)) {
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "专栏名称不能为空");
        }

        SysSection section = new SysSection();
        section.setId(id);
        section.setName(name);
        section.setChannel_id(channel_id);
        section.setSort(sort);
        section.setRoles(roles);
        section.setUpdate_date(new Timestamp(System.currentTimeMillis()));

        int result;
        if (null == channel_id) {
            result = sectionDao.updateSection(section);
        } else {
            result = sectionDao.updateSectionWithChannelId(section);
        }
        return 1 == result;
    }
}
