package com.techtreeit.sample.techtree.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techtreeit.sample.techtree.R;
import com.techtreeit.sample.techtree.model.ContactDetails;
import com.techtreeit.sample.techtree.ui.TTContactListViewHolder;

import java.util.ArrayList;

public class TTContactListAdapter extends RecyclerView.Adapter<TTContactListViewHolder> {

    private ArrayList<ContactDetails> mContactDetailList;

    public TTContactListAdapter(ArrayList<ContactDetails> contactList) {
        mContactDetailList = contactList;
    }

    @NonNull
    @Override
    public TTContactListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_list_item, viewGroup, false);
        return new TTContactListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TTContactListViewHolder ttContactListViewHolder, int position) {
        ttContactListViewHolder.onBindData(mContactDetailList.get(position));
    }

    @Override
    public int getItemCount() {
        return mContactDetailList.size();
    }

}
