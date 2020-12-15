package ru.geekbrains;


import ru.geekbrains.ClassTest;

public class Main {

    public static void main(String[] args) {
        Class go = ClassUnderTestTest.class;

        try {
            ClassTest.start(go);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ClassUnderTestTest test = new ClassUnderTestTest(ClassUnderTest);
    }}
