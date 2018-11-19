package com.bt.monitor.model;

import lombok.*;

import java.io.Serializable;

/**
 * @author augus
 * @create 2018/11/17 21:06
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBackResult<T> implements Serializable {

    private int status;
    private String message;
    private T data;
}

