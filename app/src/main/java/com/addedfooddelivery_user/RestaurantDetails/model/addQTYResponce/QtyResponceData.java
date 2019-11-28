package com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QtyResponceData implements Parcelable
{

@SerializedName("billing_detail")
@Expose
private BillingDetail billingDetail;
public final static Parcelable.Creator<QtyResponceData> CREATOR = new Creator<QtyResponceData>() {


@SuppressWarnings({
"unchecked"
})
public QtyResponceData createFromParcel(Parcel in) {
return new QtyResponceData(in);
}

public QtyResponceData[] newArray(int size) {
return (new QtyResponceData[size]);
}

}
;

protected QtyResponceData(Parcel in) {
this.billingDetail = ((BillingDetail) in.readValue((BillingDetail.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public QtyResponceData() {
}

/**
*
* @param billingDetail
*/
public QtyResponceData(BillingDetail billingDetail) {
super();
this.billingDetail = billingDetail;
}

public BillingDetail getBillingDetail() {
return billingDetail;
}

public void setBillingDetail(BillingDetail billingDetail) {
this.billingDetail = billingDetail;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(billingDetail);
}

public int describeContents() {
return 0;
}

}