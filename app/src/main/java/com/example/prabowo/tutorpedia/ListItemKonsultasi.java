package com.example.prabowo.tutorpedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListItemKonsultasi {

    private String judulkonsultasi;
    private String imageUrlkonsultasi;
    private String deskripsikonsultasi;


    public ListItemKonsultasi(String judulkonsultasi, String imageUrlkonsultasi, String deskripsikonsultasi) {
        this.judulkonsultasi = judulkonsultasi;
        this.imageUrlkonsultasi = imageUrlkonsultasi;
        this.deskripsikonsultasi = deskripsikonsultasi;
    }

    public String getDeskripsikonsultasi() {
        return deskripsikonsultasi;
    }

    public String getImageUrlkonsultasi() {
        return imageUrlkonsultasi;
    }

    public String getJudulkonsultasi() {
        return judulkonsultasi;
    }
}