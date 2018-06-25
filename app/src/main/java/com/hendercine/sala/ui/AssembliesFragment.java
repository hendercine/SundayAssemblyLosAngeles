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

    // Base strings to run through Jsoup
    private static final String ASSEMBLIES_URL = "http://www.sundayassemblyla.org";
    private static final String CLASS_NAME = "event-wrap";
    private static final String LI_ELEMENT = "li";
    private static final String ASSEMBLIES = "assemblies";
    private static final String POSITION_STATE_KEY = "scroll_position";

    private Unbinder unbinder;
    private ArrayList<Assembly> mAssembliesList;
    private ArrayList<Assembly> mAssemblyArrayList;
    private Assembly mAssembly;
    private int mScrollPosition;

    @BindView(R.id.assemblies_recycler_view)
    RecyclerView mAssembliesRV;
    private AssembliesRVAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

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
//        setRetainInstance(true);

//        if (getArguments() != null) {
//            mAssembly = Parcels.unwrap(getArguments().getParcelable(ASSEMBLIES));
//            mAssembliesList.add(mAssembly);
//            mAdapter.setAssembliesList(mAssembliesList);
//        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_assemblies, container,
                false);
        unbinder = ButterKnife.bind(this, rootView);

        mLinearLayoutManager = new LinearLayoutManager(getContext());

        // Start the service call
        Objects.requireNonNull(getActivity()).startService(createAssemblyIntentCall());

        // Set Adapter
//        new FetchSalaWebsiteData().execute();
        mAdapter = new AssembliesRVAdapter(mAssembliesList);
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
//            mAssembly.setAssemblyTheme(assemblies.get(i).getAssemblyTheme());
//            mAssembly.setAssemblyDescription(assemblies.get(i).getAssemblyDescription());
            mAssembly.setAssemblyPhotoUrl(assemblies.get(i).getAssemblyPhotoUrl());

            mAssembliesList.add(mAssembly);
        }

        Timber.i("Is there a String here in Fragment: '%s'", mAssembliesList.get(1).mAssemblyDate);
        if (mAdapter != null) {
            mAdapter.setAssembliesList(mAssembliesList);
        }
    }

//    public class FetchSalaWebsiteData extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            // Check for network
//            if (BaseActivity.isNetworkAvailable(Objects.requireNonNull(getContext())))
//                Timber.d("Get html");
//            try {
//                String assemblyDateLine;
//                String assemblyThemeLine;
//                String assemblyDescription;
//                String assemblyPhotoUrl;
//
//                Document eventSummary = Jsoup.connect(ASSEMBLIES_URL).get();
//                Elements assemblyDetails = eventSummary.getElementsByClass(CLASS_NAME);
//                Element assemblies = eventSummary.tagName(LI_ELEMENT);
//                Elements titles = assemblies.select("h4");
//                mAssemblyArrayList = new ArrayList<>();
//                for (Element title : titles) {
//                    assemblyDateLine = title.text();
////                    assemblyThemeLine = assemblies.get(i).tagName("strong").text();
////                    assemblyDescription = assemblies.tagName("span").text();
////                    assemblyPhotoUrl = assemblies.get(i).attr("src");
//
//                    mAssembly = new Assembly();
//                    mAssembly.setAssemblyDate(assemblyDateLine);
////                    mAssembly.setAssemblyTheme(assemblyThemeLine);
////                    mAssembly.setAssemblyDescription(assemblyDescription);
////                    mAssembly.setAssemblyPhotoUrl(assemblyPhotoUrl);
//                    mAssemblyArrayList.add(mAssembly);
//                }
//                Timber.i("Is there a string here: '%s'",
//                        mAssemblyArrayList.get(0).getAssemblyDate());
//
//            } catch (Exception e) {
//                Timber.e("Something went wrong in the background", e);
//                e.printStackTrace();
//            }
//            return null;
//
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//            mAssembliesList = mAssemblyArrayList;
//            Timber.i("Is there a string here: ", mAssembliesList.get(0).getAssemblyDate());
//        }
//    }
}
