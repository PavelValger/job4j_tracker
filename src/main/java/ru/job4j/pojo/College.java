package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setFio("Valger Pavel Ivanovich");
        student1.setGroup("Java 2021");
        student1.setEntrance(new Date());
        System.out.println("Student " + student1.getFio() + " enrolled in the group "
                + student1.getGroup() + " in " + student1.getEntrance());
    }
}
