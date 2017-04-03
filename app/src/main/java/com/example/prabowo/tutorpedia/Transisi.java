package com.example.prabowo.tutorpedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class Transisi extends AppCompatActivity implements View.OnClickListener {

    private ImageView IVtransisimateri;
    private ImageView IVtransisikonsultasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transisi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        IVtransisikonsultasi = (ImageView) findViewById(R.id.IVtransisikonsultasi);
        IVtransisimateri = (ImageView) findViewById(R.id.IVtransisimateri);
        IVtransisimateri.setOnClickListener(this);
        IVtransisikonsultasi.setOnClickListener(this);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    @Override
    public void onClick(View v) {

        Bundle extras = getIntent().getExtras();
        String Matkul = extras.getString("Matkul");
        System.out.println(Matkul);

        if(v == IVtransisikonsultasi){
            Intent intent = new Intent(this,Konsultasi.class);
            intent.putExtra("Jenis","Konsultasi");
            intent.putExtra("Matkul",Matkul);
            startActivity(intent);
        }

        if(v == IVtransisimateri){
            Intent intent = new Intent(this,Materi.class);
            intent.putExtra("Jenis","Materi");
            intent.putExtra("Matkul",Matkul);
            startActivity(intent);
        }
    }
}
