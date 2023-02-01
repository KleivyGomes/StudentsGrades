package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StudentList extends AppCompatActivity {
    RecyclerView recyclerView, recyclerView2;
    FloatingActionButton fat;
    ArrayList<Database> db,approvedList, examList;
    Adapter adapter, adapter2;
    MyDatabaseHelper mdh;
    Button approved, exam;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        //Floating Action Button configuration
        fat = findViewById(R.id.fat);

        fat.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),NewStudent.class);
            startActivity(intent);
        });

        //Recycler View configuration

        db = new ArrayList<>();
        mdh = new MyDatabaseHelper(this);

        examList = new ArrayList<>();
        approvedList = new ArrayList<>();

        DataInArrayList();

        for(int i = 0;i< db.size();i++){
            if(Float.valueOf(db.get(i).getEvaluation())<12){
                examList.add(db.get(i));
            }else{
                approvedList.add(db.get(i));
            }
        }


        adapter = new Adapter(this,approvedList);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



        adapter2 = new Adapter(this,examList);

        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(adapter2);

        approved = findViewById(R.id.approved);
        exam = findViewById(R.id.exam);

        approved.setOnClickListener(v -> {
            if(recyclerView.getVisibility() == View.VISIBLE){
                recyclerView.setVisibility(View.GONE);
            }else{
                recyclerView.setVisibility(View.VISIBLE);
            }

        });
        exam.setOnClickListener(v -> {
            if(recyclerView2.getVisibility() == View.VISIBLE){
                recyclerView2.setVisibility(View.GONE);
            }else{
                recyclerView2.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id;
        switch (item.getItemId()){
            case 101:
                id = adapter.db.get(item.getGroupId()).getId();
                builder = new AlertDialog.Builder(this);
                builder.setTitle("ALERT!!!").setMessage(getString(R.string.deleteMessage))
                        .setCancelable(true).setPositiveButton("YES", (dialogInterface, i) -> {
                            adapter.db.remove(item.getGroupId());
                            delete(id);
                        }).setNegativeButton("NO", (dialogInterface, i) -> dialogInterface.cancel())
                        .show();
                return true;

            case 102:
                id = adapter2.db.get(item.getGroupId()).getId();
                builder = new AlertDialog.Builder(this);
                builder.setTitle("ALERT!!!").setMessage(getString(R.string.deleteMessage))
                        .setCancelable(true).setPositiveButton("YES", (dialogInterface, i) -> {
                            adapter2.db.remove(item.getGroupId());
                            delete(id);
                        }).setNegativeButton("NO", (dialogInterface, i) -> dialogInterface.cancel())
                        .show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    boolean delete(int id){
        boolean result = mdh.delete(id);
        for(int i=0;i<db.size();i++){
            if(db.get(i).getId() == id){
                db.remove(i);
            }
        }

        if(result){
            displayToast(getString(R.string.deleted));
        }else{
            displayToast(getString(R.string.failDelete));
        }
        adapter.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        return true;
    }

    void DataInArrayList() {
        Cursor cursor = mdh.ReadAll();
        if (cursor.getCount() == 0) {
            displayToast(getString(R.string.emptyDatabase));
        } else {
            while (cursor.moveToNext()) {
                Database database = new Database(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2),cursor.getString(3),cursor.getString(4),
                        cursor.getString(5),cursor.getString(6));
                db.add(database);
            }
        }
    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}