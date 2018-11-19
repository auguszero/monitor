package com.bt.monitor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author augus
 * @create 2018/11/17 21:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlinkJobStatus implements Serializable {
    private String jobName ;
    private String host;
    private String port;
    private String jobId;
    private Date lastRunningTime ;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Date getLastRunningTime() {
        return lastRunningTime;
    }

    public void setLastRunningTime(Date lastRunningTime) {
        this.lastRunningTime = lastRunningTime;
    }
}
