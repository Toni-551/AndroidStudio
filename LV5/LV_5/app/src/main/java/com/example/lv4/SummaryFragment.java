package com.example.lv4;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SummaryFragment extends Fragment {

    TextView ime;
    TextView prezime;
    TextView datum;
    TextView predmet;
    TextView profesor;
    TextView godina;
    TextView sati;
    TextView lvsati;
    Button btn;
    Student student = new Student();

    public SummaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SummaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SummaryFragment newInstance(String param1, String param2) {
        SummaryFragment fragment = new SummaryFragment();
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
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intent = new Intent(getActivity(), MainActivity.class);

        ime = view.findViewById(R.id.ime);
        prezime = view.findViewById(R.id.prezime);
        datum = view.findViewById(R.id.datum);
        predmet = view.findViewById(R.id.predmet);
        profesor = view.findViewById(R.id.profesor);
        godina = view.findViewById(R.id.godina);
        sati = view.findViewById(R.id.sati);
        lvsati = view.findViewById(R.id.lvsati);

        btn = view.findViewById(R.id.btn);

    }
    public void SetSummaryData(Student personalInfo,Student studentInfo) {
        Student personal = personalInfo;
        Student studentinf = studentInfo;
        if(personalInfo==null){
            personal = new Student();
        }
        if(studentInfo==null){
            studentinf = new Student();
        }
        student = new Student(personal, studentinf);

        ime.setText(getString(R.string.ime) + ": " + student.getName());
        prezime.setText(getString(R.string.prezime) + ": " + student.getSurname());
        datum.setText(getString(R.string.datum_rodenja) + ": " + student.getBrithDate());
        predmet.setText(getString(R.string.naziv_predmeta) + ": " +  student.getProfessor());
        profesor.setText(getString(R.string.ime_profesora) + ": " + student.getSubject());
        godina.setText(getString(R.string.godina) + ": " + student.getAcademicYear());
        sati.setText(getString(R.string.sati_predavanja) + ": " + student.getHoursOfLectures());
        lvsati.setText(getString(R.string.lv_sati) + ": " + student.getHoursOfPractice());
    }
}