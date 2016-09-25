package com.client.maks.client;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Maks on 25.09.2016.
 */
public class Friend implements Parcelable {
    private String name;
    private int image_id;

    protected Friend(Parcel in) {
        name = in.readString();
        image_id = in.readInt();
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public Friend(String name, int image_id) {
        this.name = name;
        this.image_id = image_id;
    }

    public Friend(){}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(image_id);
    }
}
