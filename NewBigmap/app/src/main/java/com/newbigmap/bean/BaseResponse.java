package com.newbigmap.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private int status;
    public T data;
    public int errorCode;
    public String message;

    public boolean isSuccess() {
        return status == 1;
    }
}
