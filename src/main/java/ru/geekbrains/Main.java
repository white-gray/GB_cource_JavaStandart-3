package ru.geekbrains;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {
//----------start task2 --------------
        int [] massWith4 = new int[]{12,2,3,14,5,6,7,8,9};
        System.out.print("For massive ");
        for (int q : massWith4) {
            System.out.print(q + " ");
        }
        System.out.print("from last 4 we have:");
        for (int a : cutMassive (massWith4)) {
            System.out.print(" " + a);
        }
//----------start task3 --------------
        System.out.println();
        System.out.println("\t and this massive " + checkMassiveFor1_4(massWith4) + " have 1 or(and) 4");

    }

/**  task 2
 *
 */
    public static int [] cutMassive(int[] massiveIn) {
        try {
            if (massiveIn.length < 1 || !checkForFour(massiveIn)) {
                throw new RuntimeException("\n\nThis array is wrong!\n\n");
            }
        }
        catch (RuntimeException e) {
            e.printStackTrace();
//            System.exit(1);
        }
        int [] resultArray = null;
        for (int q=0; q<massiveIn.length; q++){
            if (massiveIn[q] ==4) {
                resultArray = massiveAfter4(massiveIn,q);
            }
        }
        return resultArray;
    }

    private static int[] massiveAfter4(int[] massiveIn, int q) {
        int [] resultArray = new int[massiveIn.length-q-1];
        for (int w=q+1; w<massiveIn.length;w++) {
            if (massiveIn[w]==4) {
                massiveAfter4(massiveIn,w);
            }
            resultArray[w-q-1] = massiveIn [w];
        }
        return resultArray;
    }

    private static boolean checkForFour(int[] massiveIn) {
        for (int q : massiveIn) {
            if (q == 4) {return true;}
        }
        return false;
    }

/**  task 3
 *
 */
public static boolean checkMassiveFor1_4 (int[] massiveIn) {
    for (int q : massiveIn){
        if (q ==4 || q ==4) {
            return true;
        }
    }
    return false;
}

}
