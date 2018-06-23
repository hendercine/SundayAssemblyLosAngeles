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
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hendercine.sala.BaseActivity;
import com.hendercine.sala.BuildConfig;
import com.hendercine.sala.R;
import com.hendercine.sala.models.Assembly;
import com.hendercine.sala.models.Performer;
import com.hendercine.sala.models.Song;
import com.hendercine.sala.ui.adapters.SideBarRVAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("Convert2Lambda")
public class MainActivity extends BaseActivity {

    public static final int RC_SIGN_IN = 237;

    static final String USER_ID = "userId";
    static final String USER_NAME = "userName";
    static final String USER_PHOTO_URL = "userPhotoUrl";
    private static final String TAG = MainActivity.class.getSimpleName();

    private String mAppBarTitle;
    private String mAppBarImageUrl;
    private String mAssemblyDateAndTheme;
    private String mAssemblyBackDrop;
    private String mUserId;
    private String mUsername;
    private String mUserPhotoUrl;

    private Fragment mFragment;
    private Bundle mBundle;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference mAssemblyDbRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

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
    @Nullable
    @BindView(R.id.user_nav_header_img_view)
    ImageView mUserHeaderIV;
    @Nullable
    @BindView(R.id.username_nav_header_text_view)
    TextView mUsernameHeaderView;
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
        // Initialize Firebase Components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mFirebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();

        mAssemblyDbRef = mFirebaseDatabase.getReference().child("assemblies");

        if (savedInstanceState != null) {
            mUserId = savedInstanceState.getString(USER_ID);
            mUsername = savedInstanceState.getString(USER_NAME);
            mUserPhotoUrl = savedInstanceState.getString(USER_PHOTO_URL);
        }

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

            mToggle = new ActionBarDrawerToggle(
                    this,
                    mDrawer,
                    mToolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
            );

