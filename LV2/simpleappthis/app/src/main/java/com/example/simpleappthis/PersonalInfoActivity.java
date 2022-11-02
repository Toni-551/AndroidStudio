package com.example.simpleappthis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class PersonalInfoActivity extends AppCompatActivity {


    public Button button;
    public TextInputEditText ime;
    public TextInputEditText prezime;
    public TextInputEditText datum;
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        ime = findViewById(R.id.ime);
        prezime = findViewById(R.id.prezime);
        datum = findViewById(R.id.datum);
        button = findViewById(R.id.StudentinfoBtn);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(ime.length()!=0 && prezime.length()!=0 && datum.length()!=0) {
                    startActivity(new Intent(PersonalInfoActivity.this, StudentInfoActivity.class).putExtra("name", ime.getText().toString()).putExtra("surname", prezime.getText().toString()).putExtra("date", datum.getText().toString()));

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Niste unijeli sva slova uneseno", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

}