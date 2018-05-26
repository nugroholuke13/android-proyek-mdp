package com.example.janad.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class dokterActivity extends AppCompatActivity {

    Button btnJadwal,btnLibur,btnBatal,btnOffline,btnLive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter);
        btnJadwal = findViewById(R.id.button1);
        btnLibur = findViewById(R.id.button2);
        btnBatal = findViewById(R.id.button3);
        btnOffline = findViewById(R.id.button4);
        btnLive = findViewById(R.id.button5);


        btnJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jdwlDokter = new Intent(dokterActivity.this,jadwalDokter.class);
                startActivity(jdwlDokter);
            }
        });

        btnLibur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent liburDokter = new Intent(dokterActivity.this,jadwalLiburDokter.class);
                startActivity(liburDokter);
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent batalJadwal = new Intent(dokterActivity.this,pembatalan.class
                );
                startActivity(batalJadwal);
            }
        });

        btnOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Offline = new Intent(dokterActivity.this,jadwalOffline.class);
                startActivity(Offline);
            }
        });

        btnLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Live = new Intent(dokterActivity.this,jadwalLive.class);
                startActivity(Live);
            }
        });

    }

}
