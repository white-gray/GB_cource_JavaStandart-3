package ru.geekbrains;


public class ClassUnderTestTest {
    private static ClassUnderTest classUnderTest;

    @BeforeSuite
    public static void init(){
        classUnderTest = new ClassUnderTest();
        System.out.println("Tests start");
    }

    @Test
    @Order(1)
    public void testShow1 (){
        System.out.println("test1 " + Assertions.assertEquals (1, classUnderTest.it1()));
    }

    @Test
    @Order(1)
    public void testShow2 (){
        System.out.println("test2 " + Assertions.assertEquals (1, classUnderTest.it1()));

    }

    @Test
    @Order(3)
    public void testShow3 (){
        System.out.println("test3 " + Assertions.assertEquals (1, classUnderTest.it1()));
    }

    @Test
    @Order(5)
    public void testShow4 (){
        System.out.println("test4 " + Assertions.assertEquals (1, classUnderTest.it1()));
    }

    @Test
    @Order(4)
    public void testShow5 (){
        System.out.println("test5 " + Assertions.assertEquals (1, classUnderTest.it1()));
    }

    @Test
    @Order(2)
    public void testShow6 (){
        System.out.println("test6 " + Assertions.assertEquals (1, classUnderTest.it1()));
   }

    @Test
    @Order(7)
    public void testShow7 (){
        System.out.println("test7 " + Assertions.assertEquals (1, classUnderTest.it1()));
    }

    @Test
    @Order(9)
    public void testShow8 (){
        System.out.println("test8 " + Assertions.assertEquals (1, classUnderTest.it1()));
   }

    @Test
    @Order(10)
    public void testShow9 (){
        System.out.println("test9 " + Assertions.assertEquals (1, classUnderTest.it1()));
    }

    @Test
    @Order(6)
    public void testShow10 (){
        System.out.println("test10 " + Assertions.assertEquals (1, classUnderTest.it1()));
    }

    @Test
    @Order(5)
    public void testShow11 (){
        System.out.println("test11 " + Assertions.assertEquals (1, classUnderTest.it1()));
    }

    @Test
    @Order(1)
    public void testShow12 (){
        System.out.println("test12 " + Assertions.assertEquals (1, classUnderTest.it1()));
    }

    @AfterSuite
    public static void end () {
        System.out.println("Tests finished");
    }
}
