package com.example.vjezba4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailActivity extends AppCompatActivity {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());

    Button saveButton, deleteButton;
    EditText title, content;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "note").build();
        title = findViewById(R.id.textView5);
        content = findViewById(R.id.textView6);
        saveButton = findViewById(R.id.button2);
        deleteButton = findViewById(R.id.button3);

        //DODANO
        if(getIntent().hasExtra("selected_note")) {
            Note note = getIntent().getParcelableExtra("selected_note");

            title.setText(note.title);
            content.setText(note.column);

            buttonClick(note);
        }
    }

    private void buttonClick(Note note) {

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noteTitle = title.getText().toString();
                String noteContent = content.getText().toString();

                if(TextUtils.isEmpty(noteTitle) || TextUtils.isEmpty(noteContent)) {
                    Toast.makeText(DetailActivity.this, "Input title and content", Toast.LENGTH_LONG).show();
                }
                else {
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            note.title = noteTitle;
                            note.column = noteContent;
                            db.noteDAO().updateNote(note);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(DetailActivity.this)
                        .setTitle("Delete Note")
                        .setMessage("Do you wnat to delete note?")
                        .setPositiveButton("OK", null)
                        .setNegativeButton("Cancel", null)
                        .show();

                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        executorService.execute(new Runnable() {
                            @Override
                            public void run() {
                                db.noteDAO().deleteNote(note);

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                                        Toast.makeText(DetailActivity.this, "Note is deleted", Toast.LENGTH_LONG).show();
                                        startActivity(intent);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }
}