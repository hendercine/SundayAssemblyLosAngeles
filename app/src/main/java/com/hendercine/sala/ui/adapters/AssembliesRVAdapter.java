/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/24/18 4:01 PM
 */

package com.hendercine.sala.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hendercine.sala.R;
import com.hendercine.sala.models.Assembly;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * sala created by hendercine on 6/24/18.
 */
public class AssembliesRVAdapter extends RecyclerView.Adapter<AssembliesRVAdapter.AssembliesViewHolder> {

    private ArrayList<Assembly> mAssemblyTitles;
    private ArrayList<Assembly> mAssemblyThemes;
    private ArrayList<Assembly> mAssemblyDescriptions;
    private ArrayList<Assembly> mAssemblyPhotoUrls;

    public AssembliesRVAdapter(ArrayList<Assembly> assemblyTitles, ArrayList<Assembly> assemblyThemes, ArrayList<Assembly> assemblyDescriptions, ArrayList<Assembly> assemblyPhotoUrls) {
        mAssemblyTitles = assemblyTitles;
        mAssemblyThemes = assemblyThemes;
        mAssemblyDescriptions = assemblyDescriptions;
        mAssemblyPhotoUrls = assemblyPhotoUrls;
    }

    @NonNull
    @Override
    public AssembliesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_assemblies, parent, false);
        return new AssembliesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssembliesViewHolder holder, int position) {

        Context context = holder.mAssemblyCardView.getContext();
        Assembly assemblyTitles = mAssemblyTitles.get(position);
        Assembly assemblyThemes = mAssemblyThemes.get(position);
        Assembly assemblyDescriptions = mAssemblyDescriptions.get(position);
        Assembly assemblyPhotoUrls = mAssemblyPhotoUrls.get(position);

        holder.mAssemblyDateLine.setText(assemblyTitles.getAssemblyDate());
        holder.mAssemblyThemeHeadline.setText(assemblyThemes.getAssemblyTheme());
        holder.mAssemblyDescription.setText(assemblyDescriptions.getAssemblyDescription());
        if (!assemblyPhotoUrls.getAssemblyPhotoUrl().isEmpty()) {
            Glide.with(context)
                    .load(assemblyPhotoUrls.getAssemblyPhotoUrl())
                    .into(holder.mAssemblyPic);
        } else {
            holder.mAssemblyPic.setImageResource(R.drawable.sala_logo_grass);
        }

    }

    @Override
    public int getItemCount() {
        return (mAssemblyTitles == null) ? 0 : mAssemblyTitles.size();
    }

    public void setAssembliesList(ArrayList<Assembly> assemblyTitles,
                                  ArrayList<Assembly> assemblyThemes,
                                  ArrayList<Assembly> assemblyDescriptions,
                                  ArrayList<Assembly> assemblyPhotoUrls) {
        mAssemblyTitles.clear();
        mAssemblyTitles.addAll(assemblyTitles);
        notifyDataSetChanged();
        mAssemblyThemes.clear();
        mAssemblyThemes.addAll(assemblyThemes);
        notifyDataSetChanged();
        mAssemblyDescriptions.clear();
        mAssemblyDescriptions.addAll(assemblyDescriptions);
        notifyDataSetChanged();
        mAssemblyPhotoUrls.clear();
        mAssemblyPhotoUrls.addAll(assemblyPhotoUrls);
        notifyDataSetChanged();
    }


    class AssembliesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.assembly_item_card)
        CardView mAssemblyCardView;
        @BindView(R.id.assembly_date_title)
        TextView mAssemblyDateLine;
        @BindView(R.id.assembly_pic)
        ImageView mAssemblyPic;
        @BindView(R.id.assembly_theme_headline)
        TextView mAssemblyThemeHeadline;
        @BindView(R.id.assembly_description)
        TextView mAssemblyDescription;

        AssembliesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
