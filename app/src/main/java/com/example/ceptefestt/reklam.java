package com.example.ceptefestt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class reklam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reklam);

        Thread islem = new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    sleep(3000);
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        };
        islem.start();
    }
}
