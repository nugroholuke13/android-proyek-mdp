package com.example.janad.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class daftarBooking extends AppCompatActivity {

    ListView lvbook,lvhistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_booking);
        lvbook = findViewById(R.id.lvBookingan);
        lvhistory = findViewById(R.id.lvHistory);
    }
}
