package com.jayklef.acada.entity;

import com.google.gson.annotations.SerializedName;

public class CreatePayment {

    @SerializedName("items")
    Object[] items;

    public Object[] getItems() {
        return items;
    }
}
