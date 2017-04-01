package com.example.prabowo.tutorpedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListItemKonsultasi {

    private String judulkonsultasi;
    private String imageUrlkonsultasi;
    private String deskripsikonsultasi;
    private String posterImage;
    private String posterName;


    public ListItemKonsultasi(String judulkonsultasi, String imageUrlkonsultasi, String deskripsikonsultasi, String posterImage, String posterName) {
        this.judulkonsultasi = judulkonsultasi;
        this.imageUrlkonsultasi = imageUrlkonsultasi;
        this.deskripsikonsultasi = deskripsikonsultasi;
        this.posterImage = posterImage;
        this.posterName = posterName;
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

    public String getPosterImage() {
        return posterImage;
    }

    public String getPosterName() {
        return posterName;
    }
}