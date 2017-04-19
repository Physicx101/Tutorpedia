package com.example.prabowo.tutorpedia.MateriBaru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prabowo.tutorpedia.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PrivilegedAction;

public class MateriMenyenangkan extends AppCompatActivity {

    private TextView TV1;
    private TextView TV2;
    private TextView TV3;
    private TextView TV4;
    private Button BTnext;
    private Button BTback;
    private Button BTshow;

    private int counter = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_menyenangkan);

            counter = 0;

            BTnext = (Button) findViewById(R.id.BTnext);
            BTback = (Button) findViewById(R.id.BTback);
            BTshow = (Button) findViewById(R.id.BTshow);
            TV1 = (TextView) findViewById(R.id.TV1);
            TV2 = (TextView) findViewById(R.id.TV2);
            TV3 = (TextView) findViewById(R.id.TV3);
            TV4 = (TextView) findViewById(R.id.TV4);

        }


//    @Override
    public void Pencet(View v) {
            if (v == BTshow) {
                counter++;
                switch (counter) {
                    case 1:
                        TV1.setText("Kingdom Animalia");
                        break;
                    case 2:
                        TV2.setText("Terdapa banyak ordo salah satunya vertebrata (Bertulang belakang)");
                        break;
                    case 3:
                        TV3.setText("Pada Ordo vertebrata terdapat mamalia yang bercirikan dapat menyusui");
                        break;
                    case 4:
                        TV4.setText("Salah satu contoh mamalia adalah gajah");
                        break;
                }
                }

            if (v == BTnext) {
                finishAffinity();
                startActivity(new Intent(this, MateriMenyenangkan2.class));

            }


            if (v == BTback) {
                finishAffinity();
                startActivity(new Intent(this, MateriMenyenangkan.class));

            }



}}
