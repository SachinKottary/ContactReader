package com.techtreeit.sample.techtree.loader;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.techtreeit.sample.techtree.interfaces.TTEvents;
import com.techtreeit.sample.techtree.model.ContactDetails;
import com.techtreeit.sample.techtree.rx.RxBus;

import java.util.ArrayList;

/**
 * Used for loading contact details
 */
public class TTContactDetailLoader implements LoaderManager.LoaderCallbacks<Cursor>  {
    private RxBus mRxBus;
    private Context mContext;

    public TTContactDetailLoader(Context context, RxBus rxBus) {
        mRxBus = rxBus;
        mContext = context;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new CursorLoader(mContext,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        ArrayList<ContactDetails> contactDetailList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ContactDetails details = new ContactDetails();
            details.setContactName(cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME_PRIMARY)));
            details.setContactNumer(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS)));
            details.setContactPic(cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.PHOTO_URI)));
            contactDetailList.add(details);
            cursor.moveToNext();
            //content://com.android.contacts/contacts/2299/photo - PHOTO_THUMBNAIL_URI
        }//content://com.android.contacts/contacts/2299 - High res ContactsContract.Contacts.CONTENT_URI
        cursor.close();
        TTEvents<ArrayList<ContactDetails>> ttEvents = new TTEvents<>();
        ttEvents.setFlag(TTEvents.EVENT_CONTACT_LIST_LOADED);
        ttEvents.setData(contactDetailList);
        mRxBus.send(ttEvents);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
