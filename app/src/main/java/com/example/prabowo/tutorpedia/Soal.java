package com.example.prabowo.tutorpedia;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prabowo.tutorpedia.CekSoal.CekSoal;
import com.squareup.picasso.Picasso;

public class Soal extends AppCompatActivity {
    public static int nomor = 1;
    public static int[] warna = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int[] soal = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int paket = 1;
    public static int[] ragu = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static long waktu = 5400000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);
        ImageView gambar = (ImageView) findViewById(R.id.imageView);


        run.run();



        Button img1 = (Button) findViewById(R.id.imageButton);
        Button img2 = (Button) findViewById(R.id.imageButton2);
        Button img3 = (Button) findViewById(R.id.imageButton3);
        Button img4 = (Button) findViewById(R.id.imageButton4);
        Button no = (Button) findViewById(R.id.back);
        TextView txt = (TextView) findViewById(R.id.textView);
        if (ragu[nomor] == 1) {
            if (warna[nomor] == 1) {
                img1.setBackgroundColor(Color.parseColor("#00FF00"));
                img2.setBackgroundColor(Color.parseColor("#FF9BA2"));
                img3.setBackgroundColor(Color.parseColor("#FF9BA2"));
                img4.setBackgroundColor(Color.parseColor("#FF9BA2"));
            } else if (warna[nomor] == 2) {
                img1.setBackgroundColor(Color.parseColor("#FF9BA2"));
                img2.setBackgroundColor(Color.parseColor("#00FF00"));
                img3.setBackgroundColor(Color.parseColor("#FF9BA2"));
                img4.setBackgroundColor(Color.parseColor("#FF9BA2"));
            } else if (warna[nomor] == 3) {
                img1.setBackgroundColor(Color.parseColor("#FF9BA2"));
                img2.setBackgroundColor(Color.parseColor("#FF9BA2"));
                img3.setBackgroundColor(Color.parseColor("#00FF00"));
                img4.setBackgroundColor(Color.parseColor("#FF9BA2"));
            } else if (warna[nomor] == 4) {
                img1.setBackgroundColor(Color.parseColor("#FF9BA2"));
                img2.setBackgroundColor(Color.parseColor("#FF9BA2"));
                img3.setBackgroundColor(Color.parseColor("#FF9BA2"));
                img4.setBackgroundColor(Color.parseColor("#00FF00"));
            } else if (warna[nomor] == 5) {
                img1.setBackgroundColor(Color.parseColor("#FF9BA2"));
                img2.setBackgroundColor(Color.parseColor("#FF9BA2"));
                img3.setBackgroundColor(Color.parseColor("#FF9BA2"));
                img4.setBackgroundColor(Color.parseColor("#FF9BA2"));
            }
        } else {
            if (warna[nomor] == 1) {
                img1.setBackgroundColor(Color.parseColor("#00FF00"));
                img2.setBackgroundColor(Color.parseColor("#aaaaaa"));
                img3.setBackgroundColor(Color.parseColor("#aaaaaa"));
                img4.setBackgroundColor(Color.parseColor("#aaaaaa"));
            } else if (warna[nomor] == 2) {
                img1.setBackgroundColor(Color.parseColor("#aaaaaa"));
                img2.setBackgroundColor(Color.parseColor("#00FF00"));
                img3.setBackgroundColor(Color.parseColor("#aaaaaa"));
                img4.setBackgroundColor(Color.parseColor("#aaaaaa"));
            } else if (warna[nomor] == 3) {
                img1.setBackgroundColor(Color.parseColor("#aaaaaa"));
                img2.setBackgroundColor(Color.parseColor("#aaaaaa"));
                img3.setBackgroundColor(Color.parseColor("#00FF00"));
                img4.setBackgroundColor(Color.parseColor("#aaaaaa"));
            } else if (warna[nomor] == 4) {
                img1.setBackgroundColor(Color.parseColor("#aaaaaa"));
                img2.setBackgroundColor(Color.parseColor("#aaaaaa"));
                img3.setBackgroundColor(Color.parseColor("#aaaaaa"));
                img4.setBackgroundColor(Color.parseColor("#00FF00"));
            } else if (warna[nomor] == 5) {
                img1.setBackgroundColor(Color.parseColor("#aaaaaa"));
                img2.setBackgroundColor(Color.parseColor("#aaaaaa"));
                img3.setBackgroundColor(Color.parseColor("#aaaaaa"));
                img4.setBackgroundColor(Color.parseColor("#aaaaaa"));
            }
        }

        switch (nomor) {
            case (1):
                no.setText("No.1");

                Picasso.with(getApplicationContext()).load("http://i.imgur.com/ruJWRFs.png").into(gambar);
                txt.setText("Yang tegak lurus garis x + 2y + 1 = 0 adalah");
                img1.setText("y = 2x - 14");
                img2.setText("y = 2x - 11");
                img3.setText("y = 2x + 5");
                img4.setText("y = 2x + 15");
                if (paket == 2) {
                    Picasso.with(getApplicationContext()).load("http://i.imgur.com/82Yeue2.png").into(gambar);
                    txt.setText("Berapa Hasilnya ?");
                    img1.setText("0");
                    img2.setText("1");
                    img3.setText("2");
                    img4.setText("3");
                }
                break;
            case (2):
                no.setText("No.2");

                Picasso.with(getApplicationContext()).load("http://i.imgur.com/cbxUEzc.png").into(gambar);
                txt.setText("Persamaan yang akarnya (a+2) dan (b+2) ?");
                img1.setText("x^2 + 2x - 13 = 0");
                img2.setText("x^2 + 2x + 13 = 0");
                img3.setText("x^2 - 2x + 13 = 0");
                img4.setText("x^2 + 2x - 21 = 0");
                if (paket == 2) {
                    Picasso.with(getApplicationContext()).load("http://i.imgur.com/hOcqyUl.png").into(gambar);
                    txt.setText("Berapa hasilnya ?");
                    img1.setText("0");
                    img2.setText("1");
                    img3.setText("2");
                    img4.setText("3");
                }
                break;
            case (3):
                no.setText("No.3");

                Picasso.with(getApplicationContext()).load("http://i.imgur.com/5ogd5eX.png").into(gambar);
                txt.setText("Batas nilai M yang memenuhi adalah ");
                img1.setText("M > 10/3 atau M < 1");
                img2.setText("M > 10/3 atau M < -1");
                img3.setText("M >= 1 atau M <= -10/3");
                img4.setText("M >= 1 atau M <= 10/3");
                if (paket == 2) {
                    Picasso.with(getApplicationContext()).load("http://i.imgur.com/ShS4ply.png").into(gambar);
                    txt.setText("Berapa hasilnya ?");
                    img1.setText("0");
                    img2.setText("1");
                    img3.setText("2");
                    img4.setText("3");
                }
                break;
            case (4):
                no.setText("No.4");

                Picasso.with(getApplicationContext()).load("http://i.imgur.com/aNuR1Vu.png").into(gambar);
                txt.setText("Penyelesaian Pertidaksamaan tersebut adalah");
                img1.setText("4/3<x<8 atau x<-2");
                img2.setText("0<x<8 atau  x<-2");
                img3.setText("x>8 atau x<-2");
                img4.setText("x>8 atau -2<x<-4/3");
                if (paket == 2) {
                    Picasso.with(getApplicationContext()).load("http://i.imgur.com/nDGK8zm.png").into(gambar);
                    txt.setText("Berapa hasilnya ?");
                    img1.setText("2");
                    img2.setText("4");
                    img3.setText("8");
                    img4.setText("16");
                }
                break;
            case (5):
                no.setText("No.5");
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/ALnEodI.png").into(gambar);
                txt.setText("Bentuk sederhana bilangan diatas adalah");
                img1.setText("4 - 2*sqrt(3)");
                img2.setText("2 - sqrt(3)");
                img3.setText("-2 + sqrt(3)");
                img4.setText("-4 - 2*sqrt(3)");

                break;
            case (6):

                no.setText("No.6");
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/x5KBngv.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("tan A ");
                img2.setText("-cos A");
                img3.setText("-sin A");
                img4.setText("cos A");


                break;
            case (7):
                no.setText("No.7");
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/bGyS2QU.png").into(gambar);
                txt.setText("Hasilnya adalah ");
                img1.setText("4 - 2*sqrt(3)");
                img2.setText("2 - sqrt(3)");
                img3.setText("-2 + sqrt(3)");
                img4.setText("-4 - 2*sqrt(3)");

                break;
            case (8):
                no.setText("No.8");
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/4ikzbYk.png").into(gambar);
                txt.setText("Hasilnya adalah ");
                img1.setText("-4.25");
                img2.setText("-3.75");
                img3.setText("4.25");
                img4.setText("4");

                break;
            case (9):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/yCZwNys.png").into(gambar);
                txt.setText("Hasilnya adalah ");
                img1.setText("-2");
                img2.setText("-0.5");
                img3.setText("1");
                img4.setText("2");

                break;
            case (10):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/xIMLHSB.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("1-a^2/a");
                img2.setText("1/a^2");
                img3.setText("a^2/a^2+1");
                img4.setText("a^2-1/a^2");

                break;
            case (11):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/eflIf96.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("(-1, 1) dan (3, 9)");
                img2.setText("(1, -1) dan (-3, 9)");
                img3.setText(" (2, 3) dan (3, 6)");
                img4.setText("(-3, 6) dan (2, -3)");

                break;
            case (12):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/S9vnMNs.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("-6");
                img2.setText("-5");
                img3.setText("-4");
                img4.setText("0");

                break;
            case (13):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/7GzFMoD.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("+-6");
                img2.setText("+-8");
                img3.setText("+-10");
                img4.setText("+-11");

                break;
            case (14):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/B29Fkjt.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("1");
                img2.setText("3");
                img3.setText("5");
                img4.setText("9");

                break;
            case (15):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/AxWwVzl.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("-8");
                img2.setText("-6");
                img3.setText("60");
                img4.setText("64");

                break;
            case (16):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/Xk0sMr1.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("30");
                img2.setText("10");
                img3.setText("2");
                img4.setText("-2");

                break;
            case (17):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/xWVlEn2.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("3");
                img2.setText("4");
                img3.setText("5");
                img4.setText("6");

                break;
            case (18):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/879L3f9.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("2");
                img2.setText("-6");
                img3.setText("3");
                img4.setText("6");

                break;
            case (19):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/d3iPnhI.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("3.125");
                img2.setText("6.25");
                img3.setText("12.5");
                img4.setText("18.5");

                break;
            case (20):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/5d71KWZ.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("-3");
                img2.setText("-2");
                img3.setText("3");
                img4.setText("4");

                break;
            case (21):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/pH9fQuP.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("1/8");
                img2.setText("1/9");
                img3.setText("1");
                img4.setText("9");

                break;
            case (22):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/E52VKou.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("4/3(a^1/2)(b^3/2)");
                img2.setText("(a^1/2)(b^3/2)");
                img3.setText("=(a^2/3)(b^-2)");
                img4.setText("(a^2)(b^2)");

                break;
            case (23):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/vobbilZ.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("(1-sqrt(x)(x-1)");
                img2.setText("(1+sqrt(x)(1-x)");
                img3.setText("(1+sqrt(x)(1+x)");
                img4.setText("(1-sqrt(x)(1-x)");

                break;
            case (24):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/jnJ54tb.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("-2a^2");
                img2.setText("-2a");
                img3.setText("2a^2");
                img4.setText("2a");

                break;
            case (25):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/DQBBGce.png").into(gambar);
                txt.setText("Mana yang pasti benar ?");
                img1.setText("x^2<y^2");
                img2.setText("-y<-X");
                img3.setText("xy<y^2");
                img4.setText("x^2<xy");

                break;
            case (26):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/IMVLYBA.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("(n/n+12)*100%");
                img2.setText("(n+12/2n)*100%");
                img3.setText("(n-12/n)*100%");
                img4.setText("(n/2(n+6))*100%");
                break;
            case (27):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/oFntW3t.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("1/2");
                img2.setText("1/3");
                img3.setText("3/8");
                img4.setText("8/15");
                break;
            case (28):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/Jl1E7Q6.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("0");
                img2.setText("1");
                img3.setText("7");
                img4.setText("14");
                break;
            case (29):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/aqCsbJt.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("2");
                img2.setText("4");
                img3.setText("6");
                img4.setText("8");
                break;
            case (30):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/E2qKIUm.png").into(gambar);
                txt.setText("titik manakah yang mungkin merupakan titik (x, f’(x))? ");
                img1.setText("(0, 0) ");
                img2.setText("(3, 12) ");
                img3.setText("(5, -2) ");
                img4.setText("(8, -1) ");
                break;
            case (31):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/SInrlmx.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText(" y = 2x - 3 ");
                img2.setText("y = 2x + 3 ");
                img3.setText("y = 3x + 2 ");
                img4.setText("y = 3x - 2 ");
                break;
            case (32):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/eT4MZe2.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText(" (2, 0) ");
                img2.setText("(3, 0)  ");
                img3.setText(" (4, 0)  ");
                img4.setText("(-4, 0) ");
                break;
            case (33):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/E2qKIUm.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("3x + y + 1 = 0 ");
                img2.setText(" 3x - y - 1 = 0 ");
                img3.setText(" 3x - y + 1 = 0 ");
                img4.setText("3x - y + 1 = 0 ");
                break;
            case (34):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/4wD1Rrw.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText(" 2x + 3y + 4 = 0  ");
                img2.setText("2x - 3y - 4 = 0  ");
                img3.setText("3x - 2y + 7 = 0 ");
                img4.setText("3x - 2y - 7 = 0 ");
                break;
            case (35):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/kRMzqq2.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("3");
                img2.setText("1/3");
                img3.setText("-1/3");
                img4.setText("1");
                break;
            case (36):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/UgnF18K.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("2x - 5y = 19 ");
                img2.setText("-2x + 5y = 19 ");
                img3.setText("2x + 5y = -4");
                img4.setText(" 2x + 5y = -2 ");
                break;
            case (37):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/8HJCusQ.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText(" Un = 3n - 1 ");
                img2.setText(" Un = 6n - 4 ");
                img3.setText(" Un = 4n + 2 ");
                img4.setText(" Un = 4n - 2 ");
                break;
            case (38):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/KHZgkXL.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("3");
                img2.setText("1/3");
                img3.setText("-1/3");
                img4.setText("1");
                break;
            case (39):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/kRMzqq2.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("-65 ");
                img2.setText("-59 ");
                img3.setText("-53 ");
                img4.setText("-47 ");
                break;
            case (40):
                no.setText("No." + nomor);
                Picasso.with(getApplicationContext()).load("http://i.imgur.com/K4EMrj5.png").into(gambar);
                txt.setText("Hasilnya adalah");
                img1.setText("-5");
                img2.setText("-2");
                img3.setText("0");
                img4.setText("2");
                break;


        }
        waktu = 1;
    }

    public void button(View v) {
        switch (nomor) {
            case (1):
                if (paket == 1) {
                    soal[nomor] = 0;
                } else if (paket == 2) {
//                    Variabel.setSoal1(1);
                }
                warna[nomor] = 1;
                break;
            case (2):
                if (paket == 1) {
                    soal[nomor] = 0;
                } else if (paket == 2) {
//                    Variabel.setSoal2(1);
                }
                warna[nomor] = 1;
                break;
            case (3):
                if (paket == 1) {
                    soal[nomor] = 0;
                } else if (paket == 2) {
//                    Variabel.setSoal3(0);
                }
                warna[nomor] = 1;
                break;


            case (4):
                if (paket == 1) {
                    soal[nomor] = 0;
                } else if (paket == 2) {
//                    Variabel.setSoal4(0);
                }
                warna[nomor] = 1;
                break;
            case (5):
                if (paket == 1) {
                    soal[nomor] = 0;
                } else if (paket == 2) {
//                    Variabel.setSoal5(1);
                }
                warna[nomor] = 1;
                break;
            default:
                soal[nomor] = 0;
                warna[nomor] = 1;
                break;


        }
        if (nomor == 40) {
            Intent i = new Intent(this, CekSoal.class);

            startActivity(i);
        } else {
            nomor = nomor + 1;


            Intent i = new Intent(this, Soal.class);

            startActivity(i);

        }
    }

    public void button2(View v) {
        switch (nomor) {

            case (1):
                if (paket == 1) {
                    soal[nomor] = 0;
                } else if (paket == 2) {
//                    Variabel.setSoal1(0);
                }
                warna[nomor] = 2;
                break;
            case (2):
                if (paket == 1) {
                    soal[nomor] = 1;
                } else if (paket == 2) {
//                    Variabel.setSoal2(0);
                }
                warna[nomor] = 2;
                break;
            case (3):
                if (paket == 1) {
                    soal[nomor] = 0;
                } else if (paket == 2) {
//                    Variabel.setSoal3(1);
                }
                warna[nomor] = 2;
                break;
            case (4):
                if (paket == 1) {
                    soal[nomor] = 1;
                } else if (paket == 2) {
//                    Variabel.setSoal4(0);
                }
                warna[nomor] = 2;
                break;
            case (5):
                if (paket == 1) {
                    soal[nomor] = 1;
                } else if (paket == 2) {
//                    Variabel.setSoal5(0);
                }
                warna[nomor] = 2;
                break;
            default:
                soal[nomor] = 0;
                warna[nomor] = 2;
                break;

        }
        if (nomor == 40) {
            Intent i = new Intent(this, CekSoal.class);

            startActivity(i);
        } else {
            nomor = nomor + 1;
            Intent i = new Intent(this, Soal.class);

            startActivity(i);
        }
    }

    public void button3(View v) {
        switch (nomor) {
            case (1):
                if (paket == 1) {
                    soal[nomor] = 0;
                } else if (paket == 2) {
//                    Variabel.setSoal1(0);
                }
                warna[nomor] = 3;
                break;
            case (2):
                if (paket == 1) {
                    soal[nomor] = 0;
                } else if (paket == 2) {
//                    Variabel.setSoal2(0);
                }
                warna[nomor] = 3;
                break;
            case (3):
                if (paket == 1) {
                    soal[nomor] = 1;
                } else if (paket == 2) {
//                    Variabel.setSoal3(0);
                }
                warna[nomor] = 3;
                break;
            case (4):
                if (paket == 1) {
                    soal[nomor] = 0;
                } else if (paket == 2) {
//                    Variabel.setSoal4(0);
                }
                warna[nomor] = 3;
                break;
            case (5):
                if (paket == 1) {
                    soal[nomor] = 0;
                } else if (paket == 2) {
//                    Variabel.setSoal5(1);
                }
                warna[nomor] = 3;
                break;
            default:
                soal[nomor] = 1;
                warna[nomor] = 3;
                break;
        }
        if (nomor == 40) {
            Intent i = new Intent(this, CekSoal.class);

            startActivity(i);
        } else {
            nomor = nomor + 1;
            Intent i = new Intent(this, Soal.class);

            startActivity(i);
        }
    }

    public void button4(View v) {
        switch (nomor) {
            case (1):

                if (paket == 1) {
                    soal[nomor] = 1;
                } else if (paket == 2) {
//                    Variabel.setSoal1(0);
                }
                warna[nomor] = 4;
                break;
            case (2):
                if (paket == 1) {
                    soal[nomor] = 0;
                } else if (paket == 2) {
//                    Variabel.setSoal2(0);
                }
                warna[nomor] = 4;
                break;
            case (3):
                if (paket == 1) {
                    soal[nomor] = 0;
                } else if (paket == 2) {
//                    Variabel.setSoal3(0);
                }
                warna[nomor] = 4;
                break;
            case (4):
                if (paket == 1) {
                    soal[nomor] = 0;
                } else if (paket == 2) {
//                    Variabel.setSoal4(1);
                }
                warna[nomor] = 4;
                break;
            case (5):
                if (paket == 1) {
                    soal[nomor] = 0;
                } else if (paket == 2) {
//                    Variabel.setSoal5(0);
                }
                warna[nomor] = 4;
                break;
            default:
                soal[nomor] = 0;
                warna[nomor] = 4;
                break;
        }
        if (nomor == 40) {
            Intent i = new Intent(this, CekSoal.class);

            startActivity(i);
        } else {
            nomor = nomor + 1;
            Intent i = new Intent(this, Soal.class);

            startActivity(i);
        }
    }


    public void Akhir(View v) {

        Intent i = new Intent(this, CekSoal.class);

        startActivity(i);
    }

    public void ragu(View v) {
        Button img1 = (Button) findViewById(R.id.imageButton);
        Button img2 = (Button) findViewById(R.id.imageButton2);
        Button img3 = (Button) findViewById(R.id.imageButton3);
        Button img4 = (Button) findViewById(R.id.imageButton4);
        if (ragu[nomor] == 0) {

            img1.setBackgroundColor(Color.parseColor("#FF9BA2"));
            img2.setBackgroundColor(Color.parseColor("#FF9BA2"));
            img3.setBackgroundColor(Color.parseColor("#FF9BA2"));
            img4.setBackgroundColor(Color.parseColor("#FF9BA2"));
            ragu[nomor] = 1;
        } else {
            img1.setBackgroundColor(Color.parseColor("#aaaaaa"));
            img2.setBackgroundColor(Color.parseColor("#aaaaaa"));
            img3.setBackgroundColor(Color.parseColor("#aaaaaa"));
            img4.setBackgroundColor(Color.parseColor("#aaaaaa"));
            ragu[nomor] = 0;
        }
    }


    public void next(View v) {
        if (nomor == 40) {
            Intent i = new Intent(this, CekSoal.class);

            startActivity(i);
        } else {
            nomor = nomor + 1;
            Intent i = new Intent(this, Soal.class);

            startActivity(i);
        }
    }


    Runnable run = new Runnable() {
        @Override
        public void run() {
            new CountDownTimer(waktu,1000){

                public void onTick (long millis){
                    TextView timer = (TextView) findViewById(R.id.Timer);
                    //long millis = System.currentTimeMillis();
                    int seconds = (int) (millis / 1000);
                    int minutes = (seconds % 3600) / 60;
                    int hours = seconds / 3600;
                    seconds = seconds % 60;
                    timer.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
                    waktu = millis;
                }
                public void onFinish (){
                    TextView waktu = (TextView) findViewById(R.id.Timer);
                    waktu.setText("!! Waktu Telah Habis !!");
                    for (int i=1;i<41;i++){
                        CekSoal.score = CekSoal.score + Soal.soal[i];
                    }
                    CekSoal.score = CekSoal.score*2.5;
                    CekSoal.selesai = 1;
                    CekSoal.NilaiAkhir = String.valueOf(CekSoal.score);
                    Intent i = new Intent(Soal.this, EventFragment.class);

                    startActivity(i);
                }
            }.start();
        }
    };
}

