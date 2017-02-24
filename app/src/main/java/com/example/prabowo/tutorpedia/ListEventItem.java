package com.example.prabowo.tutorpedia;

/**
 * Created by Prabowo on 14/02/2017.
 */

public class ListEventItem {

    private String judulevent;
    private String imageUrlevent;
    private String deskripsievent;


    public ListEventItem(String judulevent, String imageUrlevent, String deskripsievent) {
        this.judulevent = judulevent;
        this.imageUrlevent = imageUrlevent;
        this.deskripsievent = deskripsievent;
    }

    public String getDeskripsievent() {
        return deskripsievent;
    }

    public String getImageUrlevent() {
        return imageUrlevent;
    }

    public String getJudulevent() {
        return judulevent;
    }
}
