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

public class Hesap extends AppCompatActivity implements View.OnClickListener{

    private EditText ad,eposta,tel;
    private Button kaydet,gor;
    private TextView kayitlar;

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hesap);
        baslangic();
    }

    private void baslangic() {

        db=FirebaseDatabase.getInstance();
        ad=(EditText)findViewById(R.id.adEt);
        eposta=(EditText)findViewById(R.id.soyadEt);
        tel=(EditText)findViewById(R.id.yasEt);
        kaydet=(Button)findViewById(R.id.kaydetBtn);
        gor=(Button)findViewById(R.id.button14);
        kayitlar=(TextView)findViewById(R.id.kullaniciTv);

        kaydet.setOnClickListener(this);
        gor.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.kaydetBtn:
                String isim,epostasii;
                int telii;
                isim=ad.getText().toString().trim();
                epostasii=eposta.getText().toString().trim();
                telii= Integer.valueOf(tel.getText().toString().trim());
                kullaniciKaydet(isim,epostasii,telii);
                break;
            case R.id.button14:
                kayitlariGetir();
                break;
        }
    }

    private void kullaniciKaydet(String ad, String eposta, int tel){

        DatabaseReference dbRef = db.getReference("KullaniBilgi");
        String key = dbRef.push().getKey();
        DatabaseReference dbRefKeyli = db.getReference("KullaniBilgi/"+key);

        dbRefKeyli.setValue(new Kullanici(ad,eposta,tel));

    }

    private void kayitlariGetir() {
        kayitlar.setText("");



        DatabaseReference dbGelenler = db.getReference("KullaniBilgi");
        dbGelenler.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot gelenler : dataSnapshot.getChildren()) {

                    kayitlar.append(gelenler.getValue(Kullanici.class).getAd() + "\n");
                    kayitlar.append(gelenler.getValue(Kullanici.class).getEposta() + "\n");
                    kayitlar.append(gelenler.getValue(Kullanici.class).getTel() + "\n");

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void h2(View view) {
        Intent gecisYap = new Intent(Hesap.this, harita.class);
        startActivity(gecisYap);
    }

    public void b2(View view) {
        Intent gecisYap = new Intent(Hesap.this, bildirim.class);
        startActivity(gecisYap);
    }

    public void c2(View view) {
        Intent gecisYap = new Intent(Hesap.this, cuzdan.class);
        startActivity(gecisYap);
    }
}
