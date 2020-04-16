package com.example.ceptefestt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class harita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harita);
    }

    public void bil(View view) {
        Intent gecisYap = new Intent(harita.this,bildirim.class);
        startActivity(gecisYap);
    }

    public void öde(View view) {
        Intent gecisYap = new Intent(harita.this, cuzdan.class);
        startActivity(gecisYap);
    }

    public void ayarla(View view) {
        Intent gecisYap = new Intent(harita.this, Hesap.class);
        startActivity(gecisYap);
    }
}


