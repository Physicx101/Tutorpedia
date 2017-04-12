package com.example.prabowo.tutorpedia;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Prabowo on 07/02/2017.
 */

public class Tab2Ips extends Fragment {

    private ImageView IVgeo,IVsos,IVmatips,IVeko;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2ips, container, false);
        IVgeo = (ImageView) view.findViewById(R.id.IVgeo);
        IVgeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {getActivity().finishAffinity();
                Intent intent = new Intent(getActivity().getApplicationContext(), Transisi.class);
                intent.putExtra("Matkul","Geografi");
                startActivity(intent);
            }
        });
        IVsos = (ImageView) view.findViewById(R.id.IVsos);
        IVsos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {getActivity().finishAffinity();
                Intent intent = new Intent(getActivity().getApplicationContext(), Transisi.class);
                intent.putExtra("Matkul", "Sosiologi");
                startActivity(intent);
            }
        });

        IVmatips = (ImageView) view.findViewById(R.id.IVmatips);
        IVmatips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {getActivity().finishAffinity();
                Intent intent = new Intent(getActivity().getApplicationContext(), Transisi.class);
                intent.putExtra("Matkul", "MatematikaIps");
                startActivity(intent);
            }
        });

        IVeko = (ImageView) view.findViewById(R.id.IVeko);
        IVeko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {getActivity().finishAffinity();
                Intent intent = new Intent(getActivity().getApplicationContext(), Transisi.class);
                intent.putExtra("Matkul", "Ekonomi");
                startActivity(intent);
            }
        });

        return view;





    }
    public void onBackPressed() {
        new AlertDialog.Builder(getActivity())
                .setMessage("Apa anda ingin keluar ?")
                .setCancelable(false)
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {  getActivity().finish(); System.exit(0);
                    }
                })
                .setNegativeButton("Enggak", null)
                .show();
    }
    }


