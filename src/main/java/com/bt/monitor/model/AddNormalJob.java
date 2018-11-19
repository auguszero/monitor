package com.bt.monitor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author augus
 * @create 2018/11/19 22:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddNormalJob {

    @NotNull
    private String jobName ;
    @NotNull
    private String jobId ;
    @NotNull
    private String cronListner ;
    @NotNull
    private int heartStemp;

}
