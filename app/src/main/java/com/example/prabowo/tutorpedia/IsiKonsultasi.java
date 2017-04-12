package com.example.prabowo.tutorpedia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.prabowo.tutorpedia.CekSoal.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class IsiKonsultasi extends AppCompatActivity implements View.OnClickListener{
    private ImageView IVisikonsultasisoal;
    private ImageView IVfotoposter;
    public static int posisiItemRecycler;
    private List<Komentar> mListItemKomen = new ArrayList<>();
    private TextView TVisikonsultasijudul;
    private TextView TVisikonsultasidesc;
    private TextView TVnamaposter;
    private EditText ETtambahkomentar;
    private Button BTtambahkomentar;
    private FirebaseAuth firebaseAuth;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private Button BTmantap;
    public static long jumlah;
    private static int filter;
    public static int langkahkomen = 1;
    public static String pengirim;
    private static int pointsa;
    String CV;
    int i = 0;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();
   //private List<Komentar> komenku = new ArrayList<Komentar>();
    private DatabaseReference databaseReference;;
    private StorageReference mStorageRef;
    private FirebaseStorage storage;
    private boolean isImageFull;
    public static String Matkuldis,Jenisdis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_konsultasi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Konsultasi");


        databaseReference = FirebaseDatabase.getInstance().getReference();



        ETtambahkomentar = (EditText) findViewById(R.id.ETtambahkomentar);
        BTtambahkomentar = (Button) findViewById(R.id.BTmasukkankomentar);
        BTtambahkomentar.setOnClickListener(this);




        IVisikonsultasisoal = (ImageView) findViewById(R.id.IVisikonsultasisoal);
        IVisikonsultasisoal.setOnClickListener(new View.OnClickListener() {
            //Untuk fullscreen
            @Override
            public void onClick(View view) {
                if (isImageFull) {
                    isImageFull = false;
                    IVisikonsultasisoal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    IVisikonsultasisoal.setAdjustViewBounds(true);
                } else {
                    isImageFull = true;
                    IVisikonsultasisoal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    IVisikonsultasisoal.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });
        TVisikonsultasijudul = (TextView) findViewById(R.id.TVisikonsultasijudul);
        TVisikonsultasidesc = (TextView) findViewById(R.id.TVisikonsultasidesc);
        TVnamaposter = (TextView) findViewById(R.id.user_name_isi_konsul);
        IVfotoposter = (ImageView) findViewById(R.id.user_image_isi_konsul);
                Bundle extras = getIntent().getExtras();
        if (extras != null) {
            posisiItemRecycler = extras.getInt("PosisiItemRecycler");}

        System.out.print("Urutan ke " + posisiItemRecycler);

        filter = 1;
        populatekomentarlist();

        //registerClickCallBack();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finishAffinity();
        startActivity(new Intent(this, MainActivity.class));
        return true;
    }

    /*private void registerClickCallBack() {
        ListView list = (ListView) findViewById(R.id.komenListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                Komentar klikkomen = mListItemKomen.get(position);
                String Message = " Ini posisi " + position + klikkomen.getKomen();
                Toast.makeText(IsiKonsultasi.this,Message,Toast.LENGTH_LONG).show();

            }
        });

    }*/



    private void populatekomentarlist() {

        Bundle extras3 = getIntent().getExtras();
        Matkuldis = extras3.getString("Matkuldis");
        Bundle extras4 = getIntent().getExtras();
        Jenisdis = extras4.getString("Jenisdis");


        DatabaseReference ref = mRootref.child(Jenisdis).child(Matkuldis).child("Post" +(posisiItemRecycler)).child("Komentar");

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (filter == 1){
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        mListItemKomen.add(new Komentar(postSnapshot.child("pengirim").getValue().toString(),
                                postSnapshot.child("desc").getValue().toString(),
                                postSnapshot.child("counter").getValue().toString(),
                                postSnapshot.child("img").getValue().toString()));


                    }}


                populatelistview();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }

    private void populatelistview() {
        RecyclerView.Adapter<AdapterKomen.ViewHolder> adapter = new AdapterKomen(mListItemKomen, this);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        recyclerView = (RecyclerView) findViewById(R.id.komenListView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        /*ArrayAdapter<Komentar> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.komenListView);
        list.setAdapter(adapter);*/

    }

    /*private class MyListAdapter extends ArrayAdapter<Komentar> {

        public MyListAdapter() {
            super(IsiKonsultasi.this, R.layout.item_komentar, komenku);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.item_komentar,parent,false);
            }
            Komentar currentKomentar = komenku.get(position);
            //ImageView gambarkomen = (ImageView) itemView.findViewById(R.id.IVkomen);
            //gambarkomen.setImageURI(Uri.parse("https://openclipart.org/download/10941/TzeenieWheenie-red-green-OK-not-OK-Icons-1.svg"));

            TextView pengirim = (TextView) itemView.findViewById(R.id.TVpengirimkomen);
            pengirim.setText(currentKomentar.getPengirim());

            TextView komentar = (TextView) itemView.findViewById(R.id.TVisikomen);
            komentar.setText(currentKomentar.getKomen());

            ImageView Foto = (ImageView) itemView.findViewById(R.id.reviewAvatar);

            storage = FirebaseStorage.getInstance();
            mStorageRef = storage.getReferenceFromUrl("gs://tutorpedia-17ba0.appspot.com/FotoProfil/");
            StorageReference foto = mStorageRef.child(currentKomentar.getImgkomen()+"PP"+".jpg");
            Glide.with(this.getContext() )
                    .using(new FirebaseImageLoader())
                    .load(foto)
                    .into(Foto);


            return itemView;
        }



    }*/


    @Override
    protected void onStart() {
        super.onStart();





        Bundle extras3 = getIntent().getExtras();
        Matkuldis = extras3.getString("Matkuldis");
        Bundle extras4=getIntent().getExtras();
        Jenisdis = extras4.getString("Jenisdis");

        System.out.print("Dis : " + Matkuldis + Jenisdis );
        DatabaseReference reff = mRootref.child(Jenisdis).child(Matkuldis);



        reff.addValueEventListener(new ValueEventListener() {




            @Override
            public void onDataChange(DataSnapshot snapshot) {

                System.out.println("There are " + snapshot.getChildrenCount() + " shout messages");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    ListItemKonsultasi listItem = new ListItemKonsultasi(postSnapshot.child("judul").getValue().toString(),
                            postSnapshot.child("img").getValue().toString(),
                            postSnapshot.child("deskripsi").getValue().toString(),
                            postSnapshot.child("foto").getValue().toString(),
                            postSnapshot.child("poster").getValue().toString());



                    if (i == posisiItemRecycler) {

//
                        TVisikonsultasijudul.setText(listItem.getJudulkonsultasi());
                        TVisikonsultasidesc.setText(listItem.getDeskripsikonsultasi());
                        TVnamaposter.setText(listItem.getPosterName());


                        storage = FirebaseStorage.getInstance();
                        mStorageRef = storage.getReferenceFromUrl("gs://tutorpedia-17ba0.appspot.com/FotoProfil/");
                        StorageReference foto = mStorageRef.child(listItem.getImageUrlkonsultasi()+"Konsultasi.jpg");
                        StorageReference fotoposter = mStorageRef.child(listItem.getPosterImage() + "PP" + ".jpg");
                        Glide.with(getApplicationContext())
                                .using(new FirebaseImageLoader())
                                .load(foto)
                                .into(IVisikonsultasisoal);
                        Glide.with(getApplicationContext())
                                .using(new FirebaseImageLoader())
                                .load(fotoposter)
                                .into(IVfotoposter);


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



        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref2 = mRootref.child("User").child(user.getUid());

        ref2.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                pengirim = snapshot.child("nama").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


        if(v == BTtambahkomentar){
            filter = 2;

            Bundle extras3 = getIntent().getExtras();
            Matkuldis = extras3.getString("Matkuldis");
            Bundle extras4 = getIntent().getExtras();
            Jenisdis = extras4.getString("Jenisdis");
            langkahkomen=1;
            System.out.println("Langkah Komen nya : " + langkahkomen);

            DatabaseReference ref =mRootref.child("JumlahKomentarBenar").child(Matkuldis).child("Post"+posisiItemRecycler);
            ref.addValueEventListener(new ValueEventListener() {

                                          @Override
                                          public void onDataChange(DataSnapshot snapshot) {
                                              if (langkahkomen == 1) {
                                                  Integer jumlah = Integer.parseInt(snapshot.getValue().toString());
                                                  databaseReference.child("JumlahKomentarBenar").child(Matkuldis).child("Post"+posisiItemRecycler).setValue(jumlah+1);
                                                  System.out.println(jumlah);
                                                  databaseReference.child(Jenisdis).child(Matkuldis).child("Post" +(posisiItemRecycler)).
                                                          child("Komentar").child("Komentar" +(jumlah)).child("desc").setValue(ETtambahkomentar.getText().toString());
                                                  databaseReference.child(Jenisdis).child(Matkuldis).child("Post" +(posisiItemRecycler)).
                                                          child("Komentar").child("Komentar"+(jumlah)).child("pengirim").setValue(pengirim);
                                                  databaseReference.child(Jenisdis).child(Matkuldis).child("Post" +(posisiItemRecycler)).
                                                          child("Komentar").child("Komentar"+(jumlah)).child("img").setValue(user.getUid().toString());
                                                  databaseReference.child(Jenisdis).child(Matkuldis).child("Post" +(posisiItemRecycler)).
                                                          child("Komentar").child("Komentar"+(jumlah)).child("counter").setValue(0);
                                                  langkahkomen++;
                                              }

                                              finish();
                                              startActivity(getIntent());


                                          }


                                          @Override
                                          public void onCancelled(DatabaseError databaseError) {

                                          }

                                      }
            );





        }

    }
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apa anda ingin keluar ?")
                .setCancelable(false)
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {  finishAffinity(); System.exit(0);
                    }
                })
                .setNegativeButton("Enggak", null)
                .show();
    }
}



