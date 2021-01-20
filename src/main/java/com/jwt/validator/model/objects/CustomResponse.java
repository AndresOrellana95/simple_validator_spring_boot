package com.jwt.validator.model.objects;

public class CustomResponse {
    private String code;
    private String response;
    private Object objectResponse;

    public CustomResponse() {
    }

    public CustomResponse(String code, String response, Object objectResponse) {
        this.code = code;
        this.response = response;
        this.objectResponse = objectResponse;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Object getObjectResponse() {
        return this.objectResponse;
    }

    public void setObjectResponse(Object objectResponse) {
        this.objectResponse = objectResponse;
    }

    public CustomResponse code(String code) {
        this.code = code;
        return this;
    }

    public CustomResponse response(String response) {
        this.response = response;
        return this;
    }

    public CustomResponse objectResponse(Object objectResponse) {
        this.objectResponse = objectResponse;
        return this;
    }
}
