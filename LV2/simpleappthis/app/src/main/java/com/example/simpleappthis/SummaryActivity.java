package com.example.simpleappthis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        Intent ointent= getIntent();

        ime = findViewById(R.id.ime);
        prezime = findViewById(R.id.prezime);
        datum = findViewById(R.id.datum);
        predmet = findViewById(R.id.predmet);
        profesor = findViewById(R.id.profesor);
        godina = findViewById(R.id.godina);
        sati = findViewById(R.id.sati);
        lvsati = findViewById(R.id.lvsati);
        ime.setText("Ime: "+ ointent.getStringExtra("name"));
        prezime.setText("Prezime: "+ ointent.getStringExtra("surname"));
        datum.setText("Datum rođenja: "+ ointent.getStringExtra("date"));
        predmet.setText("Naziv predmeta: "+ ointent.getStringExtra("predmet"));
        profesor.setText("Ime profesora: "+ ointent.getStringExtra("profesor"));
        godina.setText("Godina: "+ ointent.getStringExtra("godina"));
        sati.setText("Sati predavanja: "+ ointent.getStringExtra("sati"));
        lvsati.setText("LV sati: "+ ointent.getStringExtra("lvsati"));

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(SummaryActivity.this, PersonalInfoActivity.class));
            }
        });
    }
}