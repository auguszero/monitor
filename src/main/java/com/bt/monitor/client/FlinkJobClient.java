package com.bt.monitor.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bt.monitor.model.FlinkJobStatusWeb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author augus
 * @create 2018/11/17 22:19
 */
@Service
@Slf4j
public class FlinkJobClient {

    private  String RUNNING = "RUNNING";

    public Map<String,FlinkJobStatusWeb> getRunningJobList(String host, String port){
        RestTemplate restTemplate = new RestTemplate();
        String url= getUrl(host,port);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
        Map<String,FlinkJobStatusWeb> resultList = new HashMap<String,FlinkJobStatusWeb>();
        if(responseEntity.getStatusCode()==HttpStatus.OK){
            String result = responseEntity.getBody();
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray jsonArray = JSONObject.parseArray(jsonObject.get("jobs").toString());
            Iterator<Object> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                FlinkJobStatusWeb flinkJobStatusWeb = JSONObject.parseObject(iterator.next().toString(), new TypeReference<FlinkJobStatusWeb>() {});
                if(RUNNING.equals(flinkJobStatusWeb.getState())){
                    resultList.put(flinkJobStatusWeb.getName(),flinkJobStatusWeb);
                }
            }
        }else if(responseEntity.getStatusCode()==HttpStatus.NOT_FOUND){
            log.error("url {} is not found ",new Object[]{url});
        }
        return resultList;
    }

    public boolean getRunningJob(String host,String port,String name){
        Map<String,FlinkJobStatusWeb> nameList =  getRunningJobList(host,port);
        return  nameList.keySet().contains(name);
    }

    public String getUrl(String host, String port){
        String url= "http://"+host+":"+port+"/jobs/overview";
        return url;
    }

}
