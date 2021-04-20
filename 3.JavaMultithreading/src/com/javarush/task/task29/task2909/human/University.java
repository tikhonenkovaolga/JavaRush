package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public University(String name, int age) {
        this.name = name;
        this.age = age;

    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        Student student = null;
        for (Student s:students) {
          if (s.getAverageGrade() == averageGrade){
               student = s;
          }
        }
        return student;
    }

    public Student getStudentWithMaxAverageGrade() {
        double maxAverGrade = students.get(0).getAverageGrade();
        Student student = students.get(0);
        for (int i = 1; i < students.size(); i++){
            if (students.get(i).getAverageGrade() > maxAverGrade){
                maxAverGrade = students.get(i).getAverageGrade();
                student = students.get(i);
            }
        }
        return student;

    }
   public Student getStudentWithMinAverageGrade(){
        double minAverGrade = students.get(0).getAverageGrade();
        Student student = students.get(0);
        for (int i = 1; i < students.size(); i++){
            if (students.get(i).getAverageGrade() < minAverGrade){
                minAverGrade = students.get(i).getAverageGrade();
                student = students.get(i);
            }
        }
        return student;
    }
    public void expel(Student student){
        students.remove(student);

    }

}