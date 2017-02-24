package com.example.prabowo.tutorpedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prabowo.tutorpedia.BottomNavigationHelper;

import com.example.prabowo.tutorpedia.ListItem;
import com.example.prabowo.tutorpedia.MainActivity;

import com.example.prabowo.tutorpedia.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;


public class IsiKonsultasi extends AppCompatActivity implements View.OnClickListener{
    private ImageView IVisikonsultasisoal;
    int posisiItemRecycler;
    private List<ListItem> listItems;
    private TextView TVisikonsultasijudul;
    private TextView TVisikonsultasidesc;
    String Matkuldis,Jenisdis;
    String CV;
    int i = 0;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_konsultasi);

        IVisikonsultasisoal = (ImageView) findViewById(R.id.IVisikonsultasisoal);
        TVisikonsultasijudul = (TextView) findViewById(R.id.TVisikonsultasijudul);
        TVisikonsultasidesc = (TextView) findViewById(R.id.TVisikonsultasidesc);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            posisiItemRecycler = extras.getInt("PosisiItemRecycler");}

    }


    @Override
    protected void onStart() {
        super.onStart();

        Bundle extras3 = getIntent().getExtras();
        Matkuldis = extras3.getString("Matkuldis");
        Bundle extras4=getIntent().getExtras();
        Jenisdis = extras4.getString("Jenisdis");

        System.out.print("Dis : " + Matkuldis + Jenisdis );



        DatabaseReference ref = mRootref.child(Jenisdis).child(Matkuldis);

        ref.addValueEventListener(new ValueEventListener() {




            @Override
            public void onDataChange(DataSnapshot snapshot) {

                System.out.println("There are " + snapshot.getChildrenCount() + " shout messages");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    ListItemKonsultasi listItem = new ListItemKonsultasi(postSnapshot.child("judul").getValue().toString(),
                            postSnapshot.child("img").getValue().toString(),
                            postSnapshot.child("deskripsi").getValue().toString());
                    // listItems.add(listItem);
//                    System.out.println(i + " " + post.getTitle() + " - " + post.getUsername());
//                    post.getDate();
//                    post.getTime();
//                    post.getDesc();
//                    post.getTitle();
//                    post.getUsername();
//                    post.getPhone();


                    if (i == posisiItemRecycler) {

//

                        TVisikonsultasijudul.setText(listItem.getJudulkonsultasi());
                        TVisikonsultasidesc.setText(listItem.getDeskripsikonsultasi());
                        Picasso.with(getApplicationContext())
                                .load(listItem.getImageUrlkonsultasi())
                                .into(IVisikonsultasisoal);





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

    }
}



