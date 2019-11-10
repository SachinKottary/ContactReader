package com.techtreeit.sample.techtree.utils;

import android.support.v4.app.Fragment;

import com.techtreeit.sample.techtree.ui.fragments.TTContactDetailFragment;
import com.techtreeit.sample.techtree.ui.fragments.TTContactListFragment;
import com.techtreeit.sample.techtree.ui.fragments.TTDashBoardFragment;
import com.techtreeit.sample.techtree.ui.fragments.TTLoginFragment;
import com.techtreeit.sample.techtree.ui.fragments.TTWebViewFragment;

import static com.techtreeit.sample.techtree.utils.TTFragmentConstants.FRAGMENT_CONTACT_DETAIL;
import static com.techtreeit.sample.techtree.utils.TTFragmentConstants.FRAGMENT_CONTACT_LIST;
import static com.techtreeit.sample.techtree.utils.TTFragmentConstants.FRAGMENT_DASHBOARD;
import static com.techtreeit.sample.techtree.utils.TTFragmentConstants.FRAGMENT_LOGIN;
import static com.techtreeit.sample.techtree.utils.TTFragmentConstants.FRAGMENT_WEBPAGE;

public class TTFragmentUtils {

    public static String getFragmentTag(int type) {
        switch (type) {
            case FRAGMENT_LOGIN:
                return TTLoginFragment.class.getName();

            case FRAGMENT_DASHBOARD:
                return TTDashBoardFragment.class.getName();

            case FRAGMENT_WEBPAGE:
                return TTWebViewFragment.class.getName();

            case FRAGMENT_CONTACT_LIST:
                return TTContactListFragment.class.getName();


            case FRAGMENT_CONTACT_DETAIL:
                return TTContactDetailFragment.class.getName();
        }
        return "";
    }
}
