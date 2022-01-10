package com.example.vjezba6;

import androidx.annotation.NonNull;
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

import java.util.ArrayList;
import java.util.List;

public class EditFieldActivity extends AppCompatActivity {

    private List<Predmet> predmetList;
    private Integer position;

    private DatabaseReference dbRef;
    private EditText godina;
    private EditText ime;
    private EditText predavac;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_field);

        godina = (EditText) findViewById(R.id.editGodina);
        ime = (EditText) findViewById(R.id.editIme);
        predavac = (EditText) findViewById(R.id.editPredavac);
        btnSave = (Button) findViewById(R.id.btnSave);

        predmetList = new ArrayList<>();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        dbRef = FirebaseDatabase.getInstance().getReference("vjezba6mobilne");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    Predmet data=ds.getValue(Predmet.class);
                    predmetList.add(data);
                }

                Predmet target = predmetList.get(position);
                godina.setText(target.getGodina().toString(), TextView.BufferType.EDITABLE);
                ime.setText(target.getIme(),TextView.BufferType.EDITABLE);
                predavac.setText(target.getPredavac(),TextView.BufferType.EDITABLE);

                predmetList.remove(position);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void setData() {
        Predmet predmet = new Predmet();
        predmet.setGodina(Integer.parseInt(godina.getText().toString()));
        predmet.setIme(ime.getText().toString());
        predmet.setPredavac(predavac.getText().toString());

        predmetList.add(position, predmet);
        dbRef.setValue(predmetList);

        startActivity(new Intent(this, SubjectsActivity.class));
    }
}