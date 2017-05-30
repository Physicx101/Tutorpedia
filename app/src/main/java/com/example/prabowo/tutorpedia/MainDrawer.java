package com.example.prabowo.tutorpedia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.example.prabowo.tutorpedia.CekSoal.FirebaseImageLoader;
import com.google.android.gms.maps.MapFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private FirebaseAuth firebaseAuth;
    private FirebaseStorage storage;
    private StorageReference mStorageRef;
    private TextView userEmail;
    private TextView userName;
    private ImageView userImage;
    private DatabaseReference databaseReference;
    private String UID, nama;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);


        Bundle extras =  getIntent().getExtras();

        //  Declare a new thread to do a preference check
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                Bundle extras =  getIntent().getExtras();
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

                //  If the activity has never started before...
                if (isFirstStart) {

                    if (extras != null) {
                        String NamaPengguna = extras.getString("namaku");
                        firebaseAuth = FirebaseAuth.getInstance();
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        System.out.print(user.getUid());
                        databaseReference = FirebaseDatabase.getInstance().getReference();
                        databaseReference.child("User").child(user.getUid()).child("nama").setValue(NamaPengguna);
                        databaseReference.child("User").child(user.getUid()).child("Tes").child("Tes 1").child("judul").setValue("Tes 1");
                        databaseReference.child("User").child(user.getUid()).child("Tes").child("Tes 1").child("nilai").setValue(0);
                        databaseReference.child("User").child(user.getUid()).child("Pangkat").setValue(0);
                        databaseReference.child("User").child(user.getUid()).child("Point").setValue(0);
                        databaseReference.child("Tes").child("Tes 1").child(user.getUid()).setValue(0);
                    }

                    //  Launch app intro
                    Intent i = new Intent(MainDrawer.this, IntroActivity.class);
                    startActivity(i);

                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);

                    //  Apply changes
                    e.apply();
                }
            }
        });

        // Start the thread
        t.start();


        if (extras != null) {
            String NamaPengguna = extras.getString("namaku");
            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser user = firebaseAuth.getCurrentUser();
            System.out.print(user.getUid());
            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("User").child(user.getUid()).child("nama").setValue(NamaPengguna);
            databaseReference.child("User").child(user.getUid()).child("Tes").child("Tes 1").child("judul").setValue("Tes 1");
            databaseReference.child("User").child(user.getUid()).child("Tes").child("Tes 1").child("nilai").setValue(0);
            databaseReference.child("User").child(user.getUid()).child("Pangkat").setValue(0);
            databaseReference.child("User").child(user.getUid()).child("Point").setValue(0);
            databaseReference.child("User").child(user.getUid()).child("Tutor").setValue(0);
            databaseReference.child("Tes").child("Tes 1").child(user.getUid()).setValue(0);
        }


        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        userEmail = (TextView) header.findViewById(R.id.email_user);
        userName = (TextView) header.findViewById(R.id.user_profile_name);
        userImage = (ImageView) header.findViewById(R.id.user_profile_photo);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        UID = user.getUid();
        DatabaseReference ref = mRootref.child("User").child(user.getUid());
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userName.setText(dataSnapshot.child("nama").getValue().toString());
                nama = dataSnapshot.child("nama").getValue().toString();
                userEmail.setText(user.getEmail());
                storage = FirebaseStorage.getInstance();

                mStorageRef = storage.getReferenceFromUrl("gs://tutorpedia-17ba0.appspot.com/FotoProfil/");
                StorageReference foto = mStorageRef.child(UID + "PP" + ".jpg");

                Glide.with(getApplicationContext())
                        .using(new FirebaseImageLoader())
                        .load(foto)
                        .signature(new StringSignature(Long.toString(System.currentTimeMillis()))).centerCrop()
                        .into(userImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new MapsFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_math) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fm = getSupportFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fm.beginTransaction().replace(R.id.content_frame, new MapsFragment()).commit();

        } else if (id == R.id.nav_event) {
            fm.beginTransaction().replace(R.id.content_frame, new EventFragment()).commit();
        } else if (id == R.id.nav_materi) {
            fm.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
        } else if (id == R.id.nav_akun) {
            fm.beginTransaction().replace(R.id.content_frame, new ProfileTestFragment()).commit();
        } else if (id == R.id.nav_logout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, CreditActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
