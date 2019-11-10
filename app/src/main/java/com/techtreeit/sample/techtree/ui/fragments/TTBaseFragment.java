package com.techtreeit.sample.techtree.ui.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.techtreeit.sample.techtree.TTApplication;
import com.techtreeit.sample.techtree.interfaces.TTFragmentInteractionListener;
import com.techtreeit.sample.techtree.rx.RxBus;
import com.techtreeit.sample.techtree.utils.TTUtils;

import javax.inject.Inject;

public abstract class TTBaseFragment extends Fragment {
    @Inject
    public RxBus mRxBus;
    private BroadcastReceiver mNetworkChangeReceiver;
    protected TTFragmentInteractionListener mRSFragmentInteractionListener;
    private boolean onResume = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TTApplication) getActivity().getApplicationContext()).getAppComponent().inject(this);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNetworkChangeReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //do something based on the intent's action
            }
        };
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mRSFragmentInteractionListener = (TTFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement TTFragmentInteractionListener ");
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        onResume = false;
        getActivity().unregisterReceiver(mNetworkChangeReceiver);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (TTUtils.isInternetOn(getContext())) {
            hideNoNetworkDialog();
        } else {
            if (handleNetworkState() && !onResume) {
                    showNoNetworkDialog();
            } else {
                onNetworkDisConnected();
            }
        }
        getActivity().registerReceiver(mNetworkChangeReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION));

    }

    public abstract boolean handleNetworkState();
    public abstract void onNetworkDisConnected();

    public void showNoNetworkDialog()  {

    }

    public void hideNoNetworkDialog() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mRSFragmentInteractionListener != null)
            mRSFragmentInteractionListener = null;
    }
}
