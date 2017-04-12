package com.example.prabowo.tutorpedia;

/**
 * Created by Tommy on 2/17/2017.
 */

public class ListEventItemBeneran {
    private String juduleventbeneran;
    private String imageUrleventbeneran;
    private String deskripsieventbeneran;
    private String nilai;
    private String time;
    private String poin;


    public ListEventItemBeneran(String judulevent, String imageUrlevent, String deskripsievent,String nilai,String time,String poin) {
        this.juduleventbeneran = judulevent;
        this.imageUrleventbeneran = imageUrlevent;
        this.deskripsieventbeneran = deskripsievent;
        this.nilai = nilai;
        this.time = time;
        this.poin = poin;
    }

    public String getDeskripsieventbeneran() {
        return deskripsieventbeneran;
    }

    public String getImageUrleventbeneran() {
        return imageUrleventbeneran;
    }

    public String getJuduleventbeneran() {
        return juduleventbeneran;
    }

    public String getNilai(){return  time;}

    public String getTime(){return nilai;}

    public String getPoin() {
        return poin;
    }
}
