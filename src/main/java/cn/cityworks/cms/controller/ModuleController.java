package cn.cityworks.cms.controller;

import cn.cityworks.cms.base.Definition;
import cn.cityworks.cms.base.Result;
import cn.cityworks.cms.service.ModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 模块操作-控制层
 */
@RestController
@RequestMapping("/module")
@Api(value = "模块操作controller", tags = {"模块操作接口"})
public class ModuleController {

    private ModuleService moduleService;
    @Autowired
    public void setModuleService(ModuleService moduleService){
        this.moduleService = moduleService;
    }

    @Autowired
    private HttpServletRequest request;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "添加模块", notes = "模块添加接口")
    public Result addModule(@RequestBody Map<String, Object> params){
        boolean result = moduleService.handleAddModule(params);
        return new Result(result, result ? Definition.RESPONSE_STATUS_SUCCESS : Definition.RESPONSE_STATUS_FAIL,
                result ? "模块添加成功!" : "模块添加失败!");
    }

    @PostMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "删除模块", notes = "模块删除接口")
    public Result deleteModule(@RequestBody Map<String, Object> params){
        boolean result = moduleService.handleDeleteModule(params);
        return new Result(result, result ? Definition.RESPONSE_STATUS_SUCCESS : Definition.RESPONSE_STATUS_FAIL,
                result ? "模块删除成功!" : "模块删除失败!");
    }

    @PostMapping(value = "/get", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取模块", notes = "模块获取接口")
    public Result getModule(){
        return new Result(true, Definition.RESPONSE_STATUS_SUCCESS, "模块列表获取成功",
                moduleService.handleGetModule());
    }

    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "更新模块", notes = "模块更新接口")
    public Result updateModule(@RequestBody Map<String, Object> params){
        boolean result = moduleService.handleUpdateModule(params);
        return new Result(result, result ? Definition.RESPONSE_STATUS_SUCCESS : Definition.RESPONSE_STATUS_FAIL,
                result ? "模块更新成功!" : "模块更新失败!");
    }
}
