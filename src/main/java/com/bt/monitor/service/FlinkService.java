package com.bt.monitor.service;

import com.bt.monitor.client.FlinkJobClient;
import com.bt.monitor.model.AddFlinkJob;
import com.bt.monitor.model.FlinkJobStatus;
import com.javatool.email.proxy.SendMailManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.naming.factory.SendMailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author augus
 * @create 2018/11/17 21:30
 */
@Service
@Slf4j
public class FlinkService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private FlinkJobClient flinkJobClient;

    public boolean addFlinkJob(AddFlinkJob addFlinkJob){
        ValueOperations<String, String> operations  = redisTemplate.opsForValue();
        SetOperations<String, String> setoperations  = redisTemplate.opsForSet();
        operations.set(addFlinkJob.getJobId()+":flink:host",addFlinkJob.getHost());
        operations.set(addFlinkJob.getJobId()+":flink:port",addFlinkJob.getPort());
        operations.set(addFlinkJob.getJobId()+":flink:name",addFlinkJob.getJobName());
        operations.set(addFlinkJob.getJobId()+":flink:stepTime",addFlinkJob.getStepTime()+"");
        operations.set(addFlinkJob.getJobName()+":flink:jobid",addFlinkJob.getJobId());
        setoperations.add("flink:list",addFlinkJob.getJobId());
        return true;
    }

    public FlinkJobStatus getFlinkJobById(String flinkJobId){
        ValueOperations<String, String> operations  =  redisTemplate.opsForValue();
        String host = operations.get(flinkJobId+":flink:host");
        String port = operations.get(flinkJobId+":flink:port");
        String name = operations.get(flinkJobId+":flink:name");
        long lastRunningTime = Long.parseLong(operations.get(flinkJobId+":flink:lastRunningTime"));
        FlinkJobStatus flinkJobStatus = new FlinkJobStatus();
        flinkJobStatus.setJobId(flinkJobId);
        flinkJobStatus.setHost(host);
        flinkJobStatus.setPort(port);
        flinkJobStatus.setJobName(name);
        Date date = new Date();
        date.setTime(lastRunningTime);
        flinkJobStatus.setLastRunningTime(date);
        return flinkJobStatus;
    }



    @Scheduled(cron = "0 0/1 * * * ? ")
    public void checkFlinkJob(){
        ValueOperations<String, String> operations  = redisTemplate.opsForValue();
        SetOperations<String, String> setoperations  = redisTemplate.opsForSet();
        Set<String> jobSet = setoperations.members("flink:list");
        Map<String,Integer> lastHeadTimeStemp = new HashMap<String,Integer>();
        List<FlinkJobStatus> jobList = new ArrayList<FlinkJobStatus>();
        for (String jobId:jobSet){
            Integer lastHeadTime = lastHeadTimeStemp.get(jobId);
            String host = operations.get(jobId+":flink:host");
            String port = operations.get(jobId+":flink:port");
            String name = operations.get(jobId+":flink:name");
            long stepTime = Long.parseLong(operations.get(jobId+":flink:stepTime"));
            if(lastHeadTime==null||lastHeadTime==stepTime){
                boolean jobResult = flinkJobClient.getRunningJob(host,port,name);
                log.info("jobId {} jobName {} host {} port {} jobResult {}",new Object[]{jobId,name,host,port,jobResult});
                if(!jobResult){
                    String url = flinkJobClient.getUrl(host,port);
                    try {
                        SendMailManager.sendMail("flink Job Error"," jobId "+name+" error"+"\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                lastHeadTime=0;

            }
            lastHeadTime++;
            lastHeadTimeStemp.put(jobId,lastHeadTime);
        }
    }



    public static void main(String[] args) {
        Map<String,Integer> lastHeadTimeStemp = new HashMap<String,Integer>();
        System.out.println(lastHeadTimeStemp.get("1"));
    }

}
