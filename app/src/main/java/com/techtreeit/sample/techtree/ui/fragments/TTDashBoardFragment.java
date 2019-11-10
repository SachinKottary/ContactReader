package com.techtreeit.sample.techtree.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.techtreeit.sample.techtree.R;
import com.techtreeit.sample.techtree.interfaces.TTEvents;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TTDashBoardFragment extends TTBaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public boolean handleNetworkState() {
        return false;
    }

    @Override
    public void onNetworkDisConnected() {

    }

    @OnClick(R.id.web)
    public void onWebPageClicked() {
        sendClickEvent(TTEvents.EVENT_DASHBOARD_WEBPAGE_SELECTED);
    }

    @OnClick(R.id.contact_detail)
    public void onContactClicked() {
        sendClickEvent(TTEvents.EVENT_DASHBOARD_CONTACT_SELECTED);
    }

    private void sendClickEvent(String eventType) {
        TTEvents events = new TTEvents();
        events.setFlag(eventType);
        mRxBus.send(events);
    }
}
