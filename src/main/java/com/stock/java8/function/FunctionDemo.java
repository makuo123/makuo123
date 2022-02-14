package com.stock.java8.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionDemo {

    public static Student updateStudent(Student student,
                                        Predicate<Student> predicate,
                                        Consumer<Student> consumer) {

        // predicate.test(student) 满足条件，consumer.accept(student) 修改对象的属性值
        if (predicate.test(student)) {
            consumer.accept(student);
        }
        return student;
    }

    public static boolean save(Student student) {
        return false;
    }

    public static void main(String[] args) {
        /*Student jack = new Student("jack", 50, 0.1);
        jack = updateStudent(jack, student -> student.score > 40, student -> student.diccount = 2);
        System.out.println(jack);

        Student mark = new Student("mark", 30, 0.1);
        mark = updateStudent(mark, student -> student.score > 40, student -> student.diccount = 2);
        System.out.println(mark);

        Consumer<Student> save = FunctionDemo::save;*/

        Function<Integer, Integer> name = e -> e * 2;
        Function<Integer, Integer> square = e -> e * e;
        int value = name.andThen(square).apply(3);
        System.out.println("andThen value=" + value);
        int value2 = name.compose(square).apply(3);
        System.out.println("compose value2=" + value2);
        //返回一个执行了apply()方法之后只会返回输入参数的函数对象
        Object identity = Function.identity().apply("huohuo");
        System.out.println(identity);

    }
}
