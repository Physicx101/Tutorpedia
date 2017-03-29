package com.example.prabowo.tutorpedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.prabowo.tutorpedia.R;

import static com.example.prabowo.tutorpedia.R.id.IVbio;

public class ListHadiahPoint extends AppCompatActivity {

    private ImageView IVhadiah1, IVhadiah2, IVhadiah3, IVhadiah4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hadiah_point);

        IVhadiah1 = (ImageView) findViewById(R.id.IVhadiah1);
        IVhadiah1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListHadiahPoint.this, PointActivity.class);
                intent.putExtra("Point", "1500");
                intent.putExtra("Hadiah", "adidas");
                startActivity(intent);
            }
        });

        IVhadiah2 = (ImageView) findViewById(R.id.IVhadiah2);
        IVhadiah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListHadiahPoint.this, PointActivity.class);
                intent.putExtra("Point", "1400");
                intent.putExtra("Hadiah", "adidas");
                startActivity(intent);

    }
});

        IVhadiah3 = (ImageView) findViewById(R.id.IVhadiah3);
        IVhadiah3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListHadiahPoint.this, PointActivity.class);
                intent.putExtra("Point", "1300");
                intent.putExtra("Hadiah", "powerbank");
                startActivity(intent);

            }
        });

        IVhadiah4 = (ImageView) findViewById(R.id.IVhadiah4);
        IVhadiah4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListHadiahPoint.this, PointActivity.class);
                intent.putExtra("Point", "1200");
                intent.putExtra("Hadiah", "powerbank");
                startActivity(intent);

            }
        });


    }
}


