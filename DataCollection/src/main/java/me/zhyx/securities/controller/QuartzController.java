package me.zhyx.securities.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zhyx.securities.common.model.ResponseModel;
import me.zhyx.securities.common.model.ScheduleJob;
import me.zhyx.securities.common.utils.ResultUtil;
import me.zhyx.securities.service.quartz.QuartzService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.Result;


/**
 * @Auther: yssq
 * @Date: 2019/4/16 11:20
 * @Description:
 */
@Api(value = "定时器管理接口", tags = "定时器")
@RestController
@RequestMapping("/quartz")
public class QuartzController {
    @Autowired
    QuartzService quartzService;
    @ApiOperation(httpMethod = "POST",tags = "定时器",produces = MediaType.APPLICATION_JSON_VALUE,value = "增加已编程的定时器")
    @PostMapping(value = "/add/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel add(@PathVariable("id") Integer id) {
        quartzService.add(id);
        return ResultUtil.success();
    }
    @ApiOperation(httpMethod = "PUT",tags = "定时器",produces = MediaType.APPLICATION_JSON_VALUE,value = "根据id启动定时器")
    @PutMapping(value = "/start/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel start(@PathVariable("id") int id) {
        quartzService.start(id);
        return ResultUtil.success();
    }
    @ApiOperation(httpMethod = "GET",tags = "定时器",produces = MediaType.APPLICATION_JSON_VALUE,value = "根据id暂停定时器")
    @GetMapping(value = "/pause/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel pause(@PathVariable("id") int id) {
        quartzService.pause(id);
        return ResultUtil.success();
    }
    @ApiOperation(httpMethod = "DELETE",tags = "定时器",produces = MediaType.APPLICATION_JSON_VALUE,value = "根据id删除定时器")
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel delete(@PathVariable("id") int id) {
        quartzService.delete(id);
        return ResultUtil.success();
    }
    @ApiOperation(httpMethod = "GET",tags = "定时器",produces = MediaType.APPLICATION_JSON_VALUE,value = "启动所有未删除的定时器")
    @GetMapping(value = "/startAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel startAll() {
        quartzService.startAllJob();
        return ResultUtil.success();
    }
    @ApiOperation(httpMethod = "GET",tags = "定时器",produces = MediaType.APPLICATION_JSON_VALUE,value = "暂停所有未删除的定时器")
    @GetMapping(value = "/pauseAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel pauseAll() {
        quartzService.pauseAllJob();
        return ResultUtil.success();
    }
}
