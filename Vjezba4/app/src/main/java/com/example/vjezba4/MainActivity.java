package com.example.vjezba4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());

    AppDatabase database;
    TextView textView;
    RecyclerView recyclerView;
    MyAdapter adapter;
    FloatingActionButton floatingActionButton;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "note").allowMainThreadQueries().build();

        NoteDAO noteDAO = database.noteDAO();
        floatingActionButton = findViewById(R.id.floatingActionButton);

        executeService();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });

        /*
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });*/
    }

    private void executeService() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Note> notes = database.noteDAO().getAll();
                adapter = new MyAdapter(notes, MainActivity.this::onNoteClick);  //JOS PROVJERI STA I KAKO
                recyclerView.setAdapter(adapter);
                /*handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //adapter = new MyAdapter(notes, MainActivity.this::onNoteClick);  //JOS PROVJERI STA I KAKO
                        //recyclerView.setAdapter(adapter);
                    }
                });

                 */
            }
        });
    }

    private void addNote() {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    public void onNoteClick(int position) {
        List<Note> notes = database.noteDAO().getAll();
        //notes.get(position);

        // DODANO
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("selected_note", notes.get(position));
        startActivity(intent);
    }
}