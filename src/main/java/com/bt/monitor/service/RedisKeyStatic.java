package com.bt.monitor.service;

/**
 * redis key 静态常量
 *
 * @author augus
 * @create 2018/11/19 23:02
 */

public class RedisKeyStatic {
    private static String saddList = "{jobType}:list";

    private static String jobName = "{jobType}:{jobid}:jobName";

    private static String jobFex = "{jobType}:{jobid}:";

    public static String Normal = "normal";


    public static String getSaddListKey(String jobType){
        return saddList.replace("{jobType}",jobType);
    }

    public static String getjobNameKey(String jobType,String jobId){
        return jobName.replace("{jobType}",jobType).replace("{jobid}",jobId);
    }

    public static String getjobFexKey(String jobType,String jobId){
        return jobFex;
    }


}
