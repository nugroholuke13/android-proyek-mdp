package com.example.janad.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class jadwalLiburDokter extends AppCompatActivity {
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_libur_dokter);

        btnBack = findViewById(R.id.button7);
         btnBack.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent halDok = new Intent(jadwalLiburDokter.this,dokterActivity.class);
                 startActivity(halDok);
             }
         });
    }
}
