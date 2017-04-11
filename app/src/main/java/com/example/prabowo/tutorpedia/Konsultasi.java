package com.example.prabowo.tutorpedia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Konsultasi extends AppCompatActivity implements View.OnClickListener {

    public String Matkul;
    public String Jenis;
    public static String a,b;

    private FirebaseDatabase eventfirebasedatabase;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayout linear;
    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton fab;






    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    private List<ListItemKonsultasi> listItems;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsultasi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Konsultasi");




       Bundle extras = getIntent().getExtras();
        Bundle extras2=getIntent().getExtras();


        a = extras.getString("Matkul");
        b = extras2.getString("Jenis");

        recyclerView = (RecyclerView) findViewById(R.id.recycleViewKonsultasi);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        listItems = new ArrayList<>();



        fab = (FloatingActionButton) findViewById(R.id.fabkonsul);
        fab.setOnClickListener(this);



        tambahInfo();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void tambahInfo() {




        Bundle extras = getIntent().getExtras();
        Matkul = extras.getString("Matkul");
        Bundle extras2=getIntent().getExtras();
        Jenis = extras2.getString("Jenis");

        final ProgressDialog Dialog = new ProgressDialog(Konsultasi.this);
        Dialog.setMessage("Fetching file .... ");
        Dialog.show();
        DatabaseReference event = mRootref.child(Jenis).child(Matkul);
        event.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //long max = dataSnapshot.getChildrenCount();
                    //for(int i = 1; i<=max ; i++){
                    ListItemKonsultasi listItem = new ListItemKonsultasi(postSnapshot.child("judul").getValue().toString(),
                            postSnapshot.child("img").getValue().toString(),
                            postSnapshot.child("deskripsi").getValue().toString(),
                            postSnapshot.child("foto").getValue().toString(),
                            postSnapshot.child("poster").getValue().toString());
                    listItems.add(listItem);
                    Dialog.hide();


                }


                adapter = new MyAdapterKonsultasi(listItems, getApplicationContext());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }


    @Override
    public void onClick(View v) {
        if(v == fab){
            Bundle extras = getIntent().getExtras();
            Matkul = extras.getString("Matkul");

            Intent intent = new Intent(Konsultasi.this,FormTambahKonsultasi.class);
            intent.putExtra("Matkul",Matkul);
            startActivity(intent);


        }

    }
}



