package com.example.ceptefestt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ceptefestt.Giris;
import com.example.ceptefestt.MainActivity;
import com.example.ceptefestt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;


public class kaydol extends AppCompatActivity {

    EditText edt_kullaniciAdi,edt_Ad,edt_Email,edt_Sifre;
    Button btn_Kaydol;
    TextView txt_GirisSayfasinaGit;

    FirebaseAuth yetki;
    DatabaseReference yol;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaydol);

        edt_kullaniciAdi=findViewById(R.id.editText2);
        edt_Ad=findViewById(R.id.adii);
        edt_Email=findViewById(R.id.epostasii);
        edt_Sifre=findViewById(R.id.sifresii);

        btn_Kaydol=findViewById(R.id.kayitt);
        txt_GirisSayfasinaGit=findViewById(R.id.GirisSayfasinaGit);
        yetki=FirebaseAuth.getInstance();


        txt_GirisSayfasinaGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(kaydol.this, MainActivity.class));
            }
        });


        btn_Kaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd =new ProgressDialog(kaydol.this);
                pd.setMessage("Lütfen bekleyiniz..");
                pd.show();

                String str_kullaniciAdi = edt_kullaniciAdi.getText().toString();
                String str_Ad = edt_Ad.getText().toString();
                String str_Email = edt_Email.getText().toString();
                String str_Sifre = edt_Sifre.getText().toString();

                if (TextUtils.isEmpty(str_kullaniciAdi)||TextUtils.isEmpty(str_Ad)||TextUtils.isEmpty(str_Email)||TextUtils.isEmpty(str_Sifre))
                {
                    Toast.makeText(kaydol.this, "Lütfen bütün alanları doldurunuz..", Toast.LENGTH_SHORT).show();
                }
                else if (str_Sifre.length()<6)
                {
                    Toast.makeText(kaydol.this, "Şifreniz minimum 6 karakter olmalı..", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Yeni kullanıcı kayıt etme işlemi
                    kaydet(str_kullaniciAdi,str_Ad,str_Email,str_Sifre);
                }


            }
        });

    }
    private void kaydet(final String kullaniciadi, final String ad, final String email, final String sifre)
    {
        //Yeni kullanıcı kayıt etme işlemi
        yetki.createUserWithEmailAndPassword(email,sifre)
                .addOnCompleteListener(kaydol.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            FirebaseUser firebaseKullanici= yetki.getCurrentUser();

                            String kullaniciId = firebaseKullanici.getUid();
                            yol = FirebaseDatabase.getInstance().getReference().child("Kullanıcılar").child(kullaniciId);
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("id",kullaniciId);
                            hashMap.put("kullaniciadi",kullaniciadi.toLowerCase());
                            hashMap.put("ad",ad);
                            hashMap.put("eposta",email);
                            hashMap.put("sfr",sifre);

                            yol.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        pd.dismiss();

                                        Intent intent = new Intent(kaydol.this, Giris.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);

                                    }

                                }
                            });

                        }
                        else
                        {
                            pd.dismiss();
                            Toast.makeText(kaydol.this, "Bu mail veya şifre ile kayıt başarısız...", Toast.LENGTH_LONG).show();
                        }


                    }
                });

    }

}
