package com.example.pocketlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.example.pocketlist.databinding.ActivityMainBinding;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
private NoteViewModel noteViewModel1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteViewModel1 = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NoteViewModel.class);
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,DatainsertActivity.class);
                 intent.putExtra("type","AddMode");
                startActivityForResult(intent,1);
            }
        });

   binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
   binding.recyclerView.setHasFixedSize(true);
        RVAdapter adapter=new RVAdapter();
   binding.recyclerView.setAdapter(adapter);
   noteViewModel1.getallNotes().observe(this, new Observer<List<Note>>() {
       @Override
       public void onChanged(List<Note> notes) {
           adapter.submitList(notes);
       }
   });

   new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

       @Override
       public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
           return false;
       }

       @Override
       public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
           if (direction==ItemTouchHelper.RIGHT){
               Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
      noteViewModel1.delete(adapter.getNote(viewHolder.getAdapterPosition()));}
           else{

               Intent intent=new Intent(MainActivity.this,DatainsertActivity.class);
               intent.putExtra("type","update");
               intent.putExtra("title",adapter.getNote(viewHolder.getAdapterPosition()).getTitle());
               intent.putExtra("disp",adapter.getNote(viewHolder.getAdapterPosition()).getDisp());
               intent.putExtra("id",adapter.getNote(viewHolder.getAdapterPosition()).getId());
               startActivityForResult(intent,2);

           }
       }
   }).attachToRecyclerView(binding.recyclerView);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            String title=data.getStringExtra("title");
            String disp=data.getStringExtra("disp");
            Note note=new Note(title,disp);
                 noteViewModel1.insert(note);
            Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode==2){
            String title=data.getStringExtra("title");
            String disp=data.getStringExtra("disp");
            Note note=new Note(title,disp);
            note.setId(data.getIntExtra("id",0));
            noteViewModel1.update(note);
            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
        }
    }


}