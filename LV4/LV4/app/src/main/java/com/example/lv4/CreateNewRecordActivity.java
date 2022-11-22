package com.example.lv4;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CreateNewRecordActivity extends AppCompatActivity {

    private final static String  FILE_SUMMARY = "SummaryInfo.json";

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private PersonalInfoFragment personalInfoFragment;
    private StudentInfoFragment studentInfoFragment;
    private SummaryFragment summaryFragment;

    public void onBackPressed()
    {
        CreateNewRecordActivity.this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_record);

        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        //add the fragments
        viewPagerAdapter.add(new PersonalInfoFragment(),getString(R.string.personalInfo));
        viewPagerAdapter.add(new StudentInfoFragment(),getString(R.string.studentInfo));
        viewPagerAdapter.add(new SummaryFragment(),getString(R.string.summary));

        //Set the adapter
        viewPager.setAdapter(viewPagerAdapter);

        //Display fragments title into tabLayout
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new  ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if(position == 2)
                {
                    personalInfoFragment = (PersonalInfoFragment) viewPagerAdapter.getItem(0);
                    studentInfoFragment = (StudentInfoFragment) viewPagerAdapter.getItem(1);
                    summaryFragment = (SummaryFragment) viewPagerAdapter.getItem(2);

                    Student personalInfo = personalInfoFragment.SendData();
                    Student studentInfo = studentInfoFragment.SendData();

                    if(personalInfo != null && studentInfo != null){
                        summaryFragment.SetSummaryData(personalInfo,studentInfo);
                        if(personalInfo.getImage() != null) {
                            summaryFragment.btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ParseListsData(new Student(personalInfo, studentInfo));
                                    startActivity(new Intent(CreateNewRecordActivity.this, MainActivity.class));
                                    CreateNewRecordActivity.this.finish();
                                }
                            });
                        }else{
                            Toast.makeText(CreateNewRecordActivity.this, R.string.summaryTostImage,Toast.LENGTH_SHORT).show();
                        }
                    }else if(personalInfo != null || studentInfo != null)
                    {
                        summaryFragment.SetSummaryData(personalInfo,studentInfo);
                        Toast.makeText(CreateNewRecordActivity.this, R.string.summaryTost,Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(CreateNewRecordActivity.this, R.string.summaryTost,Toast.LENGTH_SHORT).show();
                        if(summaryFragment != null)
                        {
                            summaryFragment.SetSummaryData(null,null);
                        }
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void WriteToJson(Context context,String sFileName , String json)
    {
        try {
            File fileDirectory = context.getFilesDir();
            if(!fileDirectory.exists())
            {
                fileDirectory.mkdirs();
            }
            File file = new File(fileDirectory,sFileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(json);
            bufferedWriter.close();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void ParseListsData(Student studentSave)
    {
        String oldJson = LoadJson(CreateNewRecordActivity.this,FILE_SUMMARY);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonarray = null;
        try{
            if(oldJson != null)
            {
                jsonarray = new JSONArray(oldJson);
            }
            else
            {
                jsonarray = new JSONArray();
            }

            jsonObject.put("Name", studentSave.getName());
            jsonObject.put("Surname", studentSave.getSurname());
            jsonObject.put("Image",studentSave.getImage());
            jsonObject.put("Subject", studentSave.getSubject());

            jsonarray.put(jsonObject);

        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        String json = jsonarray.toString();
        WriteToJson(CreateNewRecordActivity.this,FILE_SUMMARY,json);
    }

    private String LoadJson(Context context,String FILE_NAME)
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
    private void DisableTab(TabLayout TabLayout,int position,boolean toggle)
    {
        ((ViewGroup)TabLayout.getChildAt(0)).getChildAt(position).setEnabled(toggle);
        //((ViewGroup)TabLayout.getChildAt(0)).getChildAt(position).s(toggle);
    }

}