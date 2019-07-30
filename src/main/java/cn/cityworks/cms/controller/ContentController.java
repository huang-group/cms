package cn.cityworks.cms.controller;

import cn.cityworks.cms.base.Result;
import cn.cityworks.cms.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 内容管理-控制层
 */
@RestController
@RequestMapping("/content")
@Api(value = "内容操作controller", tags = {"内容操作接口"})
public class ContentController {

    private ContentService contentService;
    @Autowired
    public void setContentService(ContentService contentService){
        this.contentService = contentService;
    }

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "添加内容", notes = "内容添加接口")
    public Result addContent(@RequestBody Map<String, Object> params){
        return null;
    }

    @PostMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "删除内容", notes = "内容删除接口")
    public Result deleteContent(@RequestBody Map<String, Object> params){
        return null;
    }

    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "更新内容", notes = "内容更新接口")
    public Result updateContent(@RequestBody Map<String, Object> params){
        return null;
    }

    @PostMapping(value = "/audit", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "审核内容", notes = "内容审核接口")
    public Result auditContent(@RequestBody Map<String, Object> params){
        return null;
    }


}
