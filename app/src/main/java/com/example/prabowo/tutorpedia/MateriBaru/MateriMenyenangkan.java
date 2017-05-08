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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prabowo.tutorpedia.MainActivity;
import com.example.prabowo.tutorpedia.MainDrawer;
import com.example.prabowo.tutorpedia.Materi;
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
import static com.example.prabowo.tutorpedia.R.id.title1;
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
    private TextView Title1;
    private TextView Title2;
    private LinearLayout BG;

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

            Title1 = (TextView) findViewById(R.id.title1);
            Title2 = (TextView) findViewById(R.id.title2);
             BG = (LinearLayout) findViewById(R.id.BG);




        if (Materi.Mapel.equalsIgnoreCase("matematika") || Materi.Mapel.equalsIgnoreCase("matematikaips") ){
             Title1.setText("Persamaan");
            Title2.setText("Kuadrat");
            BG.setBackgroundResource(R.drawable.tmath);
            }
        else if (Materi.Mapel.equalsIgnoreCase("biologi")){

            }
        else if (Materi.Mapel.equalsIgnoreCase("fisika")){
            Title1.setText("Hukum");
            Title2.setText("Newton");
            BG.setBackgroundResource(R.drawable.tfisika);
            }
        else if (Materi.Mapel.equalsIgnoreCase("kimia")){
            Title1.setText("Reaksi");
            Title2.setText("Kimia");
            BG.setBackgroundResource(R.drawable.tchemist);
           }
        else if (Materi.Mapel.equalsIgnoreCase("geografi")){
            Title1.setText("Lempengan");
            Title2.setText("Bumi");
            BG.setBackgroundResource(R.drawable.tgeografi);
            }
        else if (Materi.Mapel.equalsIgnoreCase("sosiologi")){
            Title1.setText("Interaksi");
            Title2.setText("Masyarakat");
            BG.setBackgroundResource(R.drawable.tsosio);
          }
        else if (Materi.Mapel.equalsIgnoreCase("ekonomi")){
            Title1.setText("Lembaga");
            Title2.setText("Keuangan");
            BG.setBackgroundResource(R.drawable.tekonom);           }
        else if (Materi.Mapel.equalsIgnoreCase("bing")){
            Title1.setText("Present");
            Title2.setText("Tenses");
            BG.setBackgroundResource(R.drawable.tbing);            }
        else if (Materi.Mapel.equalsIgnoreCase("bindo")){
            Title1.setText("Kalimat");
            Title2.setText("Baku");
            BG.setBackgroundResource(R.drawable.tbind);        }




        }

