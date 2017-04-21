package com.example.prabowo.tutorpedia.MateriBaru;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.prabowo.tutorpedia.MainActivity;
import com.example.prabowo.tutorpedia.R;

/**
 * Created by tommywahyu44 on 4/18/2017.
 */

public class MateriMenyenangkan2 extends AppCompatActivity {
    private Button BTnext;
    private Button BTback;
    private Button BTshow;
    private Button Gkaki;
    private Button Ggading;
    private Button Gbadan;
    private Button Gbelalai;
    private Button Gkepala;
    private TextView TV22;
    private TextView TV23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.Fade;
            setContentView(R.layout.activity_materi_menyenangkan2);



        BTnext = (Button) findViewById(R.id.BTnext);
        BTback = (Button) findViewById(R.id.BTback);
        BTshow = (Button) findViewById(R.id.BTshow);

        Gbadan = (Button) findViewById(R.id.Gbadan);
        Gkaki = (Button) findViewById(R.id.Gkaki);
        Gbelalai = (Button) findViewById(R.id.Gbelalai);
        Gkepala = (Button) findViewById(R.id.Gkepala);
        Ggading = (Button) findViewById(R.id.Ggading);

        TV22 = (TextView) findViewById(R.id.TV22);
        TV23 = (TextView) findViewById(R.id.TV23);
    }


    //    @Override
    public void Pencet(View v) {
            if (v == Gbadan){
                TV22.setText("Badan Gajah");TV23.setText("Berat mencapai 1 Ton,gajah dewasa menggunakan perut untuk berguling");
            } else if (v == Gkaki){
                TV22.setText("Kaki Gajah");TV23.setText("Berjalan lambat namun memiliki kekuatan hentakan lebih dari 2000 Newton");
            } else if (v == Gkepala){
                TV22.setText("Kepala Gajah");TV23.setText("Gajah memiliki kepala yang besar dengan kecerdasan tinggi jika dibanding hewan lain");
            } else if (v == Ggading){
                TV22.setText("Gading Gajah");TV23.setText("Gading merupakan tulang yang tumbuh didekat mulut gajah,sangat kuat dan keras");
            } else if (v == Gbelalai){
                TV22.setText("Belalai Gajah");TV23.setText("Merupakan hidung gajah,dengan ukuran yang panjang berungsi hampir sama seperti selang");
            }


        if (v == BTnext) {
            finishAffinity();
            startActivity(new Intent(this, MainActivity.class));

        }


        if (v == BTback) {

            finishAffinity();
            startActivity(new Intent(this, MateriMenyenangkan.class));
        }



    }
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apa anda ingin keluar ?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) { finish(); System.exit(0);
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }}



