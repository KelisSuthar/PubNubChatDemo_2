package com.addedfooddelivery_user.common.model;

import java.util.ArrayList;
import java.util.List;

public class AddressList {

    String header;
    List<AddressCommon> addresses = new ArrayList<>();

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<AddressCommon> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressCommon> addresses) {
        this.addresses = addresses;
    }
}