package com.techtreeit.sample.techtree.interfaces;

import android.provider.ContactsContract;

public interface ProfileQuerry {
    String[] PROJECTION = {
            ContactsContract.PhoneLookup.DISPLAY_NAME_PRIMARY,
            ContactsContract.CommonDataKinds.Email.ADDRESS,
    };

    int ADDRESS = 0;
    int IS_PRIMARY = 1;
}
