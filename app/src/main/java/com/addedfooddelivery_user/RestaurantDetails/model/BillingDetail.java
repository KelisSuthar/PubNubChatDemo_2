package com.addedfooddelivery_user.RestaurantDetails.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillingDetail implements Parcelable
{

@SerializedName("Total")
@Expose
private Integer total;
@SerializedName("itemTotle")
@Expose
private String itemTotle;
@SerializedName("tax")
@Expose
private String tax;
@SerializedName("deliveryfee")
@Expose
private String deliveryfee;
@SerializedName("to_pay")
@Expose
private Integer toPay;
public final static Parcelable.Creator<BillingDetail> CREATOR = new Creator<BillingDetail>() {


@SuppressWarnings({
"unchecked"
})
public BillingDetail createFromParcel(Parcel in) {
return new BillingDetail(in);
}

public BillingDetail[] newArray(int size) {
return (new BillingDetail[size]);
}

}
;

protected BillingDetail(Parcel in) {
this.total = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.itemTotle = ((String) in.readValue((String.class.getClassLoader())));
this.tax = ((String) in.readValue((String.class.getClassLoader())));
this.deliveryfee = ((String) in.readValue((String.class.getClassLoader())));
this.toPay = ((Integer) in.readValue((Integer.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public BillingDetail() {
}

/**
*
* @param total
* @param deliveryfee
* @param tax
* @param toPay
* @param itemTotle
*/
public BillingDetail(Integer total, String itemTotle, String tax, String deliveryfee, Integer toPay) {
super();
this.total = total;
this.itemTotle = itemTotle;
this.tax = tax;
this.deliveryfee = deliveryfee;
this.toPay = toPay;
}

public Integer getTotal() {
return total;
}

public void setTotal(Integer total) {
this.total = total;
}

public String getItemTotle() {
return itemTotle;
}

public void setItemTotle(String itemTotle) {
this.itemTotle = itemTotle;
}

public String getTax() {
return tax;
}

public void setTax(String tax) {
this.tax = tax;
}

public String getDeliveryfee() {
return deliveryfee;
}

public void setDeliveryfee(String deliveryfee) {
this.deliveryfee = deliveryfee;
}

public Integer getToPay() {
return toPay;
}

public void setToPay(Integer toPay) {
this.toPay = toPay;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(total);
dest.writeValue(itemTotle);
dest.writeValue(tax);
dest.writeValue(deliveryfee);
dest.writeValue(toPay);
}

public int describeContents() {
return 0;
}

}