/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/19/18 9:57 AM
 */

package com.hendercine.sala.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * sala created by hendercine on 6/19/18.
 */
public class EventProgramRVAdapter extends RecyclerView.Adapter<EventProgramRVAdapter.ProgramViewHolder> {
    @NonNull
    @Override
    public EventProgramRVAdapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EventProgramRVAdapter.ProgramViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        public ProgramViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
