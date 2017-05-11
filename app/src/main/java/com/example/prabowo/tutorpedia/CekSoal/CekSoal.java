package com.example.prabowo.tutorpedia.CekSoal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.prabowo.tutorpedia.EventFragment;
import com.example.prabowo.tutorpedia.MainActivity;
import com.example.prabowo.tutorpedia.MainDrawer;
import com.example.prabowo.tutorpedia.PointActivity;
import com.example.prabowo.tutorpedia.R;
import com.example.prabowo.tutorpedia.Soal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CekSoal extends AppCompatActivity {

    private GridLayoutManager lLayout;
    public static double score = 0;
    public static  int selesai = 0;
    public static String NilaiAkhir;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private static String points;
    private static int point;
    private static int nilai;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_soal);



        List<ItemObject> rowListItem = getAllItemList();


        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        //rView.setHasFixedSize(true);
        rView.setLayoutManager(new GridLayoutManager(this, 4));

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(CekSoal.this, rowListItem);
        rView.setAdapter(rcAdapter);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        DatabaseReference ref = mRootref.child("User").child(user.getUid()).child("Point");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                point = dataSnapshot.getValue().hashCode();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_refresh){
            Toast.makeText(CekSoal.this, "Refresh App", Toast.LENGTH_LONG).show();
        }
        if(id == R.id.action_new){
            Toast.makeText(CekSoal.this, "Create Text", Toast.LENGTH_LONG).show();
        }
        */
        return super.onOptionsItemSelected(item);
    }

    private List<ItemObject> getAllItemList(){

        List<ItemObject> allItems = new ArrayList<ItemObject>();
        for (int i=1 ; i<41;i++){
            allItems.add(new ItemObject("Nomor "+i));
        }
        for (int i = 1;i<41;i++){
        if (Soal.warna[i]!=0) {
            if (Soal.ragu[i]==0){
            allItems.set(i-1,new ItemObject("V"));}
            else  {allItems.set(i-1,new ItemObject("?"));}
        }}
        return allItems;
    }

    public void kumpul (View v){


            for (int i = 1; i < 41; i++) {
                score = score + Soal.soal[i];
            }


            score = score * 2.5;
            NilaiAkhir = String.valueOf(score);
        score = score*0.5;
        nilai = (int) score;
        point = point + nilai;

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("User").child(user.getUid()).child("Tes").child("Tes 1").child("nilai").setValue("Nilai :" + NilaiAkhir);
            databaseReference.child("User").child(user.getUid()).child("Tes").child("Tes 1").child("judul").setValue("Matematika");
            databaseReference.child("Tes").child("Tes 1").child(user.getUid()).setValue(1);
            databaseReference.child("User").child(user.getUid()).child("Point").setValue(point);

            finishAffinity();
            Intent i = new Intent(this, MainDrawer.class);
            startActivity(i);

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


