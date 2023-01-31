package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_enter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btn_enter = findViewById(R.id.btn_enter);

        btn_enter.setOnClickListener(view -> {
            Intent intent = new Intent(this,StudentList.class);
            startActivity(intent);
        });


    }
}