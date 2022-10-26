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
    public TextInputEditText text;
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        text = findViewById(R.id.text);
        button = findViewById(R.id.StudentinfoBtn);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(text.length()!=0) {
                    startActivity(new Intent(PersonalInfoActivity.this, StudentInfoActivity.class).putExtra("name", text.getText().toString()));

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Ime nije uneseno", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

}