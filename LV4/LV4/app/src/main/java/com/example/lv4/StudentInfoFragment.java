package com.example.lv4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentInfoFragment extends Fragment {

    Button predbtn;
    TextInputEditText predmet;
    TextInputEditText profesor;
    TextInputEditText godina;
    TextInputEditText sati;
    TextInputEditText lvsati;
    Student student = new Student();

    public StudentInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentInfoFragment newInstance(String param1, String param2) {
        StudentInfoFragment fragment = new StudentInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            student= getArguments().getParcelable("Student");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        predmet = view.findViewById(R.id.predmet);
        profesor = view.findViewById(R.id.profesor);
        godina = view.findViewById(R.id.godina);
        sati = view.findViewById(R.id.sati);
        lvsati = view.findViewById(R.id.lvsati);

    }
    public Student SendData()
    {
        try
        {
            String subject = predmet.getText().toString();
            String professor = profesor.getText().toString();
            String year = godina.getText().toString();
            String hours = sati.getText().toString();
            String lvHours = lvsati.getText().toString();

            student = new Student(subject,professor,year, hours, lvHours);

            if(student.getHoursOfLectures().length() != 0 &&student.getHoursOfPractice().length() != 0 && student.getSubject().length() != 0 && student.getProfessor().length() != 0 && student.getAcademicYear().length() != 0)
            {
                return student;
            }
            else
            {
                return null;
            }
        }catch (Exception e)
        {
            return null;
        }
    }
}