package com.example.simpleappthis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class StudentInfoActivity extends AppCompatActivity {

    Button predbtn;
    TextInputEditText predmet;
    TextInputEditText profesor;
    TextInputEditText godina;
    TextInputEditText sati;
    TextInputEditText lvsati;
    TextView text;
    //Student student = new Student();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        Intent intent = new Intent(StudentInfoActivity.this, SummaryActivity.class);

        predmet = findViewById(R.id.predmet);
        profesor = findViewById(R.id.profesor);
        godina = findViewById(R.id.godina);
        sati = findViewById(R.id.sati);
        lvsati = findViewById(R.id.lvsati);
        predbtn = findViewById(R.id.btn);


        predbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(predmet.length()!=0 && profesor.length()!=0 && godina.length()!=0 && sati.length()!=0 &&lvsati.length()!=0 ) {
                    ArrayList<Student> lstudenti = getIntent().getParcelableArrayListExtra("Student");
                    lstudenti.get(lstudenti.size() - 1).setSubject(predmet.getText().toString());
                    lstudenti.get(lstudenti.size() - 1).setProfessor(profesor.getText().toString());
                    lstudenti.get(lstudenti.size() - 1).setAcademicYear(godina.getText().toString());
                    lstudenti.get(lstudenti.size() - 1).setHoursOfLectures(sati.getText().toString());
                    lstudenti.get(lstudenti.size() - 1).setHoursOfPractice(lvsati.getText().toString());
                    intent.putParcelableArrayListExtra("Student", lstudenti);
                    startActivity(intent);

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Nisu unesena sva polja", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}