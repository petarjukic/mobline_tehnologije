package com.example.vjezba4;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDAO {
    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Insert
    void insertAll(Note...notes);

    @Update(entity = Note.class)
    void updateNote(Note...note);

    @Delete
    void deleteNote(Note note);
}
