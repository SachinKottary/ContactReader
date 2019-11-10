package com.techtreeit.sample.techtree.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.net.URI;

public class ContactDetails implements Parcelable{

    private String mContactName;
    private String mContactNumber;
    private String mEmail;
    private String mLandLine;
    private String mContactPic;

    public ContactDetails() {

    }

    protected ContactDetails(Parcel in) {
    }

    public static final Creator<ContactDetails> CREATOR = new Creator<ContactDetails>() {
        @Override
        public ContactDetails createFromParcel(Parcel in) {
            return new ContactDetails(in);
        }

        @Override
        public ContactDetails[] newArray(int size) {
            return new ContactDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public String getContactName() {
        return mContactName;
    }

    public void setContactName(String mContactName) {
        this.mContactName = mContactName;
    }

    public String getContactNumer() {
        return mContactNumber;
    }

    public void setContactNumer(String mContactNumer) {
        this.mContactNumber = mContactNumer;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getLandLine() {
        return mLandLine;
    }

    public void setLandLine(String mLandLine) {
        this.mLandLine = mLandLine;
    }

    public String getContactPic() {
        return mContactPic;
    }

    public void setContactPic(String mContactPic) {
        this.mContactPic = mContactPic;
    }
}
