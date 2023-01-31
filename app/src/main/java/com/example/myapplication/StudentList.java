package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        //Floating Action Button configuration
        fat = findViewById(R.id.fat);

        fat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NewStudent.class);
                startActivity(intent);
            }
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

        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recyclerView.getVisibility() == View.VISIBLE){
                    recyclerView.setVisibility(View.GONE);
                }else{
                    recyclerView.setVisibility(View.VISIBLE);
                }

            }
        });
        exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recyclerView2.getVisibility() == View.VISIBLE){
                    recyclerView2.setVisibility(View.GONE);
                }else{
                    recyclerView2.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id;
        switch (item.getItemId()){
            case 101:
                id = adapter.db.get(item.getGroupId()).getId();
                adapter.db.remove(item.getGroupId());
                mdh.delete(id);
                return delete(id);
            case 102:
                id = adapter2.db.get(item.getGroupId()).getId();
                adapter2.db.remove(item.getGroupId());
                mdh.delete(id);
                return delete(id);
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
            displayToast("Delete");
        }else{
            displayToast("Fail on delete");
        }
        adapter.notifyDataSetChanged();
        return true;
    }

    void DataInArrayList() {
        Cursor cursor = mdh.ReadAll();
        if (cursor.getCount() == 0) {
            displayToast("Empty");
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