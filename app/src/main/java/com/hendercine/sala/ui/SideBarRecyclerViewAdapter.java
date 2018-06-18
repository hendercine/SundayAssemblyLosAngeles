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
import android.widget.TextView;

import com.hendercine.sala.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * sala created by hendercine on 6/15/18.
 */
public class SideBarRecyclerViewAdapter extends RecyclerView
        .Adapter<SideBarRecyclerViewAdapter.SideBarViewHolder> {

    private String[] mSideBarArray;
    private OnItemClickListener mClickListener;
    private int focusedItem = RecyclerView.NO_POSITION;

    public SideBarRecyclerViewAdapter(String[] sideBarArray) {
        this.mSideBarArray = sideBarArray;
    }

    @NonNull
    @Override
    public SideBarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .list_item_side_bar, parent, false);
        return new SideBarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SideBarViewHolder holder, final int position) {
        holder.itemView.setSelected(focusedItem == position);
        holder.mSideBarListTextView.setText(mSideBarArray[position]);
        holder.mSideBarListTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSideBarArray.length;
    }

    public long getItemId(int position) {
        return position;
    }

    class SideBarViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        @BindView(R.id.side_bar_item_text_view)
        TextView mSideBarListTextView;

        SideBarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            notifyItemChanged(focusedItem);
            focusedItem = getLayoutPosition();
            notifyItemChanged(focusedItem);
        }
    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

    }
}
