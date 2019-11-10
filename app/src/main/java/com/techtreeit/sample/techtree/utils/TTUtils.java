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

package com.techtreeit.sample.techtree.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import static android.content.Context.INPUT_METHOD_SERVICE;

@SuppressWarnings("JavaDoc,unused")
public class TTUtils {

    private static final String TAG = "TTUtils";
    private static final int NO_NETWORK = -1;


    /**
     * returns true if device is in portrait mode
     *
     * @param activity
     * @return
     */
    public static boolean isInLandscape(Context activity) {
        if (activity != null) {
            int orientation = activity.getResources().getConfiguration().orientation;
            return orientation != Configuration.ORIENTATION_PORTRAIT;
        }
        return false;
    }

    public static void hideKeyboard(View view, Context context) {
        if (view != null && context != null) {
            try {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * shows the keyboard
     *
     * @param activity
     */
    public static void showKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }


    /**
     * To show keyboard
     *
     * @param context
     * @param view
     */
    public static void showKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }


    /**
     * checks if internet is On
     *
     * @param context
     * @return boolean
     */
    public static boolean isInternetOn(Context context) {
        try {
            ConnectivityManager conn = (ConnectivityManager) context.getApplicationContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = conn.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isConnected();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * shows toast
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
