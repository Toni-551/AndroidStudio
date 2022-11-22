package com.example.lv4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    Student student = new Student();
    List<Student> lstudenti = new ArrayList<>();

    private final static String  FILE_SUMMARY = "SummaryInfo.json";
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, CreateNewRecordActivity.class);

        LoadJSONSummary();

        if(lstudenti != null)
        {
            if(!lstudenti.isEmpty())
            {
                recyclerView = findViewById(R.id.recyclerView);
                //progressBar = findViewById(R.id.progressBar);
                layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                // adapter = new StudentAdapter(studentList,subjectList);
                adapter = new PostAdapter(lstudenti);
                recyclerView.setAdapter(adapter);
            }
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

    private String LoadJson(Context context, String FILE_NAME)
    {
        String response = null;
        try {
            File file = new File(context.getFilesDir(),FILE_NAME);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();// This responce will have Json Format String
            response = stringBuilder.toString();
        }
        catch (IOException e)
        {
        }
        return response;
    }

    private void jsonStringToObjectSummary(String sJson) {
        JSONArray oArray = null;
        try {
            oArray = new JSONArray(sJson);
            int n = oArray.length();
            for (int i = 0; i < n; i++) {
                JSONObject summary = oArray.getJSONObject(i);
                String name = summary.getString("Name");
                String surname = summary.getString("Surname");
                String image = summary.getString("Image");
                String subject = summary.getString("Subject");
                Student newStudent = new Student();
                newStudent.setName(name);
                newStudent.setSurname(surname);
                newStudent.setSubject(subject);
                newStudent.setImage(image);
                lstudenti.add(newStudent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void LoadJSONSummary()
    {
        String json = LoadJson(MainActivity.this,FILE_SUMMARY);
        if(json != null)
        {
            jsonStringToObjectSummary(json);
        }
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