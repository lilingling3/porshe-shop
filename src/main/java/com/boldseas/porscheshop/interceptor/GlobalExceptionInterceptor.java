package com.boldseas.porscheshop.interceptor;

import com.alibaba.fastjson.JSON;
import com.boldseas.porscheshop.common.exception.ErrorInfo;
import com.boldseas.porscheshop.dtos.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 处理全局异常
 *
 * @author fei.ye
 * @version 2018/5/17.
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionInterceptor {

    private static final String DEFAULT_ERROR_VIEW = "error/error";
    private static final String JSON_CONTENT_TYPE = "application/json; charset=UTF-8";
    private static final String API_CONTAINS_PATH = "/api/";

    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("global error:\r\n url=" + request.getRequestURL(), ex);

        if (isRestApiRequest(request)) {
            jsonErrorHandler(request, response, ex);
            return null;
        }
        return viewErrorHandler(request, ex);
    }

    /**
     * 统一处理请求参数无效的情况
     * 针对情况：@Valid 验证入参对象字段不符合条件 获取BindingResult错误信息
     *
     * @param ex 方法参数无效异常
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestResult handleArgumentInvalidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errorMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return RestResult.failure(String.join("|", errorMessages));
    }

    private ModelAndView viewErrorHandler(HttpServletRequest req, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    private void jsonErrorHandler(HttpServletRequest req, HttpServletResponse response, Exception ex) {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(ex.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("");
        r.setUrl(req.getRequestURL().toString());

        writeJson(response, JSON.toJSONString(r));
    }

    private void writeJson(HttpServletResponse response, String jsonString) {
        response.setContentType(JSON_CONTENT_TYPE);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(jsonString));
            writer.close();
        } catch (IOException e) {
            log.error("--- writer print error :{}", e);
        } finally {
            if (null != writer) {
                writer.flush();
                writer.close();
            }
        }
    }

    /**
     * 判断请求是否为RestApi请求.
     *
     * @param request 请求对象
     * @return boolean
     */
    private boolean isRestApiRequest(HttpServletRequest request) {
        String url = request.getServletPath();
        return url.contains(API_CONTAINS_PATH);
    }
}
