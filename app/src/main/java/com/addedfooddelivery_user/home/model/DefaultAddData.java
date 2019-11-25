package com.addedfooddelivery_user.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefaultAddData implements Parcelable {

    @SerializedName("customerAddressID")
    @Expose
    private Integer customerAddressID;
    @SerializedName("customerID")
    @Expose
    private Integer customerID;
    @SerializedName("adderessType")
    @Expose
    private String adderessType;
    @SerializedName("adderessCity")
    @Expose
    private String adderessCity;
    @SerializedName("adderess")
    @Expose
    private String adderess;
    @SerializedName("adderessLatitude")
    @Expose
    private String adderessLatitude;
    @SerializedName("adderessLongitude")
    @Expose
    private String adderessLongitude;
    @SerializedName("defaultType")
    @Expose
    private String defaultType;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    public final static Parcelable.Creator<DefaultAddData> CREATOR = new Creator<DefaultAddData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DefaultAddData createFromParcel(Parcel in) {
            return new DefaultAddData(in);
        }

        public DefaultAddData[] newArray(int size) {
            return (new DefaultAddData[size]);
        }

    };

    protected DefaultAddData(Parcel in) {
        this.customerAddressID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.customerID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.adderessType = ((String) in.readValue((String.class.getClassLoader())));
        this.adderessCity = ((String) in.readValue((String.class.getClassLoader())));
        this.adderess = ((String) in.readValue((String.class.getClassLoader())));
        this.adderessLatitude = ((String) in.readValue((String.class.getClassLoader())));
        this.adderessLongitude = ((String) in.readValue((String.class.getClassLoader())));
        this.defaultType = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public DefaultAddData() {
    }

    /**
     * @param adderessCity
     * @param customerAddressID
     * @param adderessLongitude
     * @param createdAt
     * @param adderessLatitude
     * @param customerID
     * @param adderessType
     * @param adderess
     * @param defaultType
     * @param status
     * @param updatedAt
     */
    public DefaultAddData(Integer customerAddressID, Integer customerID, String adderessType, String adderessCity, String adderess, String adderessLatitude, String adderessLongitude, String defaultType, String status, String createdAt, String updatedAt) {
        super();
        this.customerAddressID = customerAddressID;
        this.customerID = customerID;
        this.adderessType = adderessType;
        this.adderessCity = adderessCity;
        this.adderess = adderess;
        this.adderessLatitude = adderessLatitude;
        this.adderessLongitude = adderessLongitude;
        this.defaultType = defaultType;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getCustomerAddressID() {
        return customerAddressID;
    }

    public void setCustomerAddressID(Integer customerAddressID) {
        this.customerAddressID = customerAddressID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getAdderessType() {
        return adderessType;
    }

    public void setAdderessType(String adderessType) {
        this.adderessType = adderessType;
    }

    public String getAdderessCity() {
        return adderessCity;
    }

    public void setAdderessCity(String adderessCity) {
        this.adderessCity = adderessCity;
    }

    public String getAdderess() {
        return adderess;
    }

    public void setAdderess(String adderess) {
        this.adderess = adderess;
    }

    public String getAdderessLatitude() {
        return adderessLatitude;
    }

    public void setAdderessLatitude(String adderessLatitude) {
        this.adderessLatitude = adderessLatitude;
    }

    public String getAdderessLongitude() {
        return adderessLongitude;
    }

    public void setAdderessLongitude(String adderessLongitude) {
        this.adderessLongitude = adderessLongitude;
    }

    public String getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(String defaultType) {
        this.defaultType = defaultType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(customerAddressID);
        dest.writeValue(customerID);
        dest.writeValue(adderessType);
        dest.writeValue(adderessCity);
        dest.writeValue(adderess);
        dest.writeValue(adderessLatitude);
        dest.writeValue(adderessLongitude);
        dest.writeValue(defaultType);
        dest.writeValue(status);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    public int describeContents() {
        return 0;
    }

}