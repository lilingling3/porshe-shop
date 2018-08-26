package com.boldseas.porscheshop.common.exception;

import lombok.Data;

/**
 * @author fei.ye
 * @version 2018/5/19.
 */
@Data
public class ErrorInfo<T> {
    public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    private Integer code;
    private String message;
    private String url;
    private T data;
}
