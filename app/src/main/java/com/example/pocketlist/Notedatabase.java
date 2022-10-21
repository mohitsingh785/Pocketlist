package com.example.pocketlist;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = Note.class,version=1)
public abstract class Notedatabase  extends RoomDatabase {

    private static Notedatabase instance;

    public abstract NoteDao noteDao();



    public static synchronized Notedatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),Notedatabase.class,"note_database").fallbackToDestructiveMigration().build();
        }
        return instance;

    }

}
