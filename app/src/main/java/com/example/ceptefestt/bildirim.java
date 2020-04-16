package com.example.ceptefestt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class bildirim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bildirim);
    }
    public void gez(View view) {
        Intent gecisYap = new Intent(bildirim.this, harita.class);
        startActivity(gecisYap);
    }

    public void bildii(View view) {
        Intent gecisYap = new Intent(bildirim.this, bildirim.class);
        startActivity(gecisYap);
    }

    public void hadi(View view) {
        Intent gecisYap = new Intent(bildirim.this, cuzdan.class);
        startActivity(gecisYap);
    }

    public void hsp(View view) {
        Intent gecisYap = new Intent(bildirim.this, Hesap.class);
        startActivity(gecisYap);
    }
}

