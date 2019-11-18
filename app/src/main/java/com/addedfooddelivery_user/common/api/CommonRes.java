package com.addedfooddelivery_user.common.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CommonRes {

    @SerializedName("StatusCode")
    @Expose
    private int StatusCode;
    @SerializedName("StatusMessage")
    @Expose
    private String StatusMessage;
    @SerializedName("ReasonPhrase")
    @Expose
    private String ReasonPhrase;

    /**
     * @return The StatusCode
     */
    public int getStatusCode() {
        return StatusCode;
    }

    /**
     * @param StatusCode The StatusCode
     */
    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public CommonRes withStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
        return this;
    }

    /**
     * @return The StatusMessage
     */
    public String getStatusMessage() {
        return StatusMessage;
    }

    /**
     * @param StatusMessage The StatusMessage
     */
    public void setStatusMessage(String StatusMessage) {
        this.StatusMessage = StatusMessage;
    }

    public CommonRes withStatusMessage(String StatusMessage) {
        this.StatusMessage = StatusMessage;
        return this;
    }

    /**
     * @return The ReasonPhrase
     */
    public String getReasonPhrase() {
        return ReasonPhrase;
    }

    /**
     * @param ReasonPhrase The ReasonPhrase
     */
    public void setReasonPhrase(String ReasonPhrase) {
        this.ReasonPhrase = ReasonPhrase;
    }

    public CommonRes withReasonPhrase(String ReasonPhrase) {
        this.ReasonPhrase = ReasonPhrase;
        return this;
    }


}