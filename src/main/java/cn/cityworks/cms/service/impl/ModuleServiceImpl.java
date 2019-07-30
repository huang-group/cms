package cn.cityworks.cms.service.impl;

import cn.cityworks.cms.base.Definition;
import cn.cityworks.cms.dao.ModuleDao;
import cn.cityworks.cms.domain.SysModule;
import cn.cityworks.cms.exception.BizException;
import cn.cityworks.cms.domain.Node;
import cn.cityworks.cms.service.ModuleService;
import cn.cityworks.cms.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 模块操作-逻辑层-实现类
 */
@Service
@Log4j2
public class ModuleServiceImpl implements ModuleService {

    private ModuleDao moduleDao;
    @Autowired(required = false)
    public void setModuleDao(ModuleDao moduleDao){
        this.moduleDao = moduleDao;
    }

    /**
     * 添加模块
     */
    @Override
    public boolean handleAddModule(Map<String, Object> params){
        //获取参数
        String name = (String)params.get("name");       //模块名称
        String fatherId = null == params.get("father_id") ? "0" : (String)params.get("father_id");      //父模块id
        String roles = (String)params.get("roles");     //权限
        int sort = null == params.get("sort") ? 0 : (int)params.get("sort");      //排序

        if(Utils.isEmpty(name)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "模块名称不能为空!");
        }

        //插入数据
        Timestamp time = new Timestamp(System.currentTimeMillis());
        SysModule sysModule = new SysModule();
        sysModule.setId(Utils.randomUUID());
        sysModule.setFather_id(fatherId);
        sysModule.setName(name);
        sysModule.setSort(sort);
        sysModule.setStatus(Definition.DATA_STATUS_NORMAL);
        sysModule.setRecord_status(Definition.DATA_STATUS_NORMAL);
        sysModule.setCreate_date(time);
        sysModule.setUpdate_date(time);
        sysModule.setRoles(roles);

        int result = moduleDao.insertModule(sysModule);

        return 1 == result;
    }

    /**
     * 删除模块
     */
    @Override
    public boolean handleDeleteModule(Map<String, Object> params){
        String id = (String)params.get("id");
        int status = null == params.get("status") ? 0 : (int)params.get("status");

        //判断参数是否是删除，不是则抛出异常
        if(status != Definition.DATA_STATUS_LOGIC_DELETE && status != Definition.DATA_STATUS_PHYSICS_DELETE){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请按照文档传入指定删除参数");
        }
        if(Utils.isEmpty(id)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要删除的模块id");
        }

        int result = moduleDao.deleteModule(id, status);

        return 1 == result;
    }

    /**
     * 获取模块树
     */
    @Override
    public List<Node> handleGetModule(){
        List<Map<String, Object>> modules = moduleDao.getAllModule();

        if(Utils.isNull(modules)){
            return new ArrayList<>();
        }

        //处理树
        return Utils.getTree(modules, "0");
    }

    /**
     * 更新模块
     */
    @Override
    public boolean handleUpdateModule(Map<String, Object> params){
        //获取参数
        String id = (String)params.get("id");
        String name = (String)params.get("name");       //模块名称
        String fatherId = (String)params.get("father_id");      //父模块id
        int sort = null == params.get("sort") ? 0 : (int)params.get("sort");      //排序
        String roles = (String)params.get("roles");     //权限

        if(Utils.isEmpty(id)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "请传入要更新的模块id");
        }
        if(Utils.isEmpty(name)){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "传入参数异常", "模块名称不能为空!");
        }

        SysModule module = new SysModule();
        module.setId(id);
        module.setName(name);
        module.setFather_id(fatherId);
        module.setSort(sort);
        module.setRoles(roles);
        module.setUpdate_date(new Timestamp(System.currentTimeMillis()));

        int result;
        if(Utils.isNull(fatherId)){
            result = moduleDao.updateModule(module);
        }else{
            result = moduleDao.updateModuleWithFatherId(module);
        }

        return 1 == result;
    }

}
