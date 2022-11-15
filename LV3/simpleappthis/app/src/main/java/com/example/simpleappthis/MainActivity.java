package com.example.simpleappthis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //recycle
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    PostAdapter adapter;

    Spinner spinner;
    Locale locale;
    String currentLanguage = "cro", currentLang;

    Button btn;
    ArrayList<Student> lstudenti = new ArrayList<>();
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, PersonalInfoActivity.class);

        if(getIntent().hasExtra("Student")){

            lstudenti = getIntent().getParcelableArrayListExtra("Student");

            setContentView(R.layout.activity_main);
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            //progressBar = findViewById(R.id.progressBar);
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new PostAdapter(lstudenti);
            recyclerView.setAdapter(adapter);
            //fetchPosts();
            intent.putParcelableArrayListExtra("Student",lstudenti);

        }



        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                startActivity(intent);
            }
        });


        //spinner
        currentLanguage = getIntent().getStringExtra(currentLang);
        spinner = findViewById(R.id.spinner);
        List<String> list = new ArrayList<>();
        list.add("Select Language");
        list.add("English");
        list.add("Croatian");
        list.add("Hungarian");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        setLocale("en");
                        break;
                    case 2:
                        setLocale("hr");
                        break;
                    case 3:
                        setLocale("hu");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void setLocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            locale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = locale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this,
                    MainActivity.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        } else {
            Toast.makeText(MainActivity.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }
}