package com.techtreeit.sample.techtree.dagger;

import android.content.Context;

import com.techtreeit.sample.techtree.TTApplication;
import com.techtreeit.sample.techtree.rx.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TTAppModule {

    private TTApplication mAppContext;

    public TTAppModule(TTApplication appContext) {
        mAppContext = appContext;
    }

    @Singleton
    @Provides
    public Context provideAppContext() {
        return mAppContext;
    }

    @Provides
    @Singleton
    public RxBus providesRxBus() {
        return new RxBus();
    }
}
