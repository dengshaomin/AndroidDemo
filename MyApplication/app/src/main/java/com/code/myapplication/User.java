package com.code.myapplication;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/**
 * Created by dengshaomin on 2018/4/13.
 */

public class User implements android.os.Parcelable, Serializable {

    private static final long serialVersionUID = 8829975621220483374L;

    private String name;

    private String id;

    private String score;

    private String male;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setMale(String male) {
        this.male = male;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeString(this.score);
        dest.writeString(this.male);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.score = in.readString();
        this.male = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
