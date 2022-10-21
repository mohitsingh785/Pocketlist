package com.example.pocketlist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.List;

public class NoteRepo {

    private NoteDao noteDao;
    private LiveData<List<Note>> notelist;

    public NoteRepo(Application application){


        Notedatabase notedatabase=Notedatabase.getInstance(application);
        noteDao=notedatabase.noteDao();
        notelist=noteDao.getalldata();
    }

    public  void insertdata(Note note){new InsertTask(noteDao).execute(note); };
    public  void deletedata(Note note){new DeleteTask(noteDao).execute(note);};
    public  void updatedata(Note note){new UpdateTask(noteDao).execute(note);};
    public LiveData<List<Note>> getalldata(){
        return notelist;
    }

    private static class InsertTask extends AsyncTask<Note,Void,Void>{
 private NoteDao noteDao;

        public InsertTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }   private static class DeleteTask extends AsyncTask<Note,Void,Void>{
 private NoteDao noteDao;

        public DeleteTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }   private static class UpdateTask extends AsyncTask<Note,Void,Void>{
 private NoteDao noteDao;

        public UpdateTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

}
