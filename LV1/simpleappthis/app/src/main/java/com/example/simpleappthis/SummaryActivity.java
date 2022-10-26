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
    public TextView predmet;
    public Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Intent ointent= getIntent();

        ime = findViewById(R.id.iem);
        predmet = findViewById(R.id.pred);
        ime.setText("Ime: "+ ointent.getStringExtra("name"));
        predmet.setText("Predmet: "+ ointent.getStringExtra("predmet"));

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(SummaryActivity.this, PersonalInfoActivity.class));
            }
        });
    }
}