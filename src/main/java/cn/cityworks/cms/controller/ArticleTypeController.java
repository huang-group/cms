package cn.cityworks.cms.controller;

import cn.cityworks.cms.base.Definition;
import cn.cityworks.cms.base.Result;
import cn.cityworks.cms.service.ArticleTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 文章类型操作-控制层
 */
@RestController
@RequestMapping("/articleType")
@Api(value = "文章类型操作controller", tags = {"文章类型操作接口"})
public class ArticleTypeController {
    private ArticleTypeService articleTypeService;

    @Autowired
    public void setArticleTypeService(ArticleTypeService articleTypeService) {
        this.articleTypeService = articleTypeService;
    }

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "添加文章类型", notes = "文章类型添加接口")
    public Result addArticleType(@RequestBody Map<String, Object> params) {
        boolean result = articleTypeService.handleAddArticleType(params);
        return new Result(result, result ? Definition.RESPONSE_STATUS_SUCCESS : Definition.RESPONSE_STATUS_FAIL,
                result ? "文章类型添加成功!" : "文章类型添加失败!");
    }

    @PostMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "删除文章类型", notes = "文章类型删除接口")
    public Result deleteArticleType(@RequestBody Map<String, Object> params) {
        boolean result = articleTypeService.handleDeleteArticleType(params);
        return new Result(result, result ? Definition.RESPONSE_STATUS_SUCCESS : Definition.RESPONSE_STATUS_FAIL,
                result ? "文章类型删除成功!" : "文章类型删除失败!");
    }

    @PostMapping(value = "/get", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取文章类型", notes = "文章类型获取接口")
    public Result getArticleType() {
        return new Result(true, Definition.RESPONSE_STATUS_SUCCESS, "文章类型列表获取成功",
                articleTypeService.handleGetArticleType());
    }

    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "更新文章类型", notes = "文章类型更新接口")
    public Result updateArticleType(@RequestBody Map<String, Object> params) {
        boolean result = articleTypeService.handleUpdateArticleType(params);
        return new Result(result, result ? Definition.RESPONSE_STATUS_SUCCESS : Definition.RESPONSE_STATUS_FAIL,
                result ? "文章类型更新成功!" : "文章类型更新失败!");
    }
}
