package com.example.dagger2example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticker
{
    @SerializedName("last_price") @Expose
    private float lastPrice;

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public float getLastPrice() {
        return lastPrice;
    }
}
