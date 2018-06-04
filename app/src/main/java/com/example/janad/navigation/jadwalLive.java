package com.example.janad.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class jadwalLive extends AppCompatActivity {

    Button btnDokter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_live);

        btnDokter  = findViewById(R.id.btn2);
        btnDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(jadwalLive.this,HomeDokterActivity.class);
                startActivity(intent);
            }
        });
    }
}
