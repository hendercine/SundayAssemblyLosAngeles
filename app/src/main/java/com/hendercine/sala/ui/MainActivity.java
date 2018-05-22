/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 5/21/18 1:08 PM
 */

package com.hendercine.sala.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hendercine.sala.R;
import com.hendercine.sala.models.User;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements AboutSalaFragment.OnFragmentSelectedListener{

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN = 1;

    private String mUsername;
    private User mUser;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private ActionBarDrawerToggle mToggle;
    private ActionBar mActionBar;

    private FragmentManager mFragmentManager;
    private AboutSalaFragment mAboutSalaFragment;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.content_frame)
    FrameLayout mContentFrame;
    @BindView(R.id.collapsing_toolbar_backdrop_img)
    ImageView collapsingToolbarBackDrop;
//    @BindView(R.id.toolbar_main)
//    android.support.v7.widget.Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        mFragmentManager = getSupportFragmentManager();
        mAboutSalaFragment = new AboutSalaFragment();
        mFragmentManager
                .beginTransaction()
                .add(mContentFrame.getId(), mAboutSalaFragment)
                .commit();
        Glide.with(this)
                .load(getString(R.string.about_banner_url))
                .into(collapsingToolbarBackDrop);

        mToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        activateDrawerItems();

//        authorizeUser();

    }

    private void activateDrawerItems() {
        // Handle navigation drawer click events
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawer.closeDrawer(GravityCompat.START, true);

                // TODO: Add code here to update the UI based on the item selected
                // For example, swap UI fragments here
                int id = menuItem.getItemId();
                Fragment fragment = null;
                Bundle bundle = new Bundle();
                if (id == R.id.about_nav) {
                    fragment = new AboutSalaFragment();
//                    Toast.makeText(getApplicationContext(),
//                            "This will display AboutSALAFragment",
//                            Toast.LENGTH_SHORT).show();
                } else if (id == R.id.program_nav) {
//                    fragment = new EventProgramFragment();
                    Toast.makeText(getApplicationContext(),
                            "This will display EventProgramFragment",
                            Toast.LENGTH_SHORT).show();
                } else if (id == R.id.lyrics_nav) {
//                    fragment = new LyricsFragment();
                    Toast.makeText(getApplicationContext(),
                            "This will display LyricsFragment",
                            Toast.LENGTH_SHORT).show();
                } else if (id == R.id.speaker_nav) {
//                    fragment = new SpeakerFragment();
                    Toast.makeText(getApplicationContext(),
                            "This will display SpeakerFragment",
                            Toast.LENGTH_SHORT).show();
                } else if (id == R.id.future_assemblies_nav) {
//                    fragment = new FutureAssembliesFragment();
                    Toast.makeText(getApplicationContext(),
                            "This will display FutureAssembliesFragment",
                            Toast.LENGTH_SHORT).show();
                } else if (id == R.id.help_often_nav) {
//                    fragment = new HelpOftenFragment();
                    Toast.makeText(getApplicationContext(),
                            "This will display HelpOftenFragment",
                            Toast.LENGTH_SHORT).show();
                } else if (id == R.id.live_better_nav) {
//                    fragment = new LiveBetterFragment();
                    Toast.makeText(getApplicationContext(),
                            "This will display LiveBetterFragment",
                            Toast.LENGTH_SHORT).show();
                } else if (id == R.id.chat_nav) {
//                    fragment = new ChatFragment();
                    Toast.makeText(getApplicationContext(),
                            "This will display ChatFragment",
                            Toast.LENGTH_SHORT).show();
                    // TODO: Create intents for Instagram, Facebook and Twitter
                } else if (id == R.id.insta_link_nav) {
                    Toast.makeText(getApplicationContext(),
                            "This will open Instagram",
                            Toast.LENGTH_SHORT).show();
                } else if (id == R.id.facebook_link_nav) {
                    Toast.makeText(getApplicationContext(),
                            "This will open Facebook",
                            Toast.LENGTH_SHORT).show();
                } else if (id == R.id.twitter_link_nav) {
                    Toast.makeText(getApplicationContext(),
                            "This will open Twitter",
                            Toast.LENGTH_SHORT).show();
                } else if (id == R.id.site_link_nav) {
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

    private void authorizeUser() {
        // Implement Firebase Auth
        mUsername = ANONYMOUS;
        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // user is signed in
                    onSignedInInitialize(user.getDisplayName());
                } else {
                    // user is signed out
                    onSignedOutCleanup();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.EmailBuilder().build(),
                                            new AuthUI.IdpConfig.PhoneBuilder().build(),
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.FacebookBuilder().build(),
                                            new AuthUI.IdpConfig.TwitterBuilder().build()
                                    ))
                                    .build(),
                            RC_SIGN_IN);
                }

            }
        };
    }
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
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    mUser = dataSnapshot.getValue(User.class);
                    if (mUser != null) {
                        mUsername = mUser.getUsername();
                    }
//                    FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
//                    mMessageAdapter.add(friendlyMessage);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void detachDatabaseReadListener() {
        if (mChildEventListener != null) {
            mDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }

    private void readAndWriteRealtimeDatabase() {
        // TODO: Refactor this method to store appropriate data - chat or user
        // data or whatever.

        // Write a message to the database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("message");

        mDatabaseReference.setValue("Hello, world!");

        // Read from the database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
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

    @Override
    public void onFragmentSelected(Uri uri) {

    }
}
