package com.example.postget.Model;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {

    @SerializedName("status")
    boolean statusString;
    @SerializedName("message")
    String messageString;

    public boolean isStatusString() {
        return statusString;
    }

    public String getMessageString() {
        return messageString;
    }
}
