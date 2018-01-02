package com.cheng.androidart.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 李国财 on 2018-01-02.
 */

public class Book implements Parcelable {
    private String name;
    private float price;

    public Book(String name, float price) {
        this.name = name;
        this.price = price;
    }

    protected Book(Parcel in) {
        name = in.readString();
        price = in.readFloat();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeFloat(price);
    }

    @Override
    public String toString() {
        return "name:"+name+" price:"+price;
    }
}
