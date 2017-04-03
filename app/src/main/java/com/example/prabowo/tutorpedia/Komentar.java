package com.example.prabowo.tutorpedia;

/**
 * Created by Bogi on 28-Feb-17.
 */

public class Komentar {

    private String pengirim;
    private String komen;
    private String imgkomen;

    public Komentar(String pengirim, String komen, String imgkomen){
        this.pengirim = pengirim;
        this.komen=komen;
        this.imgkomen=imgkomen;
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
}
