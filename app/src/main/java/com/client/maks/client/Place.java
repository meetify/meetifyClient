package com.client.maks.client;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Egor on 07.11.2016.
 */

public class Place implements Parcelable {
    private String name;
    private byte[] image;


    protected Place(Parcel in) {
        name = in.readString();
        //image = new byte[in.readInt()];

        in.readByteArray(image);
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = new  byte[image.length];
        for(int i = 0; i < image.length;i++){

            this.image[i] = image[i];
        }

    }

    public Place(String name, byte[] image) {
        this.name = name;
        this.image = new  byte[image.length];
        for(int i = 0; i < image.length;i++){

            this.image[i] = image[i];
        }
    }

    public Place(){}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        //parcel.writeInt(image.length);

        parcel.writeByteArray(image);
    }
}
