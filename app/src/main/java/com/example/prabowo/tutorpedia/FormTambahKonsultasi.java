package com.example.prabowo.tutorpedia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FormTambahKonsultasi extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference databaseReference;
    String Matkul;
    private EditText ETtambahjudul, ETtambahdesc, ETtambahimg;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();
    public static int langkah = 1;
    private static int jmlpost;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tambah_konsultasi);



        ETtambahjudul = (EditText) findViewById(R.id.ETtambahjudulkonsul);
        ETtambahdesc = (EditText) findViewById(R.id.ETtambahdesckonsul);
        ETtambahimg = (EditText)findViewById(R.id.ETtambahimgkonsul);

        databaseReference = FirebaseDatabase.getInstance().getReference();


        fab = (FloatingActionButton) findViewById(R.id.fabaddkonsul);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {

        if (v == fab){
            langkah = 1;
            Bundle extras = getIntent().getExtras();
            Matkul = extras.getString("Matkul");


            final DatabaseReference ref = mRootref.child("JumlahKomentar").child(Matkul);
            ref.addValueEventListener(new ValueEventListener() {

                                          @Override
                                          public void onDataChange(DataSnapshot snapshot) {
                                              if (langkah == 1) {
                                                  Integer jumlah = Integer.parseInt(snapshot.getValue().toString());
                                                  databaseReference.child("JumlahKomentar").child("Matematika").setValue(jumlah+1);
                                                  System.out.println("Jumlah komen baru: " + jumlah);

                                                  databaseReference.child("Konsultasi").child(Matkul).child("Post" + jumlah).child("judul").setValue(ETtambahjudul.getText().toString());
                                                  databaseReference.child("Konsultasi").child(Matkul).child("Post" + jumlah).child("deskripsi").setValue(ETtambahdesc.getText().toString());
                                                  databaseReference.child("Konsultasi").child(Matkul).child("Post" + jumlah).child("img").setValue(ETtambahimg.getText().toString());
                                                  databaseReference.child("Konsultasi").child(Matkul).child("Post" + jumlah).child("Komentar")
                                                          .child("Komentar0").child("pengirim").setValue("Admin");
                                                  databaseReference.child("Konsultasi").child(Matkul).child("Post" + jumlah).child("Komentar")
                                                          .child("Komentar0").child("desc").setValue("Pertamax Gan");
                                                  databaseReference.child("Konsultasi").child(Matkul).child("Post" + jumlah).child("Komentar")
                                                          .child("Komentar0").child("img").setValue("Random");
                                                  databaseReference.child("JumlahKomentarBenar").child(Matkul).child("Post" + jumlah).setValue(1);

                                                  Toast.makeText(getApplicationContext(),"Konsultasi anda telah ter upload ", Toast.LENGTH_LONG).show();

                                              }
                                              langkah++;

                                          }


                                          @Override
                                          public void onCancelled(DatabaseError databaseError) {

                                          }

                                      }
            );




        }

    }


}
