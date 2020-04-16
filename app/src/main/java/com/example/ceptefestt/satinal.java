package com.example.ceptefestt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ceptefestt.R;
import com.example.ceptefestt.scanCode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class satinal extends AppCompatActivity implements View.OnClickListener{
    public static TextView res_text;
    Button scan_btn;

    private EditText yapilacak;
    private Button ekle,gorBtn;
    private TextView gor;

    FirebaseDatabase db;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_satinal);
            res_text=(TextView)findViewById(R.id.result_text);
            scan_btn=(Button)findViewById(R.id.btn_scan);
            baslangic();

            scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), scanCode.class));

            }
        });
    }

    private void baslangic() {
        db=FirebaseDatabase.getInstance();
        res_text=(TextView) findViewById(R.id.result_text);
        ekle=(Button)findViewById(R.id.button23);
        gorBtn=(Button)findViewById(R.id.button);
        gor=(TextView)findViewById(R.id.textView21);

        ekle.setOnClickListener(this);
        gorBtn.setOnClickListener(this);

    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button23:
                String todo=res_text.getText().toString().trim();
                yapilacakEkle(todo);
                break;
            case R.id.button:
                yapilacaklariGor();
                break;
        }
    }

    private void yapilacakEkle(String yapilacak){

        DatabaseReference dbRef = db.getReference("yapilacaklar");
        String key = dbRef.push().getKey();
        DatabaseReference dbRefYeni = db.getReference("yapilacaklar/"+key);
        dbRefYeni.setValue(yapilacak);




    }
    private void yapilacaklariGor(){


        DatabaseReference okuma = db.getReference("yapilacaklar");
        okuma.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                gor.setText("");
                Iterable<DataSnapshot> keys = dataSnapshot.getChildren();
                for (DataSnapshot key: keys) {

                    gor.append(key.getValue().toString()+"\n");

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