            activateDrawerItems();
        }

        if (savedInstanceState == null) {
            mFragmentManager = getSupportFragmentManager();
            mAboutSalaFragment = new AboutSalaFragment();
            mFragmentManager
                    .beginTransaction()
                    .add(mContentFrame.getId(), mAboutSalaFragment)
                    .commit();
        }

        setCollapsingToolbarBehavior();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // Check if user is signed in (non-null) and update UI accordingly.
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // already signed in
                    showSnackBar(R.string.signed_in_success_message);
                } else {
                    // not signed in
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(!BuildConfig.DEBUG /* credentials */, true /* hints */)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.EmailBuilder().build(),
                                            new AuthUI.IdpConfig.GoogleBuilder()
                                                    .build()
                                    ))
                                    .setLogo(R.drawable.sala_logo_grass)
                                    .build(),
                            RC_SIGN_IN
                    );
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                showSnackBar(R.string.signed_in_snackbar);
                updateUI(mAuth.getCurrentUser());
            } else if (response == null) {
                showSnackBar(R.string.sign_in_canceled_snackbar);
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.logout_menu) {
            AuthUI.getInstance().signOut(this);
            return true;
        } else {
            return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString(USER_ID, mUserId);
        outState.putString(USER_NAME, mUsername);
        outState.putString(USER_PHOTO_URL, mUserPhotoUrl);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(mAuthStateListener);
        updateUI(mAuth.getCurrentUser());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.removeAuthStateListener(mAuthStateListener);
    }


    private void updateUI(FirebaseUser user) {
        if (user != null ) {
            // Signed in
            if (mUsernameHeaderView != null && mUserHeaderIV != null) {
                mUsernameHeaderView.setText(user.getDisplayName());

            mUserPhotoUrl = Objects.requireNonNull(user.getPhotoUrl()).toString();
                Glide.with(this)
                        .load(mUserPhotoUrl)
                        .into(mUserHeaderIV);
            }
        } else {
            // Signed out
            if (mUsernameHeaderView != null && mUserHeaderIV != null) {
                mUsernameHeaderView.setText(R.string.dummy_user_name);
                Glide.with(this)
                        .load(R.drawable.sala_logo_grass)
                        .into(mUserHeaderIV);
            }
        }
    }

    private void getLastAssemblyData() {
        // [START single_value_read]
        mDatabaseRef.child("assemblies").child(String.valueOf(0))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                String assemblyDate = ds.child
                                        ("assembly_date")
                                        .getValue(String.class);
                                String assemblyTheme = ds.child
                                        ("assembly_theme")
                                        .getValue(String.class);
                                mAssemblyDateAndTheme = assemblyDate + " " + assemblyTheme;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        // Get assembly value
//                        Assembly assembly = dataSnapshot.getValue(Assembly.class);
//
//                        // [START_EXCLUDE]
//                        if (assembly == null) {
//                            // Assembly is null, error out
//                            Timber.e("Assembly is null");
//                            Toast.makeText(
//                                    MainActivity.this,
//                                    "Error: Could not fetch assembly",
//                                    Toast.LENGTH_SHORT
//                            ).show();
//                        } else {
//                            mAssembly = new Assembly();
//                            mAssemblyDateAndTheme =
//                                    assembly.getAssemblyDate() + " " +
//                                    assembly.getAssemblyTheme();
//                            mAssemblyBackDrop = assembly.mAssemblyPhotoUrl;
//                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void activateDrawerItems() {
        // Handle navigation drawer click events
        if (mNavView != null) {
            updateUI(mAuth.getCurrentUser());
            mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    menuItem.setChecked(true);
                    if (mDrawer != null) {
                        mDrawer.closeDrawer(GravityCompat.START, true);
                    }
                    // TODO: Add code here to update the UI based on the item selected
                    // For example, swap UI fragments here
                    mFragment = null;
                    mBundle = new Bundle();
                    int position = menuItem.getItemId();
                    if (position == R.id.about_nav) {
                        mFragment = new AboutSalaFragment();
                        mAppBarTitle = mAboutTitle;
                        mAppBarImageUrl = mAboutBannerUrl;
                    } else if (position == R.id.assemblies_nav) {
//                        mFragment = new AssembliesFragment();
                        mAppBarTitle = mAssemblyDateAndTheme;
                        mAboutBannerUrl = mAssemblyBackDrop;
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display AssembliesFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.program_nav) {
//                        mBundle.putParcelable("latest_assembly", Parcels.wrap
//                                (mAssembliesList.get(0))); // Get data at index 0 for the most recent Assembly
//                        mFragment = new ProgramFragment();
//                        mFragment.setArguments(mBundle);
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display ProgramFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.lyrics_nav) {
                        //                    mFragment = new LyricsFragment();
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display LyricsFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.speaker_nav) {
                        //                    mFragment = new SpeakerFragment();
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display SpeakerFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.help_often_nav) {
                        //                    mFragment = new HelpOftenFragment();
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display HelpOftenFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.live_better_nav) {
                        //                    mFragment = new LiveBetterFragment();
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display LiveBetterFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.chat_nav) {
                        //                    mFragment = new ChatFragment();
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
                        //                    mBundle.putString("url", "https://www.sundayassemblyla.org");
                        //                    mFragment = new WebsiteFragment();
                        //                    mFragment.setArguments(mBundle);
                        Toast.makeText(
                                getApplicationContext(),
                                "This will display WebsiteFragment",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else if (position == R.id.logout_nav) {
                        AuthUI.getInstance().signOut(MainActivity.this);
                        return true;
                    }
                    if (mFragment != null) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_frame, mFragment)
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
                mFragment = null;
                mBundle = new Bundle();
                if (position == mSideBarAdapter.getItemId(0)) {
                    mFragment = new AboutSalaFragment();
                    mAppBarTitle = mAboutTitle;
                    mAppBarImageUrl = mAboutBannerUrl;
                } else if (position == mSideBarAdapter.getItemId(1)) {
//                    mFragment = new AssembliesFragment();
                    getLastAssemblyData();
                    mAppBarTitle = mAssemblyDateAndTheme;
                    mAppBarImageUrl = mAboutBannerUrl;
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display AssembliesFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(2)) {
//                    mFragment = new ProgramFragment();
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display ProgramFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(3)) {
//                    mFragment = new LyricsFragment();
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display LyricsFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(4)) {
//                    mFragment = new SpeakerFragment();
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display SpeakerFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(5)) {
//                    mFragment = new HelpOftenFragment();
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display HelpOftenFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(6)) {
//                    mFragment = new LiveBetterFragment();
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display LiveBetterFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(7)) {
//                    mFragment = new ChatFragment();
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
//                    mBundle.putString("url", "https://www.sundayassemblyla.org");
//                    mFragment = new WebsiteFragment();
//                    mFragment.setArguments(mBundle);
                    Toast.makeText(
                            getApplicationContext(),
                            "This will display WebsiteFragment",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (position == mSideBarAdapter.getItemId(12)) {
                    AuthUI.getInstance().signOut(MainActivity.this);
                }
                if (mFragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, mFragment)
//                            .setTransition(R.anim.fade)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
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
