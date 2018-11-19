package com.bt.monitor.controller;

import com.bt.monitor.model.AddFlinkJob;
import com.bt.monitor.model.ResponseBackResult;
import com.bt.monitor.service.FlinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Flink 服务监控
 *
 * @author augus
 * @create 2018/11/17 20:56
 */
@RestController()
@RequestMapping(value="/")
@Slf4j
@Api("flink 操作api ")
public class FlinkAddController {

    @Autowired
    private FlinkService flinkService;

    @ApiOperation(value="添加flink Job 地址 ", notes="添加flink Job 地址 ")
    @ApiImplicitParam(name = "addFlinkJob", value = "@NotNull\n" +
            "    private String jobName ;\n" +
            "    @NotNull\n" +
            "    private String host;\n" +
            "    @NotNull\n" +
            "    private String port;\n" +
            "    @NotNull\n" +
            "    private String jobId;\n" +
            "    @NotNull\n" +
            "    private int stepTime;", required = true, dataType = "Integer")
    @RequestMapping(value = "/AddFlinkJob" ,method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ResponseBackResult addFlinkJob(@RequestBody AddFlinkJob addFlinkJob){
        log.info("addFlinkJob {}",new Object[]{addFlinkJob.toString()});
        ResponseBackResult<Boolean> responseBackResult= new ResponseBackResult<Boolean>();
        boolean result = flinkService.addFlinkJob(addFlinkJob);
        responseBackResult.setData(result);
        responseBackResult.setMessage("success");
        responseBackResult.setStatus(200);
        return responseBackResult;
    }

    @RequestMapping(value = "/jobs/overview" ,method = RequestMethod.GET)
    public String getJobs(){
        String result = "{\"jobs\":[{\"jid\":\"29e88f7c424ba4fc862487d6203d1e89\",\"name\":\"Flink-WorkFlow demo\",\"state\":\"FAILING\",\"start-time\":1542365871279,\"end-time\":-1,\"duration\":99727447,\"last-modification\":1542433457762,\"tasks\":{\"total\":16,\"created\":0,\"scheduled\":0,\"deploying\":0,\"running\":0,\"finished\":0,\"canceling\":3,\"canceled\":12,\"failed\":1,\"reconciling\":0}},{\"jid\":\"ee1d5542933aa96394bc108361ce2d88\",\"name\":\"Flink-WorkFlow demo\",\"state\":\"RUNNING\",\"start-time\":1542457608990,\"end-time\":-1,\"duration\":7989736,\"last-modification\":1542457614660,\"tasks\":{\"total\":16,\"created\":0,\"scheduled\":0,\"deploying\":0,\"running\":16,\"finished\":0,\"canceling\":0,\"canceled\":0,\"failed\":0,\"reconciling\":0}}]}";
        return  result;
    }
}
