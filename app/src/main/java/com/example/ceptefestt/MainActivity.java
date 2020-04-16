package com.example.ceptefestt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button btn_baslangicGiris;
    Button btn_baslangicKaydol;

    FirebaseUser baslangicKullanici;

    @Override
    protected void onStart() {
        super.onStart();

        baslangicKullanici = FirebaseAuth.getInstance().getCurrentUser();

        if (baslangicKullanici != null)
        {
            startActivity(new Intent(MainActivity.this,harita.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_baslangicGiris=findViewById(R.id.btn_giris);
        btn_baslangicKaydol=findViewById(R.id.btn_kaydol);


        btn_baslangicGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,harita.class));
            }
        });

        btn_baslangicKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,kaydol.class));
            }
        });
    }
}
