package com.example.vjezba4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class Note implements Parcelable {

    public Note(String title, String column) {
        this.title = title;
        this.column = column;
    }

    @PrimaryKey(autoGenerate = true)
    public int nid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "column")
    public String column;

    protected Note(Parcel in) {
        nid = in.readInt();
        title = in.readString();
        column = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(nid);
        parcel.writeString(title);
        parcel.writeString(column);
    }
}
