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

package com.techtreeit.sample.techtree.interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public interface TTFragmentInteractionListener {
    int FRAG_ADD = 1;
    int FRAG_REPLACE = 2;
    int FRAG_ADD_ANIMATE = 3;
    int FRAG_DIALOG = 4;
    int FRAG_REPLACE_WITH_STACK = 5;
    int FRAG_ADD_WITH_STACK = 6;

    void setCurrentFragment(Bundle bundle, int fragmentType, int transType, int frameId);

    void popTopFragment();

    void popAllFromStack();

    void addContentFragWithAnim(Fragment frag, String tag);

    Fragment getFragmentByType(int fragmentType);

    String getActiveFragmentTag();


}
