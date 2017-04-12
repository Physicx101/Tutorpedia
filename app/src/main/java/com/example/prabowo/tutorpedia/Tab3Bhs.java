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

public class Tab3Bhs extends Fragment {

    private ImageView IVbind,IVbing;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3bhs, container, false);
        IVbind = (ImageView) view.findViewById(R.id.IVbind);
        IVbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {getActivity().finishAffinity();
                Intent intent = new Intent(getActivity().getApplicationContext(), Transisi.class);
                intent.putExtra("Matkul","Bindo");
                startActivity(intent);
            }
        });
        IVbing = (ImageView) view.findViewById(R.id.IVbing);
        IVbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {getActivity().finishAffinity();
                Intent intent = new Intent(getActivity().getApplicationContext(), Transisi.class);
                intent.putExtra("Matkul", "Bing");
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
                    public void onClick(DialogInterface dialog, int id) { getActivity().finish(); System.exit(0);
                    }
                })
                .setNegativeButton("Enggak", null)
                .show();
    }

}