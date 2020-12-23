package com.example.demo.dto;

import com.example.demo.constants.Constants;

public class ResponseData {

    private String statusCode; //Constants.ERROR_CODE.
    private String message;
    private Object data;

    public static ResponseData success() {
        ResponseData responseData = new ResponseData();
        responseData.setStatusCode(Constants.ERROR_CODE.SUCCESS);
        return responseData;
    }

    public static ResponseData success(String message) {
        ResponseData responseData = new ResponseData();
        responseData.setMessage(message);
        responseData.setStatusCode(Constants.ERROR_CODE.SUCCESS);
        return responseData;
    }

    public static ResponseData success(String message, Object data) {
        ResponseData responseData = new ResponseData();
        responseData.setMessage(message);
        responseData.setData(data);
        responseData.setStatusCode(Constants.ERROR_CODE.SUCCESS);
        return responseData;
    }

    public ResponseData() {
    }

    public ResponseData(String errorCode) {
        this.statusCode = statusCode;
    }

    public ResponseData(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ResponseData(String statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public static ResponseData fail(String message) {
        ResponseData responseData = new ResponseData();
        responseData.setMessage(message);
        responseData.setStatusCode(Constants.ERROR_CODE.VALIDATE_FAIL);
        return responseData;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "statusCode='" + statusCode + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
