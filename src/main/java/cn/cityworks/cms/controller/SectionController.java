package cn.cityworks.cms.controller;

import cn.cityworks.cms.base.Definition;
import cn.cityworks.cms.base.Result;
import cn.cityworks.cms.service.DissertationService;
import cn.cityworks.cms.service.SectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 专栏操作-控制层
 */
@RestController
@RequestMapping("/section")
@Api(value = "专栏操作controller", tags = {"专栏操作接口"})
public class SectionController {
    private SectionService sectionService;

    @Autowired
    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "添加专栏", notes = "专栏添加接口")
    public Result addSection(@RequestBody Map<String, Object> params) {
        boolean result = sectionService.handleAddSection(params);
        return new Result(true, result ? Definition.RESPONSE_STATUS_SUCCESS : Definition.RESPONSE_STATUS_FAIL,
                result ? "添加专栏成功" : "添加专栏失败");
    }

    @PostMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "删除专栏", notes = "专栏删除接口")
    public Result deleteSection(@RequestBody Map<String, Object> params) {
        boolean result = sectionService.handleDeleteSection(params);
        return new Result(true, result ? Definition.RESPONSE_STATUS_SUCCESS : Definition.RESPONSE_STATUS_FAIL,
                result ? "删除专栏成功" : "删除专栏失败");
    }

    @PostMapping(value = "/get", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取专栏", notes = "专栏获取接口")
    public Result getSection() {
        return new Result(true, Definition.RESPONSE_STATUS_SUCCESS, "专栏列表获取成功",
                sectionService.handleGetSection());
    }

    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "更新专栏", notes = "专栏更新接口")
    public Result updateSection(@RequestBody Map<String, Object> params) {
        boolean result = sectionService.handleUpdateSection(params);
        return new Result(true, result ? Definition.RESPONSE_STATUS_SUCCESS : Definition.RESPONSE_STATUS_FAIL,
                result ? "更新专栏成功" : "更新专栏失败");
    }
}
