package com.example.vjezba4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity2 extends AppCompatActivity {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());

    EditText title, content;
    FloatingActionButton button;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "note").build();
        title = findViewById(R.id.textView3);
        content = findViewById(R.id.textView4);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noteTitle = title.getText().toString();
                String noteContent = content.getText().toString();

                if(TextUtils.isEmpty(noteTitle) || TextUtils.isEmpty(noteContent)) {
                    Toast.makeText(MainActivity2.this, "Input title and content", Toast.LENGTH_LONG).show();
                }
                else {
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            Note note = new Note(noteTitle, noteContent);
                            db.noteDAO().insertAll(note);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }
            }
        });
    }
}