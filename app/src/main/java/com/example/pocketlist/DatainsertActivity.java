package com.example.pocketlist;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pocketlist.databinding.ActivityDatainsertBinding;

public class DatainsertActivity extends AppCompatActivity {
ActivityDatainsertBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDatainsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String type=getIntent().getStringExtra("type");
        if(type.equals("update")){
          setTitle("update");
          binding.title1.setText(getIntent().getStringExtra("title"));
          binding.disp1.setText(getIntent().getStringExtra("disp"));
          int id=getIntent().getIntExtra("id",0);
          binding.button2.setText("Update");
          binding.button2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent();

                  intent.putExtra("title", binding.title1.getText().toString());
                  intent.putExtra("disp", binding.disp1.getText().toString());
                  intent.putExtra("id",id);
                  setResult(RESULT_OK, intent);
                  finish();
              }
          });
        }
      else {
              setTitle("Add Mode");
            binding.button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();

                    intent.putExtra("title", binding.title1.getText().toString());
                    intent.putExtra("disp", binding.disp1.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DatainsertActivity.this,MainActivity.class));
    }
}