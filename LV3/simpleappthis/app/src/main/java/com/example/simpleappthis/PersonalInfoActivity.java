package com.example.simpleappthis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class PersonalInfoActivity extends AppCompatActivity {


    Button button;
    TextInputEditText ime;
    TextInputEditText prezime;
    TextInputEditText datum;
    Student student = new Student();
    ArrayList<Student> lstudenti = new ArrayList<Student>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        Intent intent = new Intent(PersonalInfoActivity.this, StudentInfoActivity.class);

        ime = findViewById(R.id.ime);
        prezime = findViewById(R.id.prezime);
        datum = findViewById(R.id.datum);
        button = findViewById(R.id.StudentinfoBtn);



        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(ime.length()!=0 && prezime.length()!=0 && datum.length()!=0) {
                    if(getIntent().hasExtra("Student")){
                        lstudenti = getIntent().getParcelableArrayListExtra("Student");
                    }
                    student.setName(ime.getText().toString());
                    student.setSurname(prezime.getText().toString());
                    student.setBrithDate(datum.getText().toString());
                    lstudenti.add(student);
                    intent.putParcelableArrayListExtra("Student", lstudenti);
                    startActivity(intent);//.putExtra("name", ime.getText().toString()).putExtra("surname", prezime.getText().toString()).putExtra("date", datum.getText().toString()));

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Niste unijeli sva slova uneseno", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

}