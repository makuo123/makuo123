package com.stock.java8.function;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class FunctionDemo {

    public static Student updateStudent(Student student,
                                        Predicate<Student> predicate,
                                        Consumer<Student> consumer){

        // predicate.test(student) 满足条件，consumer.accept(student) 修改对象的属性值
        if (predicate.test(student)){
            consumer.accept(student);
        }
        return student;
    }

    public static void main(String[] args) {
        Student jack = new Student("jack", 50, 0.1);
        jack = updateStudent(jack, student -> student.score > 40, student -> student.diccount = 2);
        System.out.println(jack);

        Student mark = new Student("mark", 30, 0.1);
        mark = updateStudent(mark, student -> student.score > 40, student -> student.diccount = 2);
        System.out.println(mark);
    }
}
