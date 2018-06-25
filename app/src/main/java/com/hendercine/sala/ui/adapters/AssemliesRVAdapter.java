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
public class AssemliesRVAdapter extends RecyclerView.Adapter<AssemliesRVAdapter.AssembliesViewHolder>{

    private final ArrayList<Assembly> mAssemblies;

    public AssemliesRVAdapter(ArrayList<Assembly> assemblies) {
        this.mAssemblies = assemblies;
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
        Assembly assembly = mAssemblies.get(position);
        holder.mAssemblyDateLine.setText(assembly.getAssemblyDate());
        holder.mAssemblyThemeHeadline.setText(assembly.getAssemblyTheme());
        holder.mAssemblyDescription.setText(assembly.getAssemblyDescription());
        if (!assembly.getAssemblyPhotoUrl().isEmpty()) {
            Glide.with(context)
                    .load(assembly.getAssemblyPhotoUrl())
                    .into(holder.mAssemblyPic);
        } else {
            holder.mAssemblyPic.setImageResource(R.drawable.sala_logo_grass);
        }

    }

    @Override
    public int getItemCount() {
        return (mAssemblies == null) ? 0 : mAssemblies.size();
    }

    public void setAssembliesList(ArrayList<Assembly> assemblies) {
            mAssemblies.clear();
            mAssemblies.addAll(assemblies);
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
