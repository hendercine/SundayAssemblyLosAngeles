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
import com.hendercine.sala.ui.adapters.AssembliesRVAdapter;
import com.hendercine.sala.ui.widget.WidgetIntentService;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AssembliesFragment extends Fragment implements SiteServiceReceiver.Listener {

    public static final String ASSEMBLIES = "assemblies";
    private static final String POSITION_STATE_KEY = "scroll_position";
    public static final String REC = "rec";

    private Unbinder unbinder;
    private ArrayList<Assembly> mAssembliesList;

    @BindView(R.id.assemblies_recycler_view)
    RecyclerView mAssembliesRV;
    private AssembliesRVAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public AssembliesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_assemblies, container,
                false);
        unbinder = ButterKnife.bind(this, rootView);

        mLinearLayoutManager = new LinearLayoutManager(getContext());

        if (savedInstanceState == null) {
            // Start the service call
            Objects.requireNonNull(getActivity()).startService(createAssemblyIntentCall());

            mAdapter = new AssembliesRVAdapter(mAssembliesList);
        }

        if (mAssembliesRV != null) {
            mAssembliesRV.setLayoutManager(mLinearLayoutManager);
            mAssembliesRV.setAdapter(mAdapter);
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        int scrollPosition = mLinearLayoutManager.findFirstCompletelyVisibleItemPosition();
        outState.putInt(POSITION_STATE_KEY, scrollPosition);
        outState.putParcelable(ASSEMBLIES, Parcels.wrap(mAssembliesList));
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
        intent.putExtra(REC, receiver);
        intent.putExtra(ASSEMBLIES, Parcels.wrap(mAssembliesList));
        WidgetIntentService.startActionAddDate(getContext());

        return intent;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        ArrayList<Assembly> assemblies = Parcels.unwrap(resultData
                .getParcelable(ASSEMBLIES));

        mAssembliesList = new ArrayList<>();
        for (int i = 0; i < assemblies.size(); i++) {
            Assembly assembly = new Assembly();
            assembly.setAssemblyDate(assemblies.get(i).getAssemblyDate());

            mAssembliesList.add(assembly);
        }

        if (mAdapter != null) {
            mAdapter.setAssembliesList(mAssembliesList);
        }
    }
}
