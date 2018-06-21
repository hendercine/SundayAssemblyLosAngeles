/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 5/21/18 1:26 PM
 */

package com.hendercine.sala.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hendercine.sala.R;
import com.hendercine.sala.models.Assembly;
import com.hendercine.sala.models.Performer;
import com.hendercine.sala.models.Program;
import com.hendercine.sala.models.Song;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link ProgramFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link ProgramFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ProgramFragment extends Fragment {

    private static final String TAG = ProgramFragment.class.getSimpleName();
    private static final String LATEST_ASSEMBLY = "latest_assembly";

    private ArrayList<Assembly> mAssemblies;
    private Assembly mAssembly;
    private Program mProgram;
    private Performer mPerformer;
    private Song mSong;
    private ArrayList<Performer> mPerformerArrayList;
    private ArrayList<Song> mSongArrayList;

    private OnFragmentInteractionListener mListener;
    private Unbinder mUnbinder;

    @BindView(R.id.program_title)
    TextView mProgramTitle;
    @BindView(R.id.program_recycler_view)
    RecyclerView mProgramRecyclerView;

    public ProgramFragment() {
        // Required empty public constructor
    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ProgramFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ProgramFragment newInstance(String param1, String param2) {
//        ProgramFragment fragment = new ProgramFragment();
//        Bundle args = new Bundle();
//        args.putString(LATEST_ASSEMBLY, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAssembly = Parcels.unwrap(getArguments().getParcelable(LATEST_ASSEMBLY));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_program, container,
                false);
        ButterKnife.bind(this, view);
        mUnbinder = ButterKnife.bind(this, view);
//
//        String assemblyTheme = mAssembly.getAssemblyTheme();
//        mProgramTitle.setText(assemblyTheme);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