//    @Override
    public void Pencet(View v) {
        Animation animation=new TranslateAnimation(0,480,100,0);
        animation.setDuration(1000);
        animation.setRepeatMode(Animation.RESTART);


            if (v == BTshow) {
                if (Materi.Mapel.equalsIgnoreCase("matematika") || Materi.Mapel.equalsIgnoreCase("matematikaips") ){
                counter++;
                switch (counter) {

                    case 1:
                        TV2.setText("Sebuah persamaan yang "+ "konstantanya pangkat 2");
                        TV2.startAnimation(animation);


                        break;
                    case 2:
                        TV3.setText("Bisa diselesaikan dengan pemfaktoran");

                        TV3.startAnimation(animation);
                        break;
                    case 3:
                        TV4.setText("Jika Gagal maka dengan rumus ABC");

                        TV4.startAnimation(animation);
                        break;
                }}
                else if (Materi.Mapel.equalsIgnoreCase("biologi")){
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

                }}
                else if (Materi.Mapel.equalsIgnoreCase("fisika")){
                    counter++;
                    switch (counter) {

                        case 1:
                            TV2.setText("Hukum newton merupakan aksi reaksi ");
                            TV2.startAnimation(animation);


                            break;
                        case 2:
                            TV3.setText("Rumus dasarnya : F = m x a");

                            TV3.startAnimation(animation);
                            break;
                        case 3:
                            TV4.setText("Jika sebuah gaya melakukan aksi maka akan terjadi reaksi");

                            TV4.startAnimation(animation);
                            break;

                    }}
                else if (Materi.Mapel.equalsIgnoreCase("kimia")){
                    counter++;
                    switch (counter) {

                        case 1:
                            TV2.setText("Reaksi kimia merupakan reaksi antar molekul");
                            TV2.startAnimation(animation);


                            break;
                        case 2:
                            TV3.setText("Jenis zat sangat berpengaruh terhadap reaksi");

                            TV3.startAnimation(animation);
                            break;
                        case 3:
                            TV4.setText("Reaksi kerap terjadi antara zat asam dan basa");

                            TV4.startAnimation(animation);
                            break;

                    }}
                else if (Materi.Mapel.equalsIgnoreCase("geografi")){
                    counter++;
                    switch (counter) {

                        case 1:
                            TV2.setText("Bumi memiliki 7 lapisan yang dibedakan berdasar lempeng");
                            TV2.startAnimation(animation);


                            break;
                        case 2:
                            TV3.setText("Bagian yang kita huni disebut Kerak Bumi");

                            TV3.startAnimation(animation);
                            break;
                        case 3:
                            TV4.setText("Bagian yang paling dalam dinamakan Inti Bumi");

                            TV4.startAnimation(animation);
                            break;

                    }}
                else if (Materi.Mapel.equalsIgnoreCase("sosiologi")){
                    counter++;
                    switch (counter) {

                        case 1:
                            TV2.setText("Interaksi sosial dibedakan menjadi 3,yaitu :");
                            TV2.startAnimation(animation);


                            break;
                        case 2:
                            TV3.setText("antara individu dengan individu,individu dengan masyarakat, dan masyarakat dengan masyarakat");

                            TV3.startAnimation(animation);
                            break;
                        case 3:
                            TV4.setText("Oleh karena itu manusia disebut sebagai makhluk sosial");

                            TV4.startAnimation(animation);
                            break;

                    }}
                else if (Materi.Mapel.equalsIgnoreCase("ekonomi")){
                    counter++;
                    switch (counter) {

                        case 1:
                            TV2.setText("Lembaga keuangan merupakan lembaga yang mengatur segala urusan di bidang keuangan");
                            TV2.startAnimation(animation);


                            break;
                        case 2:
                            TV3.setText("Salah satu contoh lembaga keuangan yaitu bank");

                            TV3.startAnimation(animation);
                            break;
                        case 3:
                            TV4.setText("Bank merupakan lembaga keuangan yang berperan utama sebagai tempat menyimpan uang");

                            TV4.startAnimation(animation);
                            break;

                    }}
                else if (Materi.Mapel.equalsIgnoreCase("bing")){
                    counter++;
                    switch (counter) {

                        case 1:
                            TV2.setText("Present Tense adalah keadaan dimana sesuatu berlangsung pada masa sekarang");
                            TV2.startAnimation(animation);


                            break;
                        case 2:
                            TV3.setText("Verb pada Present Tense menggunakan kata kerja bentuk 1");

                            TV3.startAnimation(animation);
                            break;
                        case 3:
                            TV4.setText("Contohnya : I go to school today");

                            TV4.startAnimation(animation);
                            break;

                    }}
                else if (Materi.Mapel.equalsIgnoreCase("bindo")){
                    counter++;
                    switch (counter) {

                        case 1:
                            TV2.setText("Kalimat baku merupakan kalimat yang sesuai dengan kaidah EYD");
                            TV2.startAnimation(animation);


                            break;
                        case 2:
                            TV3.setText("Kalimat baku disusun menggunakan kata - kata baku");

                            TV3.startAnimation(animation);
                            break;
                        case 3:
                            TV4.setText("Contoh kata baku yaitu 'Praktik',bukannya 'Praktek'");

                            TV4.startAnimation(animation);
                            break;

                    }}

                }

            if (v == BTnext) {
                finishAffinity();
                startActivity(new Intent(this, MateriMenyenangkan2.class));

            }


            if (v == BTback) {
                finishAffinity();
                startActivity(new Intent(this, MainDrawer.class));

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
