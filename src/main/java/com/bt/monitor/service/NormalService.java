package com.bt.monitor.service;

import com.bt.monitor.model.AddNormalJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ContextLifecycleScheduledTaskRegistrar;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author augus
 * @create 2018/11/19 22:58
 */
@Service
@Slf4j
public class NormalService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    private String saddList = "normal:{jobType}:list";
    private Map<String,String> jobId_cron = new HashMap<String,String>();
    private Map<String,String> jobId_jobName = new HashMap<String,String>();
    private String ADD_ALREADY = "{jobId}/{jobName} add already";
    private String ADD = "{jobId}/{jobName} add ";
    private String CRON_CHANGE = "{jobId}/{jobName} {originCron} has changed {currenCron}";
    private ScheduledTaskRegistrar scheduledTaskRegistrar = new ScheduledTaskRegistrar();
    private static ContextLifecycleScheduledTaskRegistrar contextLifecycleScheduledTaskRegistrar = new ContextLifecycleScheduledTaskRegistrar();

    static {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        scheduler.initialize();
        contextLifecycleScheduledTaskRegistrar.setScheduler(scheduler);
    }


    public String addNormalService(AddNormalJob addNormalJob){
        String result = "";
        String jobName = addNormalJob.getJobName();
        String jobId = addNormalJob.getJobId();
        String cronListner = addNormalJob.getCronListner();
        int heartStemp = addNormalJob.getHeartStemp();
        SetOperations<String,String> setOperations = redisTemplate.opsForSet();
        setOperations.add(RedisKeyStatic.getSaddListKey(RedisKeyStatic.Normal),jobId);
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(RedisKeyStatic.getjobNameKey(RedisKeyStatic.Normal,jobId),jobName);
        valueOperations.set(RedisKeyStatic.getjobNameKey(RedisKeyStatic.Normal,jobName)+"jobId",jobId);
        valueOperations.set(RedisKeyStatic.getjobNameKey(RedisKeyStatic.Normal,jobId)+"cronListner",cronListner);
        valueOperations.set(RedisKeyStatic.getjobNameKey(RedisKeyStatic.Normal,jobId)+"heartStemp",heartStemp+"");

        if(jobId_cron.keySet().contains(jobId)&&jobId_cron.get(jobId)==cronListner){
            result=  ADD_ALREADY.replace("{jobId}",jobId).replace("{jobName}",jobName);
        }else if (jobId_cron.keySet().contains(jobId)&&jobId_cron.get(jobId)!=cronListner){
            result=  CRON_CHANGE.replace("{jobId}",jobId).replace("{jobName}",jobName);
        }else if(!jobId_cron.keySet().contains(jobId)){
            result=  ADD.replace("{jobId}",jobId).replace("{jobName}",jobName);
        }


        return result;
    }



    public void addTaskJob(String jobId,String jobName ,final String cron){
        CronTask cronTask = new CronTask(new Runnable() {
            @Override
            public void run() {
                System.out.println(11111);
            }
        },cron);
        TriggerTask triggerTask = new TriggerTask(new Runnable() {
            @Override
            public void run() {
                System.out.println(11232);
            }
        }, new Trigger() {
            @Nullable
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                //任务触发，可修改任务的执行周期.

                CronTrigger trigger = new CronTrigger(cron);

                Date nextExec = trigger.nextExecutionTime(triggerContext);

                return nextExec;
            }
        });
        contextLifecycleScheduledTaskRegistrar.addTriggerTask(triggerTask);
        contextLifecycleScheduledTaskRegistrar.addCronTask(cronTask);
        contextLifecycleScheduledTaskRegistrar.afterSingletonsInstantiated();
    }



}
