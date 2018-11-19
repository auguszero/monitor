//package com.bt.monitor.controller;
//
//import com.bt.monitor.model.AddFlinkJob;
//import com.bt.monitor.model.AddNormalJob;
//import com.bt.monitor.model.ResponseBackResult;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 一般性服务监控软件
// *
// * @author augus
// * @create 2018/11/19 22:30
// */
//@RestController()
//@RequestMapping(value="/normal")
//@Slf4j
//@Api("一般服务")
//public class NormalController {
//
//
//    /**
//     * 添加一般性定时job 服务 在制定时间点进行操作的服务  若在计划点未执行 将进行报警提醒
//     * @param addNormalJob
//     * @return
//     */
//    @ApiOperation(value="添加一般性定时 Job 服务 ", notes="添加一般性定时 Job 服务")
//    @ApiImplicitParam(name = "addNormalJob", value = "", required = true, dataType = "Integer")
//    @RequestMapping(value = "/addNormalJob" ,method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
//    public ResponseBackResult addNormalJob(@RequestBody AddNormalJob addNormalJob){
//        log.info("addFlinkJob {}",new Object[]{addNormalJob.toString()});
//        ResponseBackResult<Boolean> responseBackResult= new ResponseBackResult<Boolean>();
//        boolean result = flinkService.addFlinkJob(addFlinkJob);
//        responseBackResult.setData(result);
//        responseBackResult.setMessage("success");
//        responseBackResult.setStatus(200);
//        return responseBackResult;
//    }
//
//}
