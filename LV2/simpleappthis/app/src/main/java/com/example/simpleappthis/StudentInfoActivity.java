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
    public TextInputEditText predmet;
    public TextInputEditText profesor;
    public TextInputEditText godina;
    public TextInputEditText sati;
    public TextInputEditText lvsati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        Intent ointent= getIntent();

        predmet = findViewById(R.id.predmet);
        profesor = findViewById(R.id.profesor);
        godina = findViewById(R.id.godina);
        sati = findViewById(R.id.sati);
        lvsati = findViewById(R.id.lvsati);
        predbtn = findViewById(R.id.btn);

        predbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(predmet.length()!=0 && profesor.length()!=0 && godina.length()!=0 && sati.length()!=0 &&lvsati.length()!=0 ) {
                    startActivity(new Intent(StudentInfoActivity.this, SummaryActivity.class).putExtra("lvsati", lvsati.getText().toString()).putExtra("sati", sati.getText().toString()).putExtra("godina", godina.getText().toString()).putExtra("predmet", predmet.getText().toString()).putExtra("profesor", profesor.getText().toString()).putExtra("name", ointent.getStringExtra("name")).putExtra("surname", ointent.getStringExtra("surname")).putExtra("date", ointent.getStringExtra("date")));

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Nisu unesena sva polja", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}