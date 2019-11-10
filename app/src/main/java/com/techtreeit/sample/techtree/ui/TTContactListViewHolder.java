package com.techtreeit.sample.techtree.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techtreeit.sample.techtree.R;
import com.techtreeit.sample.techtree.TTApplication;
import com.techtreeit.sample.techtree.interfaces.TTEvents;
import com.techtreeit.sample.techtree.model.ContactDetails;
import com.techtreeit.sample.techtree.rx.RxBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TTContactListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.contact_name)
    public TextView mContactName;
    @BindView(R.id.contact_number)
    public TextView mContactNumber;
    @BindView(R.id.contact_email)
    public TextView mEmail;
    @BindView(R.id.root_container)
    public CardView mRootContainer;
    @Inject
    public RxBus mRxBus;


    public TTContactListViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        ((TTApplication) itemView.getContext().getApplicationContext()).getAppComponent().inject(this);
    }

    public void onBindData(final ContactDetails contactDetails) {
        if (contactDetails != null) {
            mContactName.setText(contactDetails.getContactName());
            mContactNumber.setText(contactDetails.getContactNumer());
            mEmail.setText(TextUtils.isEmpty(contactDetails.getEmail()) ?
            "NA" : contactDetails.getEmail());//should move 'NA' to string file
            mRootContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TTEvents<ContactDetails> events = new TTEvents<>();
                    events.setFlag(TTEvents.EVENT_CONTACT_LIST_SELECTED);
                    events.setData(contactDetails);
                    mRxBus.send(events);
                }
            });
        }
    }

}
