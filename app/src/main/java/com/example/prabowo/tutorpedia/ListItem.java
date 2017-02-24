package com.example.prabowo.tutorpedia;

/**
 * Created by Prabowo on 02/02/2017.
 */

public class ListItem {

    private String nama;
    private String tanggallahir;
    private String imageUrl;
    private String asal;
    private String nohp;
    private String deskripsi;
    private String linkcv;


    public ListItem(String nama, String tanggallahir, String imageUrl, String asal, String nohp, String deskripsi, String linkcv ) {
        this.nama = nama;
        this.tanggallahir = tanggallahir;
        this.imageUrl = imageUrl;
        this.asal = asal;
        this.nohp = nohp;
        this.deskripsi = deskripsi;
        this.linkcv=linkcv;
    }

    public String getNama() {
        return nama;
    }

    public String getTanggallahir() {
        return tanggallahir;
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

