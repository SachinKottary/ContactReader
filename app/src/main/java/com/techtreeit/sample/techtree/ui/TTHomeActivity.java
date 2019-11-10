package com.techtreeit.sample.techtree.ui;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.techtreeit.sample.techtree.R;
import com.techtreeit.sample.techtree.interfaces.TTEvents;
import com.techtreeit.sample.techtree.loader.TTContactDetailLoader;
import com.techtreeit.sample.techtree.model.ContactDetails;
import com.techtreeit.sample.techtree.ui.fragments.TTContactDetailFragment;
import com.techtreeit.sample.techtree.ui.fragments.TTContactListFragment;
import com.techtreeit.sample.techtree.ui.fragments.TTWebViewFragment;
import com.techtreeit.sample.techtree.utils.TTConstant;
import com.techtreeit.sample.techtree.utils.TTFragmentConstants;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import static android.Manifest.permission.READ_CONTACTS;

public class TTHomeActivity extends TTBaseActivity {
    private static final int REQUEST_READ_CONTACTS = 0;
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            loadHomeDashBoardFragment();
        } else {
            initLoginFragment();
        }
        mCompositeDisposable = new CompositeDisposable();
        Observable<Object> clickObserver = mRxBus.getBus().share();
        mCompositeDisposable.add(clickObserver.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object data) {
                if (data instanceof TTEvents) {//Check for all event types from across app
                    TTEvents<Object> events = (TTEvents<Object>) data;
                    if (events.getFlag().equals(TTEvents.EVENT_CONTACT_LIST_SELECTED)) {
                        loadContactDetailFragment((ContactDetails) events.getData());
                    } else if (events.getFlag().equals(TTEvents.EVENT_DASHBOARD_CONTACT_SELECTED)) {
                        initLoaderForContactFetching();
                    } else if (events.getFlag().equals(TTEvents.EVENT_DASHBOARD_WEBPAGE_SELECTED)) {
                        loadWebpageFragment(FRAG_REPLACE_WITH_STACK);
                    } else if (events.getFlag().equals(TTEvents.EVENT_CONTACT_LIST_LOADED)) {
                        ArrayList<ContactDetails> contactDetailList = (ArrayList<ContactDetails>) events.getData();
                        loadContactListFragment(contactDetailList);
                    } else if (events.getFlag().equals(TTEvents.EVENT_FACEBOOK_LOGIN_SUCCESS) ||
                            events.getFlag().equals(TTEvents.EVENT_GUEST_LOGIN)) {
                        popAllFromStack();
                        loadHomeDashBoardFragment();
                    }
                }

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                Toast.makeText(TTHomeActivity.this, "Error Occurred: " + throwable.toString() + "", Toast.LENGTH_LONG).show();
            }
        }));

    }

    private void initLoginFragment() {
        setCurrentFragment(null, TTFragmentConstants.FRAGMENT_LOGIN, FRAG_REPLACE, R.id.contentPanel);
    }

    private void loadHomeDashBoardFragment() {
        setCurrentFragment(null, TTFragmentConstants.FRAGMENT_DASHBOARD, FRAG_REPLACE, R.id.contentPanel);
    }

    private void loadContactListFragment(ArrayList<ContactDetails> contactDetailList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(TTContactListFragment.ARGUMENTS_CONTACT_LIST, contactDetailList);
        setCurrentFragment(bundle, TTFragmentConstants.FRAGMENT_CONTACT_LIST, FRAG_REPLACE_WITH_STACK, R.id.contentPanel);
    }

    private void loadWebpageFragment(int trasasctionType) {
        setCurrentFragment(null, TTFragmentConstants.FRAGMENT_WEBPAGE, trasasctionType, R.id.contentPanel);
    }

    private void loadContactDetailFragment(ContactDetails data) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(TTContactDetailFragment.ARGUMENT_CONTACT_DETAIL, data);
        setCurrentFragment(bundle, TTFragmentConstants.FRAGMENT_CONTACT_DETAIL, FRAG_REPLACE_WITH_STACK, R.id.contentPanel);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(findViewById(R.id.root_container), R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    }).show();

        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    private void initLoaderForContactFetching() {
        if (!mayRequestContacts()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(TTConstant.LOADER_TYPE, TTConstant.CONTACT_LOADER);
        getLoaderManager().initLoader(0, bundle, new TTContactDetailLoader(this, mRxBus));
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initLoaderForContactFetching();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    @Override
    public void onBackPressed() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contentPanel);
        if (fragment instanceof TTWebViewFragment) {
            if (!(((TTWebViewFragment) fragment).OnBackPressed())) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }
}
