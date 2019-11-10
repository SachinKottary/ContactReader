package com.techtreeit.sample.techtree.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.techtreeit.sample.techtree.R;
import com.techtreeit.sample.techtree.interfaces.TTEvents;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TTLoginFragment extends TTBaseFragment {

    private static final String EMAIL = "email";

    @BindView(R.id.login_button)
    public LoginButton mFBLoginButton;
    private CallbackManager mCallBackManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCallBackManager = CallbackManager.Factory.create();
        mFBLoginButton.setFragment(this);
        mFBLoginButton.setReadPermissions(Arrays.asList(EMAIL));
        mFBLoginButton.registerCallback(mCallBackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (loginResult.getAccessToken() != null) {
                    TTEvents events = new TTEvents();
                    events.setFlag(TTEvents.EVENT_FACEBOOK_LOGIN_SUCCESS);
                    mRxBus.send(events);//Notify that login is success
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(), "Login Failed!", Toast.LENGTH_SHORT).show();
                //Generally show a dialog when login is failed
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getActivity(), "Login Failed!", Toast.LENGTH_SHORT).show();
                //Generally show a dialog when login is failed
            }
        });
    }
    @OnClick(R.id.guest_login)
    public void onGuestLogin() {
        TTEvents events = new TTEvents();
        events.setFlag(TTEvents.EVENT_GUEST_LOGIN);
        mRxBus.send(events);//Notify for guest login
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallBackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
