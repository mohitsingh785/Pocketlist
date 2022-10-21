package com.example.pocketlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel  extends AndroidViewModel {

    private NoteRepo noteRepo;
    private LiveData<List<Note>> notelist;
    public NoteViewModel(@NonNull Application application) {



        super(application);
        noteRepo=new NoteRepo(application);
        notelist=noteRepo.getalldata();
    }
    public  void insert(Note note){noteRepo.insertdata(note); };
    public  void delete(Note note){noteRepo.deletedata(note); };
    public  void update(Note note){noteRepo.updatedata(note); };
    public LiveData<List<Note>> getallNotes(){
        return notelist;
    }

}
