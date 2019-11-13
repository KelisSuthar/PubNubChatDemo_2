package com.addedfooddelivery_user.common.model;

import java.util.ArrayList;
import java.util.List;

public class AddressList {

    String header;
    List<Address> addresses = new ArrayList<>();

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}