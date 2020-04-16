package com.example.ceptefestt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class cuzdan extends AppCompatActivity implements View.OnClickListener {


    Button btn;
    Button btn1;
    Button btn2;
    EditText txt2;

    private  TextView yapilacak;
    private Button ekle,gorBtn;
    private TextView gor;

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuzdan);
        satinalma();


        txt2=(EditText) findViewById(R.id.parayatir);
        btn=(Button)findViewById(R.id.button19);
        btn1=(Button)findViewById(R.id.button20);
        btn2=(Button)findViewById(R.id.button21);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt2.setText("15 TL ");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt2.setText("25 TL");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt2.setText("50 TL");
            }
        });
    }

    private void satinalma() {
        db=FirebaseDatabase.getInstance();
        yapilacak=(EditText) findViewById(R.id.parayatir);
        ekle=(Button)findViewById(R.id.button25);
        gorBtn=(Button)findViewById(R.id.button2);
        gor=(TextView)findViewById(R.id.editText12);

        ekle.setOnClickListener(this);
        gorBtn.setOnClickListener(this);

    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button25:
                String todo=yapilacak.getText().toString().trim();
                yapilacakEkle(todo);
                break;
            case R.id.button2:
                yapilacaklariGor();
                break;
        }
    }

    private void yapilacakEkle(String yapilacak){

        DatabaseReference dbRef = db.getReference("yatırılanpara");
        String key = dbRef.push().getKey();
        DatabaseReference dbRefYeni = db.getReference("yatırılanpara/"+key);
        dbRefYeni.setValue(yapilacak);




    }
    private void yapilacaklariGor() {


        DatabaseReference okuma = db.getReference("yatırılanpara");
        okuma.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                gor.setText("");
                Iterable<DataSnapshot> keys = dataSnapshot.getChildren();
                for (DataSnapshot key : keys) {

                    gor.append(key.getValue().toString() + "\n");

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }
    public void hadi(View view) {
        Intent gecisYap = new Intent(cuzdan.this, harita.class);
        startActivity(gecisYap);
    }

    public void hadi2(View view) {
        Intent gecisYap = new Intent(cuzdan.this, bildirim.class);
        startActivity(gecisYap);
    }

    public void hadi3(View view) {
        Intent gecisYap = new Intent(cuzdan.this, cuzdan.class);
        startActivity(gecisYap);
    }

    public void hadi4(View view) {
        Intent gecisYap = new Intent(cuzdan.this, Hesap.class);
        startActivity(gecisYap);
    }

    public void qr(View view) {
        Intent gecisYap = new Intent(cuzdan.this, satinal.class);
        startActivity(gecisYap);

    }
}

