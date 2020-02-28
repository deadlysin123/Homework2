package sample;

import java.util.UUID;

public class Student {
    public String studentName;
    public String major;
    public String gpa;
    public String age;
    public int id;

    @Override
    public String toString(){
        return ("ID: " +this.id + " | NAME: " +this.studentName + " | MAJOR: " + this.major + " | GPA: " + this.gpa + " | AGE: " + this.age);

    }


}
