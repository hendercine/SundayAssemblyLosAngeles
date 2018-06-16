/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/15/18 5:06 PM
 */

package com.hendercine.sala.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.hendercine.sala.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * sala created by hendercine on 6/15/18.
 */
public class DrawerRecyclerViewAdapter extends RecyclerView
        .Adapter<DrawerRecyclerViewAdapter.DrawerViewHolder> {

    private String[] mTitleArray;
    private AdapterView.OnItemClickListener mClickListener;
    private int focusedItem = RecyclerView.NO_POSITION;

    public DrawerRecyclerViewAdapter(String[] titleArray) {
        this.mTitleArray = titleArray;
    }

    @NonNull
    @Override
    public DrawerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .list_item_drawer, parent, false);
        return new DrawerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerViewHolder holder, int position) {
        holder.itemView.setSelected(focusedItem == position);
        holder.mDrawerListTextView.setText(mTitleArray[position]);

    }

    @Override
    public int getItemCount() {
        return mTitleArray.length;
    }

    class DrawerViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        @BindView(R.id.drawer_item_text_view)
        TextView mDrawerListTextView;

        DrawerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
