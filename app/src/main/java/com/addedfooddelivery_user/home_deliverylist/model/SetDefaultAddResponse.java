package com.addedfooddelivery_user.home_deliverylist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SetDefaultAddResponse implements Parcelable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<Object> data = new ArrayList<Object>();
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<SetDefaultAddResponse> CREATOR = new Creator<SetDefaultAddResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SetDefaultAddResponse createFromParcel(Parcel in) {
            return new SetDefaultAddResponse(in);
        }

        public SetDefaultAddResponse[] newArray(int size) {
            return (new SetDefaultAddResponse[size]);
        }

    };

    protected SetDefaultAddResponse(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.data, (java.lang.Object.class.getClassLoader()));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public SetDefaultAddResponse() {
    }

    /**
     * @param data
     * @param message
     * @param status
     */
    public SetDefaultAddResponse(Integer status, List<Object> data, String message) {
        super();
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeList(data);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}