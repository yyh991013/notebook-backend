package com.notebook.notebookbackend.response;


import java.util.HashMap;
import java.util.Map;

/**
 * @author 22454
 */
public class CommonResponse {
    private String status;
    private Object data;

    public static CommonResponse create(String key, Object data) {
        Map<String, Object> map = new HashMap<>(2);
        map.put(key, data);
        return CommonResponse.create(map);
    }

    public static CommonResponse create(Object data) {
        return CommonResponse.create(data, "success");
    }

    public static CommonResponse create(Object data, String status) {
        CommonResponse type = new CommonResponse();
        type.setStatus(status);
        type.setData(data);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static CommonResponse success() {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData("success");
        commonResponse.setStatus("success");
        return commonResponse;
    }

    public static CommonResponse failed() {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData("failed");
        commonResponse.setStatus("failed");
        return commonResponse;
    }

}

