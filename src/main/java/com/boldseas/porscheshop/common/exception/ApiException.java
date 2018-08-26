package com.boldseas.porscheshop.common.exception;

/**
 * @author fei.ye
 * @version 2018/5/19.
 */
public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
