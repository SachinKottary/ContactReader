package com.techtreeit.sample.techtree.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techtreeit.sample.techtree.R;
import com.techtreeit.sample.techtree.model.ContactDetails;
import com.techtreeit.sample.techtree.ui.adapter.TTContactListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TTContactListFragment extends TTBaseFragment {

    public static final String ARGUMENTS_CONTACT_LIST = "contact_list";

    @BindView(R.id.recycler_view)
    public RecyclerView mRecyclerView;
    private Unbinder mUnBinder;
    private ArrayList<ContactDetails> mDetailList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            mDetailList = getArguments().getParcelableArrayList(ARGUMENTS_CONTACT_LIST);
        }
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        mUnBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mDetailList != null && mDetailList.size() > 0) {
            TTContactListAdapter adapter = new TTContactListAdapter(mDetailList);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setAdapter(adapter);
        } else {
            //Show emp[ty view
        }
    }

    @Override
    public boolean handleNetworkState() {
        return false;
    }

    @Override
    public void onNetworkDisConnected() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }
}
