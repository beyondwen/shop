package com.wenhao.shop.core.base;

import com.wenhao.shop.core.constants.Constants;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BaseApiService<T> {

    public BaseResponse<T> setResultError(Integer code, String msg) {
        return setResult(code, msg, null);
    }

    //返回错误
    public BaseResponse<T> setResultError(String msg) {
        return setResult(Constants.HTTP_RES_CODE_500, msg, null);
    }

    //返回成功
    public BaseResponse<T> setResultSuccess(T data) {
        return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, data);
    }

    //返回成功
    public BaseResponse<T> setResultSuccess() {
        return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, null);
    }

    //返回成功
    public BaseResponse<T> setResultSuccess(String msg) {
        return setResult(Constants.HTTP_RES_CODE_200, msg, null);
    }

    //通用封装
    public BaseResponse<T> setResult(Integer code, String msg, T data) {
        return new BaseResponse<T>(code, msg, data);
    }

}
