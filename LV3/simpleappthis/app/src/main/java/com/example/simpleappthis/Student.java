package com.example.simpleappthis;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Student implements Parcelable {
    private String Name;
    private String Surname;
    private String BrithDate;
    private String Subject;
    private String Professor;
    private String AcademicYear;
    private String HoursOfLectures;
    private String HoursOfPractice;

    public Student() {
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getBrithDate() {
        return BrithDate;
    }

    public String getSubject() {
        return Subject;
    }

    public String getProfessor() {
        return Professor;
    }

    public String getAcademicYear() {
        return AcademicYear;
    }

    public String getHoursOfLectures() {
        return HoursOfLectures;
    }

    public String getHoursOfPractice() {
        return HoursOfPractice;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public void setBrithDate(String brithDate) {
        BrithDate = brithDate;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public void setProfessor(String professor) {
        Professor = professor;
    }

    public void setAcademicYear(String academicYear) {
        AcademicYear = academicYear;
    }

    public void setHoursOfLectures(String hoursOfLectures) {
        HoursOfLectures = hoursOfLectures;
    }

    public void setHoursOfPractice(String hoursOfPractice) {
        HoursOfPractice = hoursOfPractice;
    }

    //parcel part
    public Student(Parcel in){
        String[] data= new String[8];

        in.readStringArray(data);
        this.Name = data[0];
        this.Surname = data[1];
        this.BrithDate = data[2];
        this.Subject = data[3];
        this.Professor = data[4];
        this.AcademicYear = data[5];
        this.HoursOfLectures = data[6];
        this.HoursOfPractice = data[7];
    }
    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub

        dest.writeStringArray(new String[]{this.Name,this.Surname, this.BrithDate, this.Subject, this.Professor, this.AcademicYear, this.HoursOfLectures, this.HoursOfPractice});
    }

    public static final Parcelable.Creator<Student> CREATOR= new Parcelable.Creator<Student>() {

        @Override
        public Student createFromParcel(Parcel source) {
        // TODO Auto-generated method stub
            return new Student(source);  //using parcelable constructor
        }

        @Override
        public Student[] newArray(int size) {
            // TODO Auto-generated method stub
            return new Student[size];
        }
    };
}

