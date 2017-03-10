package com.example.prabowo.tutorpedia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class IsiKonsultasi extends AppCompatActivity implements View.OnClickListener {
    private ImageView IVisikonsultasisoal;
    private static int posisiItemRecycler;
    private List<ListItemTutor> mListItemTutors;
    private TextView TVisikonsultasijudul;
    private TextView TVisikonsultasidesc;
    private EditText ETtambahkomentar;
    private ImageView BTtambahkomentar;
    public static long jumlah;
    private static int filter;
    String Matkuldis, Jenisdis;
    public static int langkahkomen = 1;
    String CV;
    int i = 0;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();
    private List<Komentar> komenku = new ArrayList<Komentar>();
    private DatabaseReference databaseReference;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_konsultasi);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        ETtambahkomentar = (EditText) findViewById(R.id.ETtambahkomentar);
        BTtambahkomentar = (ImageView) findViewById(R.id.BTmasukkankomentar);
        BTtambahkomentar.setOnClickListener(this);


        IVisikonsultasisoal = (ImageView) findViewById(R.id.IVisikonsultasisoal);
        TVisikonsultasijudul = (TextView) findViewById(R.id.TVisikonsultasijudul);
        TVisikonsultasidesc = (TextView) findViewById(R.id.TVisikonsultasidesc);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            posisiItemRecycler = extras.getInt("PosisiItemRecycler");
        }

        System.out.print("Urutan ke " + posisiItemRecycler);
        filter = 1;
        populatekomentarlist();

        registerClickCallBack();

    }

    private void registerClickCallBack() {
        ListView list = (ListView) findViewById(R.id.demooListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                Komentar klikkomen = komenku.get(position);
                String Message = " Ini posisi " + position + klikkomen.getKomen();
                Toast.makeText(IsiKonsultasi.this, Message, Toast.LENGTH_LONG).show();

            }
        });

    }


    private void populatekomentarlist() {


        Bundle extras3 = getIntent().getExtras();
        Matkuldis = extras3.getString("Matkuldis");
        Bundle extras4 = getIntent().getExtras();
        Jenisdis = extras4.getString("Jenisdis");


        DatabaseReference ref = mRootref.child(Jenisdis).child(Matkuldis).child("Post" + (posisiItemRecycler)).child("Komentar");

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (filter == 1) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        komenku.add(new Komentar(postSnapshot.child("pengirim").getValue().toString(),
                                postSnapshot.child("desc").getValue().toString(),
                                postSnapshot.child("img").getValue().toString()));

                    }
                }


                populatelistview();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }

    private void populatelistview() {
        ArrayAdapter<Komentar> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.demooListView);
        list.setAdapter(adapter);

    }

    private class MyListAdapter extends ArrayAdapter<Komentar> {

        public MyListAdapter() {
            super(IsiKonsultasi.this, R.layout.item_view, komenku);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }
            Komentar currentKomentar = komenku.get(position);
            /*ImageView gambarkomen = (ImageView) itemView.findViewById(R.id.IVkomen);
            gambarkomen.setImageURI(Uri.parse("https://openclipart.org/download/10941/TzeenieWheenie-red-green-OK-not-OK-Icons-1.svg"));*/

            TextView pengirim = (TextView) itemView.findViewById(R.id.TVpengirimkomen);
            pengirim.setText(currentKomentar.getPengirim());

            TextView komentar = (TextView) itemView.findViewById(R.id.TVisikomen);
            komentar.setText(currentKomentar.getKomen());

            return itemView;
        }


    }


    @Override
    protected void onStart() {
        super.onStart();

        Bundle extras3 = getIntent().getExtras();
        Matkuldis = extras3.getString("Matkuldis");
        Bundle extras4 = getIntent().getExtras();
        Jenisdis = extras4.getString("Jenisdis");

        System.out.print("Dis : " + Matkuldis + Jenisdis);
        DatabaseReference ref = mRootref.child(Jenisdis).child(Matkuldis);


        ref.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snapshot) {

                System.out.println("There are " + snapshot.getChildrenCount() + " shout messages");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    ListItemKonsultasi listItem = new ListItemKonsultasi(postSnapshot.child("judul").getValue().toString(),
                            postSnapshot.child("img").getValue().toString(),
                            postSnapshot.child("deskripsi").getValue().toString());


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

        if (v == BTtambahkomentar) {
            filter = 2;

            Bundle extras3 = getIntent().getExtras();
            Matkuldis = extras3.getString("Matkuldis");
            Bundle extras4 = getIntent().getExtras();
            Jenisdis = extras4.getString("Jenisdis");
            langkahkomen = 1;
            System.out.println("Langkah Komen nya : " + langkahkomen);

            DatabaseReference ref = mRootref.child("JumlahKomentarBenar").child(Matkuldis).child("Post" + posisiItemRecycler);
            ref.addValueEventListener(new ValueEventListener() {

                                          @Override
                                          public void onDataChange(DataSnapshot snapshot) {
                                              if (langkahkomen == 1) {
                                                  Integer jumlah = Integer.parseInt(snapshot.getValue().toString());
                                                  databaseReference.child("JumlahKomentarBenar").child(Matkuldis).child("Post" + posisiItemRecycler).setValue(jumlah + 1);
                                                  System.out.println(jumlah);
                                                  databaseReference.child(Jenisdis).child(Matkuldis).child("Post" + (posisiItemRecycler)).
                                                          child("Komentar").child("Komentar" + (jumlah)).child("desc").setValue(ETtambahkomentar.getText().toString());
                                                  databaseReference.child(Jenisdis).child(Matkuldis).child("Post" + (posisiItemRecycler)).
                                                          child("Komentar").child("Komentar" + (jumlah)).child("pengirim").setValue("Anonymous");
                                                  databaseReference.child(Jenisdis).child(Matkuldis).child("Post" + (posisiItemRecycler)).
                                                          child("Komentar").child("Komentar" + (jumlah)).child("img").setValue("xxx");
                                                  langkahkomen++;
                                              }


                                          }


                                          @Override
                                          public void onCancelled(DatabaseError databaseError) {

                                          }

                                      }
            );


        }

    }
}



