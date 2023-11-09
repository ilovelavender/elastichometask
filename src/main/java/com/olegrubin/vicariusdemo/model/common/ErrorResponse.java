package com.olegrubin.vicariusdemo.model.common;

public class ErrorResponse extends BaseResponse<String> {

    public ErrorResponse(String errorMessage) {
        super(false, errorMessage);
    }
}
