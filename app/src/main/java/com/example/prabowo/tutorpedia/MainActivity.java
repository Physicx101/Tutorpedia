package com.example.prabowo.tutorpedia;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;


public class MainActivity extends AppCompatActivity {
    String NamaPengguna;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private EditText ETnamapengguna;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                        NamaPengguna = extras.getString("namaku");
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
                    Intent i = new Intent(MainActivity.this, IntroActivity.class);
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


       /* Bundle extras = getIntent().getExtras();
        if (extras != null) {
            NamaPengguna = extras.getString("namaku");
            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser user = firebaseAuth.getCurrentUser();
            System.out.print(user.getUid());
            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("User").child(user.getUid()).child("nama").setValue(NamaPengguna);
            databaseReference.child("User").child(user.getUid()).child("Tes").child("Tes 1").child("judul").setValue("Tes 1");
            databaseReference.child("User").child(user.getUid()).child("Tes").child("Tes 1").child("nilai").setValue(0);
            databaseReference.child("User").child(user.getUid()).child("Pangkat").setValue(0);
            databaseReference.child("User").child(user.getUid()).child("Point").setValue(0);
            databaseReference.child("Tes").child("Tes 1").child(user.getUid()).setValue(0); */

            /*(String alert1 = "Selamat Datang di Tutorpedia";
            //String alert2 = "Kode hadiah";
            //String alert3 = "Kode user: " + user.getUid().toString();
            String alert4 = "Ikuti Tutorial Berikut Supaya Anda Tahu Fitur Fitur Di Sini";

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage(alert1 +"\n"+"\n"+"\n" + alert4);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Tutorial",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(MainActivity.this,Tutor1.class));
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();

            }*/


            if (savedInstanceState == null) {


                HomeFragment homeFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).addToBackStack(null).commit();


                BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
                bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
                    @Override
                    public void onTabSelected(@IdRes int tabId) {
                        switch (tabId) {
                            case R.id.tab_home:
                                HomeFragment homeFragment = new HomeFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).addToBackStack(null).commit();
                                break;
                            case R.id.tab_event:
                                EventFragment eventFragment = new EventFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, eventFragment).addToBackStack(null).commit();
                                break;
                            case R.id.tab_find:
                                FindFragment findFragment = new FindFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, findFragment).addToBackStack(null).commit();
                                break;
                            case R.id.tab_account:
                                ProfileTestFragment profileFragment = new ProfileTestFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).addToBackStack(null).commit();
                                break;
                        }
                    }
                });
            }
        }








    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apa anda ingin keluar ?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) { finish(); System.exit(0);
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }
}
