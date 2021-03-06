/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 6/24/18 4:01 PM
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * sala created by hendercine on 6/24/18.
 */
public class AssembliesRVAdapter extends RecyclerView.Adapter<AssembliesRVAdapter.AssembliesViewHolder> {

    private ArrayList<Assembly> mAssemblies;

    public AssembliesRVAdapter(ArrayList<Assembly> assemblies) {
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

        Assembly assembly = mAssemblies.get(position);
        holder.mAssemblyDateLine.setText(assembly.getAssemblyDate());
    }

    @Override
    public int getItemCount() {
        return (mAssemblies == null) ? 0 : mAssemblies.size();
    }

    public void setAssembliesList(ArrayList<Assembly> assemblies) {
        mAssemblies = new ArrayList<>();
        mAssemblies.addAll(assemblies);
        notifyDataSetChanged();
    }

    class AssembliesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.assembly_date_title)
        TextView mAssemblyDateLine;

        AssembliesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
