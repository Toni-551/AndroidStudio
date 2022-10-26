package com.example.simpleappthis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class StudentInfoActivity extends AppCompatActivity {

    public Button predbtn;
    public TextInputEditText predtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        Intent ointent= getIntent();

        predtext = findViewById(R.id.txt);
        predbtn = findViewById(R.id.btn);

        predbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(predtext.length()!=0) {
                    startActivity(new Intent(StudentInfoActivity.this, SummaryActivity.class).putExtra("predmet", predtext.getText().toString()).putExtra("name", ointent.getStringExtra("name")));

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Predmet nije uneseno", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}