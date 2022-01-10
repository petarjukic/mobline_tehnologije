package com.example.vjezba6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubjectsActivity extends AppCompatActivity {

    private List<Predmet> predmetList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter rvAdapter;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        predmetList = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference("vjezba6mobilne");


        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("podatak", "Tu sam");
                for(DataSnapshot ds:snapshot.getChildren()){
                    Predmet data = ds.getValue(Predmet.class);
                    Log.d("podatak", data.getIme());
                    predmetList.add(data);
                }

                rvAdapter = new RecyclerViewAdapter(predmetList);
                recyclerView.setAdapter(rvAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}