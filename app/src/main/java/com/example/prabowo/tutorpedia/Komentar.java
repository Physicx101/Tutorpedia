package com.example.prabowo.tutorpedia;

/**
 * Created by Bogi on 28-Feb-17.
 */

public class Komentar {

    private String pengirim;
    private String komen;
    private String imgkomen;
    private String counter;


    public Komentar(String pengirim, String komen,String counter, String imgkomen){
        this.pengirim = pengirim;
        this.komen=komen;
        this.imgkomen=imgkomen;
        this.counter=counter;
    }

    public String getImgkomen() {
        return imgkomen;
    }

    public String getKomen() {
        return komen;
    }

    public String getPengirim() {
        return pengirim;
    }

    public String getCounter() {
        return counter;
    }
}
