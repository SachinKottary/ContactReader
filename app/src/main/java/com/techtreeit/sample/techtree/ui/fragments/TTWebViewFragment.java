/*
 *
 *  *
 *  *  *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  *  *  * @Project:
 *  *  *  *		 VOOT
 *  *  *  * @Copyright:
 *  *  *  *     		Copyright © 2017, Viacom18 Media Private Limited. All Rights Reserved *
 *  *  *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  *
 *  *
 *
 */

package com.techtreeit.sample.techtree.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.techtreeit.sample.techtree.R;
import com.techtreeit.sample.techtree.utils.TTConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TTWebViewFragment extends TTBaseFragment {

    public static final String DEFAULT_URL = "https://www.google.com";
    private Unbinder unBinder;

    @BindView(R.id.lyt_browser)
    WebView mWebView;

    @BindView(R.id.browser_progress)
    ProgressBar mProgressBar;
    private String mWebUrl;

    private WebViewClient mWebViewClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (mProgressBar != null) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (mProgressBar != null) {
                mProgressBar.setVisibility(View.GONE);
            }
            super.onPageFinished(view, url);
        }
    };


    public TTWebViewFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            mWebUrl = getArguments().getString(TTConstant.KEY_LINK);
        }

    }

    @Override
    public boolean handleNetworkState() {
        return true;
    }

    @Override
    public void onNetworkDisConnected() {
        if (mWebView != null)
            mWebView.onPause();
    }

    public void onNetworkConnected() {
        if (mWebView != null)
            mWebView.onResume();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browser, container, false);
        unBinder = ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initUI() {
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        mWebView.requestFocus(View.FOCUS_DOWN);

        mWebView.loadUrl(!TextUtils.isEmpty(mWebUrl) ? mWebUrl : DEFAULT_URL);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mWebView != null) {
            mWebView.onResume();
        }
    }


    @Override
    public void onDestroy() {
        if (unBinder != null) {
            unBinder.unbind();
            unBinder = null;
        }
        if (mWebView != null) {
            mWebView.destroy();
        }

        super.onDestroy();
    }


    @Override
    public void onPause() {
        super.onPause();

        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    public boolean OnBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        } else if (mWebView != null) {
            mWebView.onPause();
        }

        return false;
    }


}
