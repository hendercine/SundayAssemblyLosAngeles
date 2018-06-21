/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 5/21/18 1:08 PM
 */

package com.hendercine.sala.ui;

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
import com.hendercine.sala.models.User;
import com.hendercine.sala.ui.adapters.SideBarRVAdapter;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN = 1;

    private String mUsername;
    private User mUser;

    private String mAppBarTitle;
    private String mAppBarImageUrl;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mAssemblyDbRef;
    private DatabaseReference mChatDbReference;

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
    @BindView(R.id.main_side_bar_recycler_view)
    RecyclerView mSideBarRecyclerView;
    @Nullable
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.content_frame)
    FrameLayout mContentFrame;
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
    @BindString(R.string.future_assemblies_nav_title)
    String mFutureSideBar;
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
        // Initialize Database and access data
        mAssemblyDbRef = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("assemblies");

        mAssemblyDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mAssemblyData = (Assembly) dataSnapshot.getChildren();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        makeMasterAssembliesList();

        mTwoPane = getResources().getBoolean(R.bool.isTablet);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mAppBarTitle = mAboutTitle;
        mAppBarImageUrl = mAboutBannerUrl;

        if (!mTwoPane && mSideBarRecyclerView != null) {
            mSideBarArray = new String[]{mAboutSideBar, mProgramSidebar,
                    mLyricsSideBar, mSpeakerSideBar, mFutureSideBar,
                    mHelpSideBar, mLiveSideBar, mChatSideBar, mInstaSideBar,
                    mFacebookSideBar, mTwitterSideBar, mWebsiteSideBar, mLogoutSideBar};
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
                    R.string.navigation_drawer_close);

            activateDrawerItems();
        }

        setCollapsingToolbarBehavior();

//        authorizeUser();

    }

// TODO: Remove if not ultimately useful
//    private void makeMasterAssembliesList() {
//        // Add value event listener to get Assembly data
//        mAssemblyListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                mAssemblyData = dataSnapshot.getValue(Assembly.class);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Timber.e("Database load error", databaseError.toException());
//                Toast.makeText(MainActivity.this,
//                        "Failed to retrieve data.",
//                        Toast.LENGTH_SHORT)
//                        .show();
//            }
//        };
//
//        mAssemblyDbRef.addValueEventListener(mAssemblyListener);
//    }

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
                    } else if (position == R.id.program_nav) {
                        bundle.putParcelable("latest_assembly", Parcels.wrap
                                (mAssembliesList.get(0))); // Get data at index 0 for the most recent Assembly
                        fragment = new ProgramFragment();
                        fragment.setArguments(bundle);
                        Toast.makeText(getApplicationContext(),
                                "This will display ProgramFragment",
                                Toast.LENGTH_SHORT).show();
                    } else if (position == R.id.lyrics_nav) {
                        //                    fragment = new LyricsFragment();
                        Toast.makeText(getApplicationContext(),
                                "This will display LyricsFragment",
                                Toast.LENGTH_SHORT).show();
                    } else if (position == R.id.speaker_nav) {
                        //                    fragment = new SpeakerFragment();
                        Toast.makeText(getApplicationContext(),
                                "This will display SpeakerFragment",
                                Toast.LENGTH_SHORT).show();
                    } else if (position == R.id.future_assemblies_nav) {
                        //                    fragment = new AssembliesFragment();
                        Toast.makeText(getApplicationContext(),
                                "This will display AssembliesFragment",
                                Toast.LENGTH_SHORT).show();
                    } else if (position == R.id.help_often_nav) {
                        //                    fragment = new HelpOftenFragment();
                        Toast.makeText(getApplicationContext(),
                                "This will display HelpOftenFragment",
                                Toast.LENGTH_SHORT).show();
                    } else if (position == R.id.live_better_nav) {
                        //                    fragment = new LiveBetterFragment();
                        Toast.makeText(getApplicationContext(),
                                "This will display LiveBetterFragment",
                                Toast.LENGTH_SHORT).show();
                    } else if (position == R.id.chat_nav) {
                        //                    fragment = new ChatFragment();
                        Toast.makeText(getApplicationContext(),
                                "This will display ChatFragment",
                                Toast.LENGTH_SHORT).show();
                        // TODO: Create intents for Instagram, Facebook and Twitter
                    } else if (position == R.id.insta_link_nav) {
                        Toast.makeText(getApplicationContext(),
                                "This will open Instagram",
                                Toast.LENGTH_SHORT).show();
                    } else if (position == R.id.facebook_link_nav) {
                        Toast.makeText(getApplicationContext(),
                                "This will open Facebook",
                                Toast.LENGTH_SHORT).show();
                    } else if (position == R.id.twitter_link_nav) {
                        Toast.makeText(getApplicationContext(),
                                "This will open Twitter",
                                Toast.LENGTH_SHORT).show();
                    } else if (position == R.id.site_link_nav) {
                        //                    bundle.putString("url", "https://www.sundayassemblyla.org");
                        //                    fragment = new WebsiteFragment();
                        //                    fragment.setArguments(bundle);
                        Toast.makeText(getApplicationContext(),
                                "This will display WebsiteFragment",
                                Toast.LENGTH_SHORT).show();
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
//                    fragment = new ProgramFragment();
                    Toast.makeText(getApplicationContext(),
                            "This will display ProgramFragment",
                            Toast.LENGTH_SHORT).show();
                } else if (position == mSideBarAdapter.getItemId(2)) {
//                    fragment = new LyricsFragment();
                    Toast.makeText(getApplicationContext(),
                            "This will display LyricsFragment",
                            Toast.LENGTH_SHORT).show();
                } else if (position == mSideBarAdapter.getItemId(3)) {
//                    fragment = new SpeakerFragment();
                    Toast.makeText(getApplicationContext(),
                            "This will display SpeakerFragment",
                            Toast.LENGTH_SHORT).show();
                } else if (position == mSideBarAdapter.getItemId(4)) {
//                    fragment = new AssembliesFragment();
                    Toast.makeText(getApplicationContext(),
                            "This will display AssembliesFragment",
                            Toast.LENGTH_SHORT).show();
                } else if (position == mSideBarAdapter.getItemId(5)) {
//                    fragment = new HelpOftenFragment();
                    Toast.makeText(getApplicationContext(),
                            "This will display HelpOftenFragment",
                            Toast.LENGTH_SHORT).show();
                } else if (position == mSideBarAdapter.getItemId(6)) {
//                    fragment = new LiveBetterFragment();
                    Toast.makeText(getApplicationContext(),
                            "This will display LiveBetterFragment",
                            Toast.LENGTH_SHORT).show();
                } else if (position == mSideBarAdapter.getItemId(7)) {
//                    fragment = new ChatFragment();
                    Toast.makeText(getApplicationContext(),
                            "This will display ChatFragment",
                            Toast.LENGTH_SHORT).show();
                    // TODO: Create intents for Instagram, Facebook and Twitter
                } else if (position == mSideBarAdapter.getItemId(8)) {
                    Toast.makeText(getApplicationContext(),
                            "This will open Instagram",
                            Toast.LENGTH_SHORT).show();
                } else if (position == mSideBarAdapter.getItemId(9)) {
                    Toast.makeText(getApplicationContext(),
                            "This will open Facebook",
                            Toast.LENGTH_SHORT).show();
                } else if (position == mSideBarAdapter.getItemId(10)) {
                    Toast.makeText(getApplicationContext(),
                            "This will open Twitter",
                            Toast.LENGTH_SHORT).show();
                } else if (position == mSideBarAdapter.getItemId(11)) {
//                    bundle.putString("url", "https://www.sundayassemblyla.org");
//                    fragment = new WebsiteFragment();
//                    fragment.setArguments(bundle);
                    Toast.makeText(getApplicationContext(),
                            "This will display WebsiteFragment",
                            Toast.LENGTH_SHORT).show();
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

    // TODO: Uncomment and/or implement to include Firebase Auth
//    private void authorizeUser() {
//        // Implement Firebase Auth
//        mUsername = ANONYMOUS;
//        // Initialize Firebase components
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        mFirebaseAuth = FirebaseAuth.getInstance();
//        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // user is signed in
//                    onSignedInInitialize(user.getDisplayName());
//                } else {
//                    // user is signed out
//                    onSignedOutCleanup();
//                    startActivityForResult(
//                            AuthUI.getInstance()
//                                    .createSignInIntentBuilder()
//                                    .setAvailableProviders(Arrays.asList(
//                                            new AuthUI.IdpConfig.EmailBuilder().build(),
//                                            new AuthUI.IdpConfig.PhoneBuilder().build(),
//                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
//                                            new AuthUI.IdpConfig.FacebookBuilder().build(),
//                                            new AuthUI.IdpConfig.TwitterBuilder().build()
//                                    ))
//                                    .build(),
//                            RC_SIGN_IN);
//                }
//
//            }
//        };
//    }

// TODO: Uncomment and implement for Firebase Auth
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RC_SIGN_IN) {
//            if (resultCode == RESULT_OK) {
//                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
//            } else if (resultCode == RESULT_CANCELED) {
//                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        }
//    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();
// TODO: Uncomment and implement for Firebase Auth
//        if (mAuthStateListener != null) {
//            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
//        }
//        detachDatabaseReadListener();
        // TODO: Clear the adapter
//        mMessageAdapter.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
// TODO: Uncomment and implement for Firebase Auth
//        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAssemblyListener != null) {
            mAssemblyDbRef.removeEventListener(mAssemblyListener);
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
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
// TODO: Uncomment for Firebase Auth
//  switch (item.getItemId()) {
//            case R.id.logout_menu:
//                //sign out
//                AuthUI.getInstance().signOut(this);
//                return true;
//            default:
//        }
//        }
        return super.onOptionsItemSelected(item);
    }

    private void onSignedInInitialize(String username) {
        mUsername = username;
        attachDatabaseReadListener();

    }

    private void onSignedOutCleanup() {
        mUsername = ANONYMOUS;
//        mMessageAdapter.clear();
    }

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
// TODO: Uncomment to hook up chat feature
//                    mUser = dataSnapshot.getValue(User.class);
//                    if (mUser != null) {
//                        mUsername = mUser.getUsername();
//                    }
//                    FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
//                    mMessageAdapter.add(friendlyMessage);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            };
            mChatDbReference.addChildEventListener(mChildEventListener);
            mAssemblyDbRef.addChildEventListener(mChildEventListener);
        }
    }

    private void detachDatabaseReadListener() {
        if (mChildEventListener != null) {
            mChatDbReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }

    private void readAndWriteChatDatabase() {
        // TODO: Refactor this method to store message and user data
        // Write a message to the database
        mChatDbReference = mFirebaseDatabase.getReference("message");

        mChatDbReference.setValue("Hello, world!");

        // Read from the database
        mChatDbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Timber.d("Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Timber.w("Failed to read value.", databaseError.toException());
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

//    @Override
//    public void onFragmentSelected(Uri uri) {
//
//    }
}
