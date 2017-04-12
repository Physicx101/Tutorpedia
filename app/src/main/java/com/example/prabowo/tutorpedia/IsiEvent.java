package com.example.prabowo.tutorpedia;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;


public class IsiEvent extends AppCompatActivity implements View.OnClickListener{
    private ImageView IVisievent;
    int posisiItemRecycler;
    private List<ListItemTutor> mListItemTutors;
    private TextView TVisieventnama;
    private TextView TVisievent;
    private TextView TVisieventdesc;
    private TextView TVisieventlahir;
    private TextView TVisieventagama;
    private TextView TVisieventkontak;
    private TextView TVisieventasal;
    private Button BTgetcv;
    private FloatingActionButton fab;
    String CV;
    int i = 0;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_event);


        IVisievent = (ImageView) findViewById(R.id.IVisievent);
        TVisieventnama = (TextView) findViewById(R.id.TVisieventnama);
        TVisieventdesc = (TextView) findViewById(R.id.TVisieventdesc);
        TVisieventlahir= (TextView) findViewById(R.id.TVisieventlahir);
        TVisieventasal = (TextView) findViewById(R.id.TVisieventasal);
        TVisieventkontak = (TextView) findViewById(R.id.TVisieventkontak);
        BTgetcv = (Button) findViewById(R.id.BTgetcv);
        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             posisiItemRecycler = extras.getInt("PosisiItemRecycler");}

        BTgetcv.setOnClickListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                startActivity(sendIntent);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();



            DatabaseReference ref = mRootref.child("Mentor");

            ref.addValueEventListener(new ValueEventListener() {




                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    System.out.println("There are " + snapshot.getChildrenCount() + " shout messages");
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        ListItemTutor listItemTutor = new ListItemTutor(postSnapshot.child("nama").getValue().toString(),
                                postSnapshot.child("tanggallahir").getValue().toString(),
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

                            TVisieventnama.setText(listItemTutor.getNama());
                            TVisieventasal.setText(listItemTutor.getAsal());
                            //TVisieventlahir.setText(listItemTutor.getTanggallahir());
                            TVisieventdesc.setText(listItemTutor.getDeskripsi());
                            TVisieventkontak.setText(listItemTutor.getNohp());
                            Picasso.with(getApplicationContext())
                                    .load(listItemTutor.getImageUrl())
                                    .into(IVisievent);
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
    public void onClick(View v) {
        if(v == BTgetcv) {
           Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(CV));
            startActivity(intent);
        }
    }
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apa anda ingin keluar ?")
                .setCancelable(false)
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) { finish(); System.exit(0);
                    }
                })
                .setNegativeButton("Enggak", null)
                .show();
    }
}



