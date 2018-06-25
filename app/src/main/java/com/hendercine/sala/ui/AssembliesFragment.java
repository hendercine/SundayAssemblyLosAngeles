/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 5/21/18 2:02 PM
 */

package com.hendercine.sala.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendercine.sala.R;
import com.hendercine.sala.data.SalaSiteIntentService;
import com.hendercine.sala.data.SiteServiceReceiver;
import com.hendercine.sala.models.Assembly;
import com.hendercine.sala.ui.adapters.AssemliesRVAdapter;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AssembliesFragment extends Fragment implements SiteServiceReceiver.Listener {

    private static final String ASSEMBLIES = "assemblies";
    private static final String POSITION_STATE_KEY = "scroll_position";
    private Unbinder unbinder;
    private ArrayList<Assembly> mAssembliesList;
    private Assembly mAssembly;

    @BindView(R.id.assemblies_recycler_view)
    RecyclerView mAssembliesRV;
    private AssemliesRVAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private int mScrollPosition;

    public AssembliesFragment() {
        // Required empty public constructor
    }

    public AssembliesFragment newInstance(Assembly assembly) {
        AssembliesFragment fragment = new AssembliesFragment();
        Bundle args = new Bundle();
        args.putParcelable(ASSEMBLIES, Parcels.wrap(assembly));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        // Start the service call
        Objects.requireNonNull(getActivity()).startService(createAssemblyIntentCall());

        if (getArguments() != null) {
            mAssembliesList = Parcels.unwrap(getArguments().getParcelable(ASSEMBLIES));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_assemblies, container,
                false);
        unbinder = ButterKnife.bind(this, rootView);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        // Set Adapter
        mAdapter = new AssemliesRVAdapter(mAssembliesList);
        if (mAssembliesRV != null) {
            mAssembliesRV.setLayoutManager(mLinearLayoutManager);
            mAssembliesRV.setAdapter(mAdapter);
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mScrollPosition = mLinearLayoutManager.findFirstCompletelyVisibleItemPosition();
        outState.putInt(POSITION_STATE_KEY, mScrollPosition);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private Intent createAssemblyIntentCall() {
        Intent intent = new Intent(getContext(), SalaSiteIntentService.class);
        SiteServiceReceiver receiver = new SiteServiceReceiver(new Handler());
        receiver.setListener(this);
        intent.putExtra("rec", receiver);
        intent.putExtra("assemblies", Parcels.wrap(mAssembliesList));

        return intent;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        ArrayList<Assembly> assemblies = Parcels.unwrap(resultData
                .getParcelable(ASSEMBLIES));

        mAssembliesList = new ArrayList<>();
        for (int i = 0; i < assemblies.size(); i++) {
            mAssembly = new Assembly();
            mAssembly.setAssemblyDate(assemblies.get(i).getAssemblyDate());
            mAssembly.setAssemblyTheme(assemblies.get(i).getAssemblyTheme());
            mAssembly.setAssemblyDescription(assemblies.get(i).getAssemblyDescription());
            mAssembly.setAssemblyPhotoUrl(assemblies.get(i).getAssemblyPhotoUrl());

            mAssembliesList.add(mAssembly);
        }

        mAssembliesRV.setLayoutManager(mLinearLayoutManager);
        mAssembliesRV.smoothScrollToPosition(mScrollPosition);
        mAdapter.setAssembliesList(mAssembliesList);
    }
}
