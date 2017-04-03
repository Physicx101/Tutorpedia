package com.example.prabowo.tutorpedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListItemMateri {

    private String judulmateri;
    private String imageUrlmateri;
    private String deskripsimateri;
    private String linkmateri;


    public ListItemMateri(String judulmateri, String imageUrlmateri, String deskripsimateri, String linkmateri) {
        this.judulmateri = judulmateri;
        this.imageUrlmateri = imageUrlmateri;
        this.deskripsimateri = deskripsimateri;
        this.linkmateri = linkmateri;
    }

    public String getDeskripsimateri() {
        return deskripsimateri;
    }

    public String getImageUrlmateri() {
        return imageUrlmateri;
    }

    public String getJudulmateri() {
        return judulmateri;
    }

    public String getLinkmateri() {
        return linkmateri;
    }
}
