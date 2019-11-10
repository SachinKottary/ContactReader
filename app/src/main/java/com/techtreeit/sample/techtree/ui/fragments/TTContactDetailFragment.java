package com.techtreeit.sample.techtree.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techtreeit.sample.techtree.R;
import com.techtreeit.sample.techtree.model.ContactDetails;
import com.techtreeit.sample.techtree.utils.TTImageLoaderUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TTContactDetailFragment extends TTBaseFragment {

    public static final String ARGUMENT_CONTACT_DETAIL = "contact_detail";

    @BindView(R.id.contact_name)
    public TextView mContactName;
    @BindView(R.id.contact_number)
    public TextView mContactNumber;
    @BindView(R.id.contact_email)
    public TextView mEmail;
    @BindView(R.id.contact_pic)
    public ImageView mContactImageView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            updateContactDetails(getArguments());
        }
    }

    private void updateContactDetails(Bundle arguments) {
        ContactDetails contactDetails = arguments.getParcelable(ARGUMENT_CONTACT_DETAIL);
        if (contactDetails != null) {
            mContactName.setText(contactDetails.getContactName());
            mContactNumber.setText(contactDetails.getContactNumer());
            mEmail.setText(TextUtils.isEmpty(contactDetails.getEmail()) ?
                    "NA" : contactDetails.getEmail());//should move 'NA' to string file
            mContactImageView.setVisibility(View.VISIBLE);
            if (contactDetails.getContactPic() != null) {
                TTImageLoaderUtils.setThumbnailImage(mContactImageView, contactDetails.getContactPic(), R.drawable.com_facebook_profile_picture_blank_square);
            } else {
                mContactImageView.setBackground(getResources().getDrawable(R.drawable.com_facebook_profile_picture_blank_square));
            }
        }
    }

    @Override
    public boolean handleNetworkState() {
        return false;
    }

    @Override
    public void onNetworkDisConnected() {

    }
}
