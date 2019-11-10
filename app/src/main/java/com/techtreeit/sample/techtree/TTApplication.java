package com.techtreeit.sample.techtree;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.techtreeit.sample.techtree.dagger.DaggerTTAppComponent;
import com.techtreeit.sample.techtree.dagger.TTAppComponent;
import com.techtreeit.sample.techtree.dagger.TTAppModule;

public class TTApplication extends Application {

    private TTAppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerTTAppComponent.builder().
                tTAppModule(new TTAppModule(this))
        .build();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public TTAppComponent getAppComponent() {
        return mAppComponent;
    }


}
