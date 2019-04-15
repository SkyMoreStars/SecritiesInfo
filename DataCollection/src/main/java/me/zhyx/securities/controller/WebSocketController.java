package me.zhyx.securities.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.zhyx.securities.common.enums.ResultEnum;
import me.zhyx.securities.common.model.ResponseModel;
import me.zhyx.securities.common.utils.ResultUtil;
import me.zhyx.securities.dao.Test;
import me.zhyx.securities.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author yssq
 * @Date: 2019/4/15 11:48
 * @Description:
 */
@Api(value = "/ws", tags = "消息推送")
@Slf4j
@RestController
@RequestMapping("/ws")
public class WebSocketController {

    @Autowired
    Test test;

    /**
     * 推送数据
     *
     * @param cid
     * @param message
     * @return
     */
    @ApiOperation(value = "根据用户id推送数据", tags = "消息推送", notes = "注意问题点", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/socket/push/{cid}")
    public ResponseModel pushToWeb(@PathVariable String cid, String message) {
        try {
            WebSocketServer.sendInfo(message, cid);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.error(ResultEnum.ERRORPARAMS);
        }
        return ResultUtil.success();
    }
    @GetMapping(value = "/test")
    public ResponseModel test(){
        return ResultUtil.success(test.test());

    }
}
