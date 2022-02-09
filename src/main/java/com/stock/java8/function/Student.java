package com.stock.java8.function;

public class Student {

     public String name;
     public double score;
     public double diccount;

    public Student() {
    }

    public Student(String name, double score, double diccount) {
        this.name = name;
        this.score = score;
        this.diccount = diccount;
    }



    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", diccount=" + diccount +
                '}';
    }
}
