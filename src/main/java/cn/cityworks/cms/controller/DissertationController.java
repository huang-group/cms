package cn.cityworks.cms.controller;

import cn.cityworks.cms.base.Definition;
import cn.cityworks.cms.base.Result;
import cn.cityworks.cms.service.DissertationService;
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
 * 专题操作-控制层
 */
@RestController
@RequestMapping("/dissertation")
@Api(value = "专题操作controller", tags = {"专题操作接口"})
public class DissertationController {
    private DissertationService dissertationService;

    @Autowired
    public void setDissertationService(DissertationService dissertationService) {
        this.dissertationService = dissertationService;
    }

    @Autowired
    private HttpServletRequest request;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "添加专题", notes = "专题添加接口")
    public Result addDissertation(@RequestBody Map<String, Object> params) {
        boolean result = dissertationService.handleAddDissertation(params);
        return new Result(true, result ? Definition.RESPONSE_STATUS_SUCCESS : Definition.RESPONSE_STATUS_FAIL,
                result ? "添加专题成功" : "添加专题失败");
    }

    @PostMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "删除专题", notes = "专题删除接口")
    public Result deleteDissertation(@RequestBody Map<String, Object> params) {
        boolean result = dissertationService.handleDeleteDissertation(params);
        return new Result(true, result ? Definition.RESPONSE_STATUS_SUCCESS : Definition.RESPONSE_STATUS_FAIL,
                result ? "删除专题成功" : "删除专题失败");
    }

    @PostMapping(value = "/get", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取专题", notes = "专题获取接口")
    public Result getDissertation() {
        return new Result(true, Definition.RESPONSE_STATUS_SUCCESS, "专题列表获取成功",
                dissertationService.handleGetDissertation());
    }

    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "更新专题", notes = "专题更新接口")
    public Result updateDissertation(@RequestBody Map<String, Object> params) {
        boolean result = dissertationService.handleUpdateDissertation(params);
        return new Result(true, result ? Definition.RESPONSE_STATUS_SUCCESS : Definition.RESPONSE_STATUS_FAIL,
                result ? "更新专题成功" : "更新专题失败");
    }
}
