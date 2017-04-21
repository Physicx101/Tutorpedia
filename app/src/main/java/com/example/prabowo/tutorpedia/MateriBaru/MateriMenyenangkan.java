package com.example.prabowo.tutorpedia.MateriBaru;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prabowo.tutorpedia.MainActivity;
import com.example.prabowo.tutorpedia.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PrivilegedAction;

import static android.R.attr.animateFirstView;
import static android.R.attr.animation;
import static android.R.attr.centerColor;
import static android.R.attr.duration;
import static android.R.attr.enterFadeDuration;
import static com.example.prabowo.tutorpedia.R.id.textView;
import static com.roughike.bottombar.R.id.time;
import static java.lang.Thread.sleep;

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
        Animation animation=new TranslateAnimation(0,480,100,0);
        animation.setDuration(1000);
        animation.setRepeatMode(Animation.RESTART);

            if (v == BTshow) {
                counter++;
                switch (counter) {

                    case 1:
                        TV2.setText("Terdapat banyak ordo salah satunya"+ "(Bertulang belakang)");
                        TV2.startAnimation(animation);


                        break;
                    case 2:
                        TV3.setText("Pada Ordo vertebrata terdapat mamalia yang bercirikan dapat menyusui");

                        TV3.startAnimation(animation);
                        break;
                    case 3:
                        TV4.setText("Salah satu contoh mamalia adalah gajah");

                        TV4.startAnimation(animation);
                        break;
                }
                }

            if (v == BTnext) {
                finishAffinity();
                startActivity(new Intent(this, MateriMenyenangkan2.class));

            }


            if (v == BTback) {
                finishAffinity();
                startActivity(new Intent(this, MainActivity.class));

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
