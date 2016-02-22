package com.javarush.test.level29.lesson15.big01.human;

import java.util.List;

public class University {
    private List<Student> students;
    private String name;
    private int age;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public List<Student> getStudents()
    {

        return students;
    }

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }


    public University(String name, int age){
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double db) {
        Student st = null;

        for (Student student : students){
          if(student.getAverageGrade() == db)  {
              st = student;
          }
        }
        return st;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student st = null;
        double max = 0;
        for (Student student : students){
            double t = student.getAverageGrade();
            if(t > max)  {
                st = student;
                max = t;
            }
        }
        return st;
    }
public Student getStudentWithMinAverageGrade(){
    double min = 100;
    Student st = null;

    for (Student stud : students){
        double k = stud.getAverageGrade();
        if (k < min){
            st = stud;
            min = k;
        }
    }
    return  st;
}
   public void expel(Student student){
        students.remove(student);
    }

}
