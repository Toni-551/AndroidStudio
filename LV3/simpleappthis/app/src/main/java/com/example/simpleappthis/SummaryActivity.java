package com.example.simpleappthis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

    public TextView ime;
    public TextView prezime;
    public TextView datum;
    public TextView predmet;
    public TextView profesor;
    public TextView godina;
    public TextView sati;
    public TextView lvsati;
    public Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        ArrayList<Student> lstudenti = getIntent().getParcelableArrayListExtra("Student");

        Intent intent = new Intent(SummaryActivity.this, MainActivity.class);

        ime = findViewById(R.id.ime);
        prezime = findViewById(R.id.prezime);
        datum = findViewById(R.id.datum);
        predmet = findViewById(R.id.predmet);
        profesor = findViewById(R.id.profesor);
        godina = findViewById(R.id.godina);
        sati = findViewById(R.id.sati);
        lvsati = findViewById(R.id.lvsati);
        ime.setText(getString(R.string.ime)+ ": " + lstudenti.get(lstudenti.size() - 1).getName());
        prezime.setText(getString(R.string.prezime)+ ": " +lstudenti.get(lstudenti.size() - 1).getSurname());
        datum.setText(getString(R.string.datum_rodenja)+ ": " + lstudenti.get(lstudenti.size() - 1).getBrithDate());
        predmet.setText(getString(R.string.naziv_predmeta)+ ": " +lstudenti.get(lstudenti.size() - 1).getSubject());
        profesor.setText(getString(R.string.ime_profesora)+ ": " +lstudenti.get(lstudenti.size() - 1).getProfessor());
        godina.setText(getString(R.string.godina)+ ": " +lstudenti.get(lstudenti.size() - 1).getAcademicYear());
        sati.setText(getString(R.string.sati_predavanja)+ ": " +lstudenti.get(lstudenti.size() - 1).getHoursOfLectures());
        lvsati.setText(getString(R.string.lv_sati)+ ": " +lstudenti.get(lstudenti.size() - 1).getHoursOfPractice());

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                intent.putParcelableArrayListExtra("Student", lstudenti);
                startActivity(intent);
            }
        });
    }
}