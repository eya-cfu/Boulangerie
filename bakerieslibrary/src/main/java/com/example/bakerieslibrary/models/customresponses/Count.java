package com.example.bakerieslibrary.models.customresponses;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Count {

    @SerializedName("count")
    private Integer count = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    @NonNull
    public String toString() {
        return "count=" + count ;
    }
}
