package com.bt.monitor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author augus
 * @create 2018/11/17 21:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFlinkJob implements Serializable {
    @NotNull
    private String jobName ;
    @NotNull
    private String host;
    @NotNull
    private String port;
    @NotNull
    private String jobId;
    @NotNull
    private int stepTime;

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

    public int getStepTime() {
        return stepTime;
    }

    public void setStepTime(int stepTime) {
        this.stepTime = stepTime;
    }
}
