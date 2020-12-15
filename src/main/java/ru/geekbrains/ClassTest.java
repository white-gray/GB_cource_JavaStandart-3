package ru.geekbrains;


import java.lang.reflect.Method;
import java.util.ArrayList;

public class ClassTest {
    int methodAfterSuite;
    int methodBeforeSuite;

    public static void start(Class obj) throws Exception {
        ClassTest ct = new ClassTest();
        ArrayList<Method> testsArr = new ArrayList<>();


        Method[] methods = obj.getDeclaredMethods();
        for (Method met: methods) {
            if(met.isAnnotationPresent(BeforeSuite.class)){
                met.invoke(null );
                ct.methodBeforeSuite++;
            }
            if(met.isAnnotationPresent(AfterSuite.class)){
                ct.methodAfterSuite++;
            }
            if (met.isAnnotationPresent(Test.class)) {
                testsArr.add(met);
            }
        }
        if ((ct.methodAfterSuite | ct.methodBeforeSuite) > 1) throw new RuntimeException();

        testsArr.sort((o2, o1) -> {return o1.getAnnotation(Order.class).value() - o2.getAnnotation(Order.class).value();});


        for (int i = testsArr.size() - 1; i >= 0; i--) {
            System.out.print("Приоритет: " + testsArr.get(i).getAnnotation(Order.class).value() + " Тест: ");
            System.out.print(testsArr.get(i).getName()+"\n");

        }
        for (Method m : methods) {
            if (m.isAnnotationPresent(AfterSuite.class)) {
                m.invoke(null);
            }
        }
    }

    }
