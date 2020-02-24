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
        return (this.id + " " +this.studentName + " " + this.major + " " + this.gpa + " " + this.age);
    }


}
