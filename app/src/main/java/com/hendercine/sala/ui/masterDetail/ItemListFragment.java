/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/15/18 1:27 PM
 */

package com.hendercine.sala.ui.masterDetail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendercine.sala.R;
import com.hendercine.sala.ui.masterDetail.dummy.DummyContent;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment {
    private RecyclerView mRecycleView;
    private SimpleItemRecyclerViewAdapter mSimpleItemRecyclerViewAdapter;
    public ItemListFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        mRecycleView = view.findViewById(R.id.item_list);
        mSimpleItemRecyclerViewAdapter = new SimpleItemRecyclerViewAdapter(getActivity(), DummyContent.ITEMS);
        mRecycleView.setAdapter(mSimpleItemRecyclerViewAdapter);
        return view;
    }
}
