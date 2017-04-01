package com.example.prabowo.tutorpedia;

/**
 * Created by Prabowo on 02/02/2017.
 */

public class ListItemTutor {

    private String nama;
    private String email;
    private String imageUrl;
    private String asal;
    private String nohp;
    private String deskripsi;
    private String linkcv;


    public ListItemTutor(String nama, String email, String imageUrl, String asal, String nohp, String deskripsi, String linkcv ) {
        this.nama = nama;
        this.email = email;
        this.imageUrl = imageUrl;
        this.asal = asal;
        this.nohp = nohp;
        this.deskripsi = deskripsi;
        this.linkcv=linkcv;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAsal() {
        return asal;
    }

    public String getNohp() {
        return nohp;
    }

    public String getDeskripsi(){ return deskripsi;}

    public String getLinkcv() {
        return linkcv;
    }
}

