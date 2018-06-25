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

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class AssembliesFragment extends Fragment implements SiteServiceReceiver.Listener {

    private static final String ASSEMBLY_TITLES = "assembly_titles";
    private static final String ASSEMBLY_THEMES = "assembly_themes";
    private static final String ASSEMBLY_DESCRIPTIONS = "assembly_descriptions";
    private static final String ASSEMBLY_PHOTO_URLS = "assembly_photo_urls";
    private static final String POSITION_STATE_KEY = "scroll_position";

    private Unbinder unbinder;
    private ArrayList<Assembly> mAssemblyTitlesList;
    private ArrayList<Assembly> mAssemblyThemesList;
    private ArrayList<Assembly> mAssemblyDescriptionsList;
    private ArrayList<Assembly> mAssemblyPhotoUrlsList;
    private int mScrollPosition;

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
        // Start the service call
        Objects.requireNonNull(getActivity())
                .startService(createAssemblyIntentCall());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_assemblies, container,
                false
        );
        unbinder = ButterKnife.bind(this, rootView);

        mLinearLayoutManager = new LinearLayoutManager(getContext());

        // Set Adapter
//        new FetchSalaWebsiteData().execute();
        mAdapter = new AssembliesRVAdapter(mAssemblyTitlesList,
                mAssemblyThemesList, mAssemblyDescriptionsList, mAssemblyPhotoUrlsList
        );
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
        intent.putExtra("assembly_titles", Parcels.wrap(mAssemblyTitlesList));

        return intent;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        ArrayList<Assembly> assemblyTitles = Parcels.unwrap(resultData
                .getParcelable(ASSEMBLY_TITLES));
        ArrayList<Assembly> assemblyThemes = Parcels.unwrap(resultData
                .getParcelable(ASSEMBLY_THEMES));
        ArrayList<Assembly> assemblyDescriptions = Parcels.unwrap(resultData
                .getParcelable(ASSEMBLY_DESCRIPTIONS));
        ArrayList<Assembly> assemblyPhotoUrls = Parcels.unwrap(resultData
                .getParcelable(ASSEMBLY_PHOTO_URLS));
        mAssemblyTitlesList = new ArrayList<>();
        mAssemblyTitlesList.addAll(assemblyTitles);
        mAssemblyThemesList = new ArrayList<>();
        mAssemblyThemesList.addAll(assemblyThemes);
        mAssemblyDescriptionsList = new ArrayList<>();
        mAssemblyDescriptionsList.addAll(assemblyDescriptions);
        mAssemblyPhotoUrlsList = new ArrayList<>();
        mAssemblyPhotoUrlsList.addAll(assemblyPhotoUrls);

        Timber.i(
                "Is there a title String here in Fragment: '%s'",
                mAssemblyTitlesList.get(1).mAssemblyDate
        );
        Timber.i(
                "Is there a theme String here in Fragment: '%s'",
                mAssemblyTitlesList.get(1).mAssemblyTheme
        );
        Timber.i(
                "Is there a decription String here in Fragment: '%s'",
                mAssemblyTitlesList.get(1).mAssemblyDescription
        );
        Timber.i(
                "Is there a String photo url here in Fragment: '%s'",
                mAssemblyTitlesList.get(1).mAssemblyPhotoUrl
        );

        mAdapter = new AssembliesRVAdapter(mAssemblyTitlesList,
                mAssemblyThemesList, mAssemblyDescriptionsList, mAssemblyPhotoUrlsList
        );
        mAdapter.setAssembliesList(mAssemblyTitlesList,
                mAssemblyThemesList, mAssemblyDescriptionsList, mAssemblyPhotoUrlsList
        );
        mAssembliesRV.setAdapter(mAdapter);
    }
}
