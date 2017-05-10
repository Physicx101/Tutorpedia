package com.example.prabowo.tutorpedia;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;


public class IsiTutor extends AppCompatActivity implements View.OnClickListener {
    private ImageView IVfototutor;
    int posisiItemRecycler;
    private List<ListItemTutor> mListItemTutors;
    private TextView TVnamatutor;
    private TextView TVisievent;
    private TextView TVdesctutor;
    private TextView TVemailtutor;
    private TextView TVisieventagama;
    private TextView TVnomortutor;
    private TextView TVasaltutor;
    //private Button BTgetcv;
    private Button BTrequest;
    private FloatingActionButton fab,fab2;
    private FirebaseAuth firebaseAuth;
    private String nomorTutor;
    String CV;
    int i = 0;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        IVfototutor = (ImageView) findViewById(R.id.IVisievent);
        TVnamatutor = (TextView) findViewById(R.id.TVnamatutor);
        TVdesctutor = (TextView) findViewById(R.id.TVdesctutor);
        TVemailtutor = (TextView) findViewById(R.id.TV_email_tutor);
        TVasaltutor = (TextView) findViewById(R.id.TV_asal_tutor);
        TVnomortutor = (TextView) findViewById(R.id.TV_nomor_tutor);
        BTrequest = (Button) findViewById(R.id.BTrequest);
        //BTgetcv = (Button) findViewById(R.id.BTgetcv);
        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        //fab2 = (FloatingActionButton) findViewById(R.id.floatingActionButton2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            posisiItemRecycler = extras.getInt("kodetutor");
        }

        /*BTgetcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(CV));
                startActivity(intent);
            }
        });*/
        //fab2.setOnClickListener(this);


        DatabaseReference ref = mRootref.child("Ideafuse");
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        ref.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(final DataSnapshot snapshot) {
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Integer.parseInt(snapshot.child("Respond").getValue().toString())==0){
                            //fab.setVisibility(View.GONE);
                            Toast.makeText(IsiTutor.this, "Harap buat request terlebih dahulu", Toast.LENGTH_LONG).show();
                        } else {
                            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                            sendIntent.setData(Uri.parse("sms:" + nomorTutor));
                            startActivity(sendIntent);
                        }
                    }
                });



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();


        DatabaseReference ref = mRootref.child("Mentor");
        DatabaseReference ref2 = mRootref.child("Mentor").child("Guru" + (posisiItemRecycler + 1));

        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nomorTutor = dataSnapshot.child("kontak").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snapshot) {

                System.out.println("There are " + snapshot.getChildrenCount() + " shout messages");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    ListItemTutor listItemTutor = new ListItemTutor(postSnapshot.child("nama").getValue().toString(),
                            postSnapshot.child("email").getValue().toString(),
                            postSnapshot.child("img").getValue().toString(),
                            postSnapshot.child("lokasi").getValue().toString(),
                            postSnapshot.child("kontak").getValue().toString(),
                            postSnapshot.child("deskripsi").getValue().toString(),
                            postSnapshot.child("linkcv").getValue().toString());
                    // mListItemTutors.add(listItemTutor);
//                    System.out.println(i + " " + post.getTitle() + " - " + post.getUsername());
//                    post.getDate();
//                    post.getTime();
//                    post.getDesc();
//                    post.getTitle();
//                    post.getUsername();
//                    post.getPhone();


                    if (i == posisiItemRecycler) {

//

                        TVnamatutor.setText(listItemTutor.getNama());
                        TVasaltutor.setText(listItemTutor.getAsal());
                        TVemailtutor.setText(listItemTutor.getEmail());
                        TVdesctutor.setText(listItemTutor.getDeskripsi());
                        TVnomortutor.setText(listItemTutor.getNohp());
                        Picasso.with(getApplicationContext())
                                .load(listItemTutor.getImageUrl())
                                .into(IVfototutor);
                        CV = listItemTutor.getLinkcv().toString();


                    }

                    i++;
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

    @Override
    public void onClick(View view) {

       /* if (view == fab) {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("sms:" + nomorTutor));
            startActivity(sendIntent);
        }*/

        if (view == BTrequest) {

            String alert1 = "Request Telah Dikirim, Tunggu Konfirmasi";


            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage(alert1 );
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                           finish();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();

            mRootref.child("Ideafuse").child("Request").child("no").setValue(1);
            mRootref.child("Ideafuse").child("Request").child("yes").setValue(1);
        }
    }


 }


