package com.techtreeit.sample.techtree.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.techtreeit.sample.techtree.R;
import com.techtreeit.sample.techtree.TTApplication;
import com.techtreeit.sample.techtree.interfaces.TTFragmentInteractionListener;
import com.techtreeit.sample.techtree.rx.RxBus;
import com.techtreeit.sample.techtree.utils.TTFragmentUtils;

import javax.inject.Inject;

public class TTBaseActivity extends AppCompatActivity implements TTFragmentInteractionListener{

    @Inject
    protected RxBus mRxBus;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ((TTApplication) getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //do something based on the intent's action
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void setCurrentFragment(Bundle bundle, int fragmentType, int transType, int frameId) {
        addFragment(bundle, fragmentType, transType, frameId);
    }

    private void addFragment(Bundle bundle, int fragmentType, int transType, int frameId) {

        if (!isFinishing()) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment fragment = null;

            if (fragment == null) {
                fragment = Fragment.instantiate(this,
                        TTFragmentUtils.getFragmentTag(fragmentType), bundle);
                if (transType == FRAG_ADD) {
                    ft.add(frameId, fragment, TTFragmentUtils.getFragmentTag(fragmentType));
                } else if (transType == FRAG_REPLACE) {
                    ft.replace(frameId, fragment, TTFragmentUtils.getFragmentTag(fragmentType));
                } else if (transType == FRAG_REPLACE_WITH_STACK) {
                    ft.replace(frameId, fragment, TTFragmentUtils.getFragmentTag(fragmentType));
                    ft.addToBackStack(TTFragmentUtils.getFragmentTag(fragmentType));
                } else if (transType == FRAG_ADD_WITH_STACK) {
                    ft.add(frameId, fragment, TTFragmentUtils.getFragmentTag(fragmentType));
                    ft.addToBackStack(TTFragmentUtils.getFragmentTag(fragmentType));
                }

            } else {
                ft.attach(fragment);
            }
            ft.commitAllowingStateLoss();
            fm.executePendingTransactions();
        }
    }


    @Override
    public void popTopFragment() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStackImmediate();
    }

    @Override
    public void popAllFromStack() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        int count = fm.getBackStackEntryCount();

        for (int i = 0; i < count; i++) {

            Fragment fragment = null;
            if (getActiveFragmentTag() != null) {
                fragment = fm.findFragmentByTag(getActiveFragmentTag());
                fm.popBackStackImmediate(getActiveFragmentTag(),
                        FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            if (fragment != null)
                ft.remove(fragment);

        }


        ft.commitAllowingStateLoss();
    }

    @Override
    public void addContentFragWithAnim(Fragment frag, String tag) {

    }

    @Override
    public Fragment getFragmentByType(int fragmentType) {
        return null;
    }

    @Override
    public String getActiveFragmentTag() {
        return null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mReceiver);
    }
}
