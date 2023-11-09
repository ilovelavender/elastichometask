package com.olegrubin.vicariusdemo.model.common;

public class SuccessResponse<T> extends BaseResponse<T> {

    public SuccessResponse(T data) {
        super(true, data);
    }
}
