package com.addedfooddelivery_user.loginEmail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetail implements Parcelable
{

    @SerializedName("customerID")
    @Expose
    private Integer customerID;
    @SerializedName("loginType")
    @Expose
    private String loginType;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("isVerify")
    @Expose
    private Integer isVerify;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    public final static Parcelable.Creator<UserDetail> CREATOR = new Creator<UserDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserDetail createFromParcel(Parcel in) {
            return new UserDetail(in);
        }

        public UserDetail[] newArray(int size) {
            return (new UserDetail[size]);
        }

    }
            ;

    protected UserDetail(Parcel in) {
        this.customerID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.loginType = ((String) in.readValue((String.class.getClassLoader())));
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.phoneNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.isVerify = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.accessToken = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public UserDetail() {
    }

    /**
     *
     * @param phoneNumber
     * @param loginType
     * @param customerID
     * @param userName
     * @param accessToken
     * @param isVerify
     * @param email
     */
    public UserDetail(Integer customerID, String loginType, String userName, String phoneNumber, Integer isVerify, String email, String accessToken) {
        super();
        this.customerID = customerID;
        this.loginType = loginType;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.isVerify = isVerify;
        this.email = email;
        this.accessToken = accessToken;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(customerID);
        dest.writeValue(loginType);
        dest.writeValue(userName);
        dest.writeValue(phoneNumber);
        dest.writeValue(isVerify);
        dest.writeValue(email);
        dest.writeValue(accessToken);
    }

    public int describeContents() {
        return 0;
    }

}