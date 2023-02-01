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

        cancel.setOnClickListener(v -> {
            Intent intent = new Intent(this,StudentList.class);
            startActivity(intent);
        });

        add.setOnClickListener(v -> {
            String name, test1,test2,homework,oee;
            boolean result;
            if(EditName.getText().toString().isEmpty() ||
                    EditTest1.getText().toString().isEmpty() ||
                    EditTest2.getText().toString().isEmpty() ||
                    EditHomework.getText().toString().isEmpty() ||
                    EditOee.getText().toString().isEmpty()){
                displayToast(getString(R.string.EmptyInput));
            }else{
                name = EditName.getText().toString();
                test1 = EditTest1.getText().toString();
                test2 = EditTest2.getText().toString();
                homework = EditHomework.getText().toString();
                oee = EditOee.getText().toString();
                long evaluation = Math.round(Integer.valueOf(test1) * 0.3 + Integer.valueOf(test2) * 0.3 +
                        Integer.valueOf(homework) * 0.3 + Integer.valueOf(oee) * 0.1) ;
                result = mb.add(name,test1,test2,homework,oee,String.valueOf(evaluation));
                if(result){
                    displayToast(getString(R.string.addSuccess));
                }else{
                    displayToast(getString(R.string.addFail));
                }
                Intent intent = new Intent(this,StudentList.class);
                startActivity(intent);
            }

        });
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}