package com.example.prabowo.tutorpedia;

/**
 * Created by Prabowo on 07/02/2017.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;



public class Tab1Ipa extends Fragment implements View.OnClickListener {
    private ImageView IVmatipa;
    private ImageView IVbio;
    private ImageView IVkim;
    private ImageView IVfis;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1ipa, container, false);
        IVmatipa = (ImageView) view.findViewById(R.id.IVmatipa);
        IVmatipa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity().getApplicationContext(), Transisi.class);
                intent.putExtra("Matkul","Matematika");
                startActivity(intent);
            }
        });
        IVbio = (ImageView) view.findViewById(R.id.IVbio);
        IVbio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity().getApplicationContext(), Transisi.class);
                intent.putExtra("Matkul", "Biologi");
                startActivity(intent);
            }
        });

        IVkim = (ImageView) view.findViewById(R.id.IVkimia);
        IVkim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity().getApplicationContext(), Transisi.class);
                intent.putExtra("Matkul", "Kimia");
                startActivity(intent);
            }
        });

        IVfis = (ImageView) view.findViewById(R.id.IVfisika);
        IVfis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity().getApplicationContext(), Transisi.class);
                intent.putExtra("Matkul", "Fisika");
                startActivity(intent);
            }
        });

        return view;





}


    @Override
    public void onClick(View v) {

    }

}