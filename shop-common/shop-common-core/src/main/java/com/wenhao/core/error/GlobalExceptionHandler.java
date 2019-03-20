package com.wenhao.core.error;

import com.alibaba.fastjson.JSONObject;
import com.wenhao.base.BaseApiService;
import com.wenhao.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends BaseApiService<JSONObject> {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public BaseResponse<JSONObject> exceptionHandler(Exception e) {
        log.info("###全局捕获异常###,error:{}", e);
        return setResultError("系统错误!");
    }

    /*@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<JSONObject> handleHttpAccessException(Exception e) {
        log.info(e.getMessage(), e);
        return setResultError("访问外部服务错误: " + e.getLocalizedMessage());
    }*/

}

