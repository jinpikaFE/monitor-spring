package com.macro.mall.tiny.modules.monitor.controller;


import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.monitor.model.MonitorApikey;
import com.macro.mall.tiny.modules.monitor.service.MonitorApikeyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author macro
 * @since 2023-07-26
 */
@Api(tags = "MonitorApikeyController")
@Tag(name = "MonitorApikeyController", description = "监控项目 apikey")
@RestController
@RequestMapping("/api/v1/monitor/monitorApikey")
public class MonitorApikeyController {
    @Autowired
    private MonitorApikeyService monitorApikeyService;

    @ApiOperation("获取所有的apikey")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<CommonResult<Map<String, Object>>> getList() {
        List<MonitorApikey> monitorApikeyList = monitorApikeyService.list();
        Map<String, Object> res = new HashMap<>();
        res.put("list", monitorApikeyList);
        return CommonResult.success(res);
    }

    @ApiOperation("添加apikey")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommonResult<Object>> create(@RequestBody MonitorApikey monitorApikey) {
        Boolean success = monitorApikeyService.save(monitorApikey);
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除apikey")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<CommonResult<Object>> delete(@PathVariable Long id) {
        Boolean success = monitorApikeyService.removeById(id);
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("更新apikey")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<CommonResult<Object>> update(@PathVariable Long id, @RequestBody MonitorApikey monitorApikey) {
        monitorApikey.setId(id);
        Boolean success = monitorApikeyService.updateById(monitorApikey);
        if (success) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }
}

