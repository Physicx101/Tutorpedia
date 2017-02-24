package com.example.prabowo.tutorpedia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Materi extends AppCompatActivity {

    private FirebaseDatabase eventfirebasedatabase;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayout linear;
    private LinearLayoutManager linearLayoutManager;
    String Matkul;
    String Jenis;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    private List<ListItemMateri> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        recyclerView = (RecyclerView) findViewById(R.id.recycleViewMateri);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        linear = (LinearLayout) findViewById(R.id.LLayoutmateri);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        listItems = new ArrayList<>();
        tambahInfo();
    }








    public void tambahInfo() {

        Bundle extras = getIntent().getExtras();
        Matkul = extras.getString("Matkul");
        Bundle extras2=getIntent().getExtras();
        Jenis = extras2.getString("Jenis");


        DatabaseReference event = mRootref.child(Jenis).child(Matkul);
        event.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //long max = dataSnapshot.getChildrenCount();
                    //for(int i = 1; i<=max ; i++){
                    ListItemMateri listItem = new ListItemMateri(postSnapshot.child("judul").getValue().toString(),
                            postSnapshot.child("img").getValue().toString(),
                            postSnapshot.child("deskripsi").getValue().toString(),
                            postSnapshot.child("link").getValue().toString());
                    listItems.add(listItem);


                }

                adapter = new MyAdapterMateri(listItems, getApplicationContext());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }



}