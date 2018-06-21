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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hendercine.sala.R;
import com.hendercine.sala.models.Assembly;
import com.hendercine.sala.models.Performer;
import com.hendercine.sala.models.Song;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * sala created by hendercine on 6/19/18.
 */
public class EventProgramRVAdapter extends RecyclerView.Adapter<EventProgramRVAdapter.ProgramViewHolder> {

    private ArrayList<Assembly> mAssemblies;
    private ArrayList<Performer> mPerformers;
    private ArrayList<Song> mSongs;
    private Performer mPerformer;
    private Song mSong;

    public EventProgramRVAdapter(ArrayList<Assembly> assemblies, ArrayList<Performer> performers, ArrayList<Song> songs) {
        mAssemblies = assemblies;
        mPerformers = performers;
        mSongs = songs;
    }

    @NonNull
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_program, parent, false);
        return new ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramViewHolder holder, int position) {
//        mAssemblies = new ArrayList<>();
//        final Assembly assembly = mAssemblies.get(position);
//        if (assembly != null) {
//            ArrayList<Performer> latestPerformers = new ArrayList<>();
//            mPerformers = new ArrayList<>();
//            mSongs = new ArrayList<>();
//            holder.segmentTitleView.setText(mPerformer.getPerformanceTitle());
//            holder.segmentCreatorView.setText(mPerformer.getPerformerName());
//            holder.segmentCategoryView.setText(mPerformer.getPerformanceCategory());
//        }

    }

    private Assembly getItem(int position) {
        if (position < 0 || position >= mAssemblies.size()) {
            return null;
        } else {
            return mAssemblies.get(position);
        }

    }

    @Override
    public int getItemCount() {
        return mAssemblies.size();
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        @BindView(R.id.segment_category_text_view)
        TextView segmentCategoryView;
        @BindView(R.id.segment_creator_text_view)
        TextView segmentCreatorView;
        @BindView(R.id.segment_title_text_view)
        TextView segmentTitleView;

        public ProgramViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
