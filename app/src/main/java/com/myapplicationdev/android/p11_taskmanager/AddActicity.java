package com.myapplicationdev.android.p11_taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActicity extends AppCompatActivity {

    EditText etName, etDesc;
    Button btnAddTask, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_acticity);

        etName = findViewById(R.id.editTextName);
        etDesc = findViewById(R.id.editTextDesc);
        btnAddTask = findViewById(R.id.buttonAddTask);
        btnCancel = findViewById(R.id.buttonCancel);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}