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
import android.support.design.widget.NavigationView;
import android.support.transition.Fade;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hendercine.sala.BaseActivity;
import com.hendercine.sala.BuildConfig;
import com.hendercine.sala.R;
import com.hendercine.sala.models.User;
import com.hendercine.sala.ui.adapters.SideBarRVAdapter;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import java.util.Arrays;
import java.util.Objects;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

@SuppressWarnings("Convert2Lambda")
public class MainActivity extends BaseActivity {

    public static final int RC_SIGN_IN = 237;

    private static final String USER_ID = "userId";
    private static final String USER_NAME = "userName";
    private static final String USER_PHOTO_URL = "userPhotoUrl";
    private static final String USERS = "users";
    private static final String CURRENT_USER = "current_user";
    private static final String ASSEMBLIES = "assemblies";

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
    private FirebaseUser mCurrentUser;

    private ActionBarDrawerToggle mToggle;
    private ActionBar mActionBar;

    private FragmentManager mFragmentManager;
    private AssembliesFragment mAssembliesFragment;

    private boolean mIsTwoPane;
    private String[] mSideBarArray;
    private SideBarRVAdapter mSideBarAdapter;

    private View mNavHeaderView;

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
    ImageView mUserNavHeaderIV;
    @Nullable
    @BindView(R.id.username_nav_header_text_view)
    TextView mUsernameHeaderTV;
    @Nullable
    @BindView(R.id.logout_btn)
    Button mNavLogoutBtn;
    @BindView(R.id.content_frame)
    FrameLayout mContentFrame;

    @BindString(R.string.about_banner_url)
    String mAboutBannerUrl;
    @BindString(R.string.about_sala_title)
    String mAboutTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //noinspection HardCodedStringLiteral
        Timber.tag("LogMessage");
        // Initialize Firebase Components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mFirebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();

        mAssemblyDbRef = mFirebaseDatabase.getReference().child(ASSEMBLIES);

        if (savedInstanceState != null) {
            mUserId = savedInstanceState.getString(USER_ID);
            mUsername = savedInstanceState.getString(USER_NAME);
            mUserPhotoUrl = savedInstanceState.getString(USER_PHOTO_URL);
        }

        mIsTwoPane = getResources().getBoolean(R.bool.isTablet);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mAppBarTitle = mAboutTitle;
        mAppBarImageUrl = mAboutBannerUrl;

        if (mIsTwoPane && mSideBarRecyclerView != null) {
            mSideBarArray = getResources().getStringArray(R.array.side_bar_array);
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
            mAssembliesFragment = new AssembliesFragment();
            mFragmentManager
                    .beginTransaction()
                    .add(mContentFrame.getId(), mAssembliesFragment)
                    .commit();
        }

        setCollapsingToolbarBehavior();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // Check if user is signed in (non-null) and update UI accordingly.
                mCurrentUser = firebaseAuth.getCurrentUser();
                if (mCurrentUser != null) {
                    if (savedInstanceState == null) {
                        // already signed in
                        checkIfNewUser(mCurrentUser);
                        updateUI(mCurrentUser);
                    }
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

    private void checkIfNewUser(FirebaseUser user) {
        String userId = getUid();
        String displayName = user.getDisplayName();
        String userMail = user.getEmail();

        mDatabaseRef.child(USERS).child(userId).addListenerForSingleValueEvent
                (new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        User user1 = dataSnapshot.getValue(User.class);
                        if (user1 == null) {
                            writeNewUser(userId, displayName, userMail);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        Timber.i(getString(R.string.user_signed_in_msg));
        FirebaseUserMetadata metadata = Objects.requireNonNull(mAuth
                .getCurrentUser()).getMetadata();

        if (metadata != null) {
            if (metadata.getCreationTimestamp() == metadata.getLastSignInTimestamp()) {
                // The user is new, show them a fancy intro screen!
                writeNewUser(userId, displayName, userMail);
                showToast(getString(R.string.welcome_msg) + displayName + "!");

            } else {
                // This is an existing user, show them a welcome back screen.
                showToast(getString(R.string.welcome_back_msg) + displayName + "!");
            }
        }
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        return !mIsTwoPane;
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

        outState.putParcelable(CURRENT_USER, mCurrentUser);
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
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
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
        if (user != null) {
            // Signed in
            if (mUsernameHeaderTV != null && mUserNavHeaderIV != null) {
                mUsernameHeaderTV.setText(user.getDisplayName());

                if (user.getPhotoUrl() != null) {
                    mUserPhotoUrl = Objects.requireNonNull(user.getPhotoUrl())
                            .toString();
                    Glide.with(this)
                            .load(mUserPhotoUrl)
                            .into(mUserNavHeaderIV);
                } else {
                    Glide.with(this)
                            .load(R.drawable.baseline_account_circle_48)
                            .into(mUserNavHeaderIV);
                }
            }
        } else {
            // Signed out
            if (mUsernameHeaderTV != null && mUserNavHeaderIV != null) {
                mUsernameHeaderTV.setText(R.string.dummy_user_name);
                Glide.with(this)
                        .load(R.drawable.sala_logo_grass)
                        .into(mUserNavHeaderIV);
            }
        }
    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User();
        user.setEmail(email);
        user.setUserId(userId);
        user.setUsername(name);
        mDatabaseRef.child(USERS).child(userId).setValue(user);

    }

    public void headerLogout(View view) {
        if (view != null) {
            signOut();
        }
    }

    public void signOut() {
        AuthUI.getInstance().signOut(MainActivity.this);
    }

    private void activateDrawerItems() {
        // Handle navigation drawer click events
        if (mNavView != null) {
            mNavHeaderView = mNavView.getHeaderView(0);
            mNavLogoutBtn = mNavHeaderView.findViewById(R.id.logout_btn);
            if (mNavLogoutBtn != null) {
                mNavLogoutBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        headerLogout(v);
                    }
                });
            }
            mUsernameHeaderTV = mNavHeaderView.findViewById(R.id.username_nav_header_text_view);
            mUserNavHeaderIV = mNavHeaderView.findViewById(R.id.user_nav_header_img_view);
            updateUI(mAuth.getCurrentUser());
            mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    menuItem.setChecked(true);
                    if (mDrawer != null) {
                        mDrawer.closeDrawer(GravityCompat.START, true);
                    }
                    mFragment = null;
                    mBundle = new Bundle();
                    int position = menuItem.getItemId();
                    if (position == R.id.about_nav) {
                        mFragment = new AboutSalaFragment();
                        mAppBarTitle = mAboutTitle;
                        mAppBarImageUrl = mAboutBannerUrl;
                    } else if (position == R.id.assemblies_nav) {
                        mFragment = new AssembliesFragment();
                        mAppBarTitle = mAboutTitle;
                        mAppBarImageUrl = mAboutBannerUrl;
                    } else if (position == R.id.program_nav) {
                        showToast(R.string.program_msg);
                    } else if (position == R.id.lyrics_nav) {
                        showToast(R.string.lyrics_msg);
                    } else if (position == R.id.speaker_nav) {
                        showToast(R.string.speaker_bio_msg);
                    } else if (position == R.id.help_often_nav) {
                        showToast(R.string.help_often_msg);
                    } else if (position == R.id.live_better_nav) {
                        showToast(R.string.live_better_msg);
                    } else if (position == R.id.chat_nav) {
                        showToast(R.string.sala_chat_msg);
                    } else if (position == R.id.insta_link_nav) {
                        showToast(R.string.insta_msg);
                    } else if (position == R.id.facebook_link_nav) {
                        showToast(R.string.facebook_msg);
                    } else if (position == R.id.twitter_link_nav) {
                        showToast(R.string.twitter_msg);
                    } else if (position == R.id.site_link_nav) {
                        showToast(R.string.website_msg);
                    } else if (position == R.id.logout_nav) {
                        signOut();
                        return true;
                    }
                    if (mFragment != null) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_frame, mFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
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
                // For example, swap UI fragments here
                mFragment = null;
                mBundle = new Bundle();
                if (position == mSideBarAdapter.getItemId(0)) {
                    mFragment = new AboutSalaFragment();
                    mAppBarTitle = mAboutTitle;
                    mAppBarImageUrl = mAboutBannerUrl;
                } else if (position == mSideBarAdapter.getItemId(1)) {
                    mFragment = new AssembliesFragment();
                    mAppBarTitle = mAboutTitle;
                    mAppBarImageUrl = mAboutBannerUrl;
                } else if (position == mSideBarAdapter.getItemId(2)) {
                    showToast(R.string.program_msg);
                } else if (position == mSideBarAdapter.getItemId(3)) {
                    showToast(R.string.lyrics_msg);
                } else if (position == mSideBarAdapter.getItemId(4)) {
                    showToast(R.string.speaker_bio_msg);
                } else if (position == mSideBarAdapter.getItemId(5)) {
                    showToast(R.string.help_often_msg);
                } else if (position == mSideBarAdapter.getItemId(6)) {
                    showToast(R.string.live_better_msg);
                } else if (position == mSideBarAdapter.getItemId(7)) {
                    showToast(R.string.sala_chat_msg);
                    // TODO: Create intents for Instagram, Facebook and Twitter
                } else if (position == mSideBarAdapter.getItemId(8)) {
                    showToast(R.string.insta_msg);
                } else if (position == mSideBarAdapter.getItemId(9)) {
                    showToast(R.string.facebook_msg);
                } else if (position == mSideBarAdapter.getItemId(10)) {
                    showToast(R.string.twitter_msg);
                } else if (position == mSideBarAdapter.getItemId(11)) {
                    showToast(R.string.website_msg);
                } else if (position == mSideBarAdapter.getItemId(12)) {
                    signOut();
                }
                if (mFragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, mFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
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
            mCollapsingToolbarLayout.setTitleEnabled(true);
            mCollapsingToolbarLayout.setTitle(mAppBarTitle);
            mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    Fade fade = new Fade();
                    if (Math.abs(verticalOffset) == mAppBarLayout.getTotalScrollRange()) {
                        // Collapsed
                        TransitionManager.beginDelayedTransition
                                (mAppBarLayout, fade);
                        mToolbar.setVisibility(View.VISIBLE);
                    } else if (verticalOffset == 0) {
                        // Expanded
                        mToolbar.setVisibility(View.GONE);
                    } else {
                        // Mid-scroll
                        TransitionManager.beginDelayedTransition
                                (mToolbar, fade);
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
