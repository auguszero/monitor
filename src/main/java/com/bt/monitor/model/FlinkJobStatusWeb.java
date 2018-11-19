package com.bt.monitor.model;

import lombok.*;

import java.util.Date;


/**
 * @author augus
 * @create 2018/11/17 22:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FlinkJobStatusWeb {
    private long duration;
    private String jid;
    private Date last_modification;
    private String name;
    private Date start_time;
    private String state;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public Date getLast_modification() {
        return last_modification;
    }

    public void setLast_modification(Date last_modification) {
        this.last_modification = last_modification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
