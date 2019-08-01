package cn.cityworks.cms.controller;

import cn.cityworks.cms.base.Definition;
import cn.cityworks.cms.base.Result;
import cn.cityworks.cms.service.ChannelService;
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
 * 频道操作-控制层
 */
@RestController
@RequestMapping("/channel")
@Api(value = "频道操作controller", tags = {"频道操作接口"})
public class ChannelController {
    private ChannelService channelService;

    @Autowired
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Autowired
    private HttpServletRequest request;

    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "添加频道", notes = "频道添加接口")
    public Result addChannel(@RequestBody Map<String, Object> params) {
        boolean result = channelService.handleAddChannel(params);
        return new Result(result, result ? Definition.RESPONSE_STATUS_SUCCESS : Definition.RESPONSE_STATUS_FAIL,
                result ? "频道添加成功!" : "频道添加失败!");
    }

    @PostMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "删除频道", notes = "频道删除接口")
    public Result deleteChannel(@RequestBody Map<String, Object> params) {
        boolean result = channelService.handleDeleteChannel(params);
        return new Result(result, result ? Definition.RESPONSE_STATUS_SUCCESS : Definition.RESPONSE_STATUS_FAIL,
                result ? "频道删除成功!" : "频道删除失败!");
    }

    @PostMapping(value = "/get", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取频道", notes = "频道获取接口")
    public Result getChannel() {
        return new Result(true, Definition.RESPONSE_STATUS_SUCCESS, "频道列表获取成功",
                channelService.handleGetChannel());
    }

    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "更新频道", notes = "频道更新接口")
    public Result updateChannel(@RequestBody Map<String, Object> params) {
        boolean result = channelService.handleUpdateChannel(params);
        return new Result(result, result ? Definition.RESPONSE_STATUS_SUCCESS : Definition.RESPONSE_STATUS_FAIL,
                result ? "频道更新成功!" : "频道更新失败!");
    }
}
