/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 5/21/18 1:08 PM
 */

package com.hendercine.sala.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hendercine.sala.BaseActivity;
import com.hendercine.sala.R;
import com.hendercine.sala.models.Assembly;
import com.hendercine.sala.models.Performer;
import com.hendercine.sala.models.Song;
import com.hendercine.sala.ui.adapters.SideBarRVAdapter;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    private String mAppBarTitle;
    private String mAppBarImageUrl;
    private String mAssemblyDateAndTheme;
    private String mAssemblyBackDrop;

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    private ChildEventListener mChildEventListener;
    private ValueEventListener mAssemblyListener;

    private ActionBarDrawerToggle mToggle;
    private ActionBar mActionBar;

    private FragmentManager mFragmentManager;
    private AboutSalaFragment mAboutSalaFragment;
    private boolean mTwoPane;
    private String[] mSideBarArray;
    private SideBarRVAdapter mSideBarAdapter;

    private Assembly mAssemblyData;
    private Assembly mAssembly;
    private Performer mPerformer;
    private Song mSong;
    private ArrayList<Assembly> mAssembliesList;

    @Nullable
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @Nullable
    @BindView(R.id.nav_view)
    NavigationView mNavView;

    @Nullable
    @BindView(R.id.main_side_bar_recycler_view)
    RecyclerView mSideBarRecyclerView;

    @BindView(R.id.collapsing_toolbar_backdrop_img)
    ImageView collapsingToolbarBackDrop;
    @BindView(R.id.app_bar_title)
    TextView mTitleView;
    @Nullable
    @BindView(R.id.toolbar_main)
    Toolbar mToolbar;
    @Nullable
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Nullable
    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.content_frame)
    FrameLayout mContentFrame;

    @BindString(R.string.about_banner_url)
    String mAboutBannerUrl;
    @BindString(R.string.about_sala_title)
    String mAboutTitle;
    @BindString(R.string.about_nav_title)
    String mAboutSideBar;
    @BindString(R.string.program_nav_title)
    String mProgramSidebar;
    @BindString(R.string.lyrics_nav_title)
    String mLyricsSideBar;
    @BindString(R.string.speaker_bio_nav_title)
    String mSpeakerSideBar;
    @BindString(R.string.assemblies_nav_title)
    String mAssembliesSideBar;
    @BindString(R.string.help_often_nav_title)
    String mHelpSideBar;
    @BindString(R.string.live_better_nav_title)
    String mLiveSideBar;
    @BindString(R.string.salamander_chat_nav_title)
    String mChatSideBar;
    @BindString(R.string.instagram_nav_title)
    String mInstaSideBar;
    @BindString(R.string.facebook_nav_title)
    String mFacebookSideBar;
    @BindString(R.string.twitter_nav_title)
    String mTwitterSideBar;
    @BindString(R.string.sala_on_the_web_nav_title)
    String mWebsiteSideBar;
    @BindString(R.string.logout_nav_title)
    String mLogoutSideBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]

        getLastAssemblyData();

        mTwoPane = getResources().getBoolean(R.bool.isTablet);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mAppBarTitle = mAboutTitle;
        mAppBarImageUrl = mAboutBannerUrl;

        if (!mTwoPane && mSideBarRecyclerView != null) {
            mSideBarArray = new String[]{
                    mAboutSideBar, mAssembliesSideBar, mProgramSidebar,
                    mLyricsSideBar, mSpeakerSideBar, mHelpSideBar,
                    mLiveSideBar, mChatSideBar, mInstaSideBar,
                    mFacebookSideBar, mTwitterSideBar, mWebsiteSideBar,
                    mLogoutSideBar};
            mSideBarRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mSideBarAdapter = new SideBarRVAdapter(mSideBarArray);
            mSideBarRecyclerView.setAdapter(mSideBarAdapter);
            activateSideBarItems();
        } else {
            if (mActionBar != null) {
                mActionBar.setDisplayHomeAsUpEnabled(true);
                mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            }
            //TODO: Check for savedInstanceState if != null restore last active state
            mFragmentManager = getSupportFragmentManager();
            mAboutSalaFragment = new AboutSalaFragment();
            mFragmentManager
                    .beginTransaction()
                    .add(mContentFrame.getId(), mAboutSalaFragment)
                    .commit();

            mToggle = new ActionBarDrawerToggle(
                    this,
                    mDrawer,
                    mToolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
            );

            activateDrawerItems();
        }

        setCollapsingToolbarBehavior();

    }

    private void getLastAssemblyData() {
        // [START single_value_read]
        mDatabase.child("assemblies").child("assembly_date")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Get assembly value
                        Assembly assembly = dataSnapshot.getValue(Assembly.class);

                        // [START_EXCLUDE]
                        if (assembly == null) {
                            // Assembly is null, error out
                            Timber.e("Assembly is null");
                            Toast.makeText(
                                    MainActivity.this,
                                    "Error: Could not fetch assembly",
                                    Toast.LENGTH_SHORT
                            ).show();
                        } else {
                            mAssemblyDateAndTheme =
                                    assembly.mAssemblyDate + " " +
                                    assembly.mAssemblyTheme;
                            mAssemblyBackDrop = assembly.mAssemblyPhotoUrl;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void activateDrawerItems() {
        // Handle navigation drawer click events
        if (mNavView != null) {
            mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    menuItem.setChecked(true);
                    if (mDrawer != null) {
                        mDrawer.closeDrawer(GravityCompat.START, true);
                    }
                    // TODO: Add code here to update the UI based on the item selected
                    // For example, swap UI fragments here
                    Fragment fragment = null;
                    Bundle bundle = new Bundle();
                    int position = menuItem.getItemId();
                    if (position == R.id.about_nav) {
                        fragment = new AboutSalaFragment();
                        mAppBarTitle = mAboutTitle;
                        mAppBarImageUrl = mAboutBannerUrl;
                    } else if (position == R.id.assemblies_nav) {
//                        fragment = new AssembliesFragment();
                        mAppBarTitle = mAssemblyDateAndTheme;
                        mAboutBannerUrl = mAssemblyBackDrop;
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display AssembliesFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.program_nav) {
//                        bundle.putParcelable("latest_assembly", Parcels.wrap
//                                (mAssembliesList.get(0))); // Get data at index 0 for the most recent Assembly
//                        fragment = new ProgramFragment();
//                        fragment.setArguments(bundle);
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display ProgramFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.lyrics_nav) {
                        //                    fragment = new LyricsFragment();
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display LyricsFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.speaker_nav) {
                        //                    fragment = new SpeakerFragment();
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display SpeakerFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.help_often_nav) {
                        //                    fragment = new HelpOftenFragment();
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display HelpOftenFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.live_better_nav) {
                        //                    fragment = new LiveBetterFragment();
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display LiveBetterFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.chat_nav) {
                        //                    fragment = new ChatFragment();
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display ChatFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                        // TODO: Create intents for Instagram, Facebook and Twitter
                    } else if (position == R.id.insta_link_nav) {
                        Toast.makeText(
                                getApplicationContext(),
                                "This will open Instagram",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.facebook_link_nav) {
                        Toast.makeText(
                                getApplicationContext(),
                                "This will open Facebook",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.twitter_link_nav) {
                        Toast.makeText(
                                getApplicationContext(),
                                "This will open Twitter",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.site_link_nav) {
                        //                    bundle.putString("url", "https://www.sundayassemblyla.org");
                        //                    fragment = new WebsiteFragment();
                        //                    fragment.setArguments(bundle);
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display WebsiteFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.logout_nav) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this,
                                SignInActivity.class));
                        finish();
                        return true;
                    }
                    if (fragment != null) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_frame, fragment)
                                //                            .setTransition(R.anim.fade)
                                .addToBackStack(null)
                                .commit();
                    }
                    mDrawer.closeDrawer(GravityCompat.START, true);
                    return true;
                }
            });
        }
    }

    private void activateSideBarItems() {
        // Handle two-pane side bar drawer click events
        mSideBarAdapter.setClickListener(new SideBarRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // TODO: Add code here to update the UI based on the item selected
                // For example, swap UI fragments here
                Fragment fragment = null;
                Bundle bundle = new Bundle();
                if (position == mSideBarAdapter.getItemId(0)) {
                    fragment = new AboutSalaFragment();
                    mAppBarTitle = mAboutTitle;
                    mAppBarImageUrl = mAboutBannerUrl;
                } else if (position == mSideBarAdapter.getItemId(1)) {
//                    fragment = new AssembliesFragment();
                    mAppBarTitle = mAssemblyDateAndTheme;
                    mAboutBannerUrl = mAssemblyBackDrop;
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display AssembliesFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(2)) {
//                    fragment = new ProgramFragment();
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display ProgramFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(3)) {
//                    fragment = new LyricsFragment();
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display LyricsFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(4)) {
//                    fragment = new SpeakerFragment();
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display SpeakerFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(5)) {
//                    fragment = new HelpOftenFragment();
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display HelpOftenFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(6)) {
//                    fragment = new LiveBetterFragment();
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display LiveBetterFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(7)) {
//                    fragment = new ChatFragment();
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display ChatFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                    // TODO: Create intents for Instagram, Facebook and Twitter
                } else if (position == mSideBarAdapter.getItemId(8)) {
                    Toast.makeText(
                            getApplicationContext(),
                            "This will open Instagram",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(9)) {
                    Toast.makeText(
                            getApplicationContext(),
                            "This will open Facebook",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(10)) {
                    Toast.makeText(
                            getApplicationContext(),
                            "This will open Twitter",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(11)) {
//                    bundle.putString("url", "https://www.sundayassemblyla.org");
//                    fragment = new WebsiteFragment();
//                    fragment.setArguments(bundle);
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display WebsiteFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                }
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, fragment)
//                            .setTransition(R.anim.fade)
                            .addToBackStack(null)
                            .commit();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void setCollapsingToolbarBehavior() {
        // Handle transition from expanded to collapsed and in between
        if (mCollapsingToolbarLayout != null && mToolbar != null &&
                mAppBarLayout != null) {
            mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    Fade fade = new Fade();
                    if (Math.abs(verticalOffset) == mAppBarLayout.getTotalScrollRange()) {
                        // Collapsed
                        TransitionManager.beginDelayedTransition
                                (mAppBarLayout, fade);
                        mToolbar.setVisibility(View.VISIBLE);
                        mCollapsingToolbarLayout.setTitleEnabled(true);
                        mCollapsingToolbarLayout.setTitle(mAppBarTitle);
                        mTitleView.setVisibility(View.GONE);
                    } else if (verticalOffset == 0) {
                        // Expanded
                        mCollapsingToolbarLayout.setTitleEnabled(false);
                        mToolbar.setVisibility(View.GONE);
                        mTitleView.setText(mAppBarTitle);
                        mTitleView.setVisibility(View.VISIBLE);
                    } else {
                        // Mid-scroll
                        mCollapsingToolbarLayout.setTitleEnabled(true);
                        mCollapsingToolbarLayout.setTitle(mAppBarTitle);
                        mTitleView.setVisibility(View.GONE);
                    }
                }
            });
        }
        // Load Backdrop Image
        Glide.with(this)
                .load(mAppBarImageUrl)
                .into(collapsingToolbarBackDrop);
    }
}
