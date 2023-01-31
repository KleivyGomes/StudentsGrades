package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicReference;

public class NewStudent extends AppCompatActivity {
    EditText EditName, EditTest1, EditTest2, EditHomework, EditOee;
    Button add, cancel;
    MyDatabaseHelper mb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);
        EditName = findViewById(R.id.newName);
        EditTest1 = findViewById(R.id.newTest1);
        EditTest2 = findViewById(R.id.newTest2);
        EditHomework = findViewById(R.id.newHomework);
        EditOee = findViewById(R.id.newOEE);


        mb = new MyDatabaseHelper(this);

        add = findViewById(R.id.btn_add);
        cancel = findViewById(R.id.btn_cancel);

        cancel.setOnClickListener(view -> {
            Intent intent = new Intent(this,StudentList.class);
            startActivity(intent);
        });

        add.setOnClickListener(view -> {
            String name, test1,test2,homework,oee;
            name = EditName.getText().toString();
            test1 = EditTest1.getText().toString();
            test2 = EditTest2.getText().toString();
            homework = EditHomework.getText().toString();
            oee = EditOee.getText().toString();
            double evaluation = Integer.valueOf(test1) * 0.3 + Integer.valueOf(test2) * 0.3 +
                    Integer.valueOf(homework) * 0.3 + Integer.valueOf(oee) * 0.1;
            mb.add(name,test1,test2,homework,oee,String.valueOf(evaluation));
            Intent intent = new Intent(this,StudentList.class);
            startActivity(intent);
        });


    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}