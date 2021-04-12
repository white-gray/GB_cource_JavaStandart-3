import java.sql.SQLOutput;
import java.util.List;

public class Main {

    public static void main(String[] args) {

/**
 *  Task 1
 */
        System.out.println("\n--------------- Task 1 --------------\n");
	doArray <Object[]> array  = new doArray(1,2,3,"4","5","six","sevem",8,"nine");
        array.showArray();
	array.changeAttay(0,8);
        array.showArray();
/**
 *  Task 2
 */
        System.out.println("\n\n--------------- Task 2 --------------\n");
        List<Object> arrayList;
        arrayList = array.toArrayList();
        System.out.println("\nnow we have ArrayList from array:");
        for (Object str : arrayList)
        System.out.print(" " + str);
/**
 *  Task 3
 */
        System.out.println("\n\n\n--------------- Task 3 --------------\n");
        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Apple> appleBox3 = new Box<>();
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();
        Box<Orange> orangeBox3 = new Box<>();
;
        appleBox1.addFruit(new Apple(),8);
        appleBox2.addFruit(new Apple(),3);
        appleBox3.addFruit(new Apple(),15);
        orangeBox1.addFruit(new Orange(),7);
        orangeBox2.addFruit(new Orange(),2);
        orangeBox3.addFruit(new Orange(),10);

        System.out.println("\nWeight of boxes:");
        System.out.println("\tappleBox1 is : "+appleBox1.getWeight());
        System.out.println("\tappleBox2 is : "+appleBox2.getWeight());
        System.out.println("\tappleBox3 is : "+appleBox3.getWeight());
        System.out.println("\torangeBox1 is : "+orangeBox1.getWeight());
        System.out.println("\torangeBox2 is : "+orangeBox2.getWeight());
        System.out.println("\torangeBox3 is : "+orangeBox3.getWeight());

        System.out.println("\nComparison of boxes weight:");
        System.out.println("\tappleBox1 and appleBox2: "+appleBox1.compare(appleBox2));
        System.out.println("\torangeBox2 and orangeBox1: "+orangeBox2.compare(orangeBox1));
        System.out.println("\torangeBox2 and appleBox2: "+orangeBox2.compare(appleBox2));

        System.out.println("\nSprinkle from box to box:");
        System.out.println("\tfrom orangeBox2 to orangeBox1");
        orangeBox1.sprinkleFromBox(orangeBox2);
        System.out.println("\tfrom appleBox1 to appleBox2");
        appleBox1.sprinkleFromBox(appleBox2);
  /** // java: incompatible types: Box<Apple> cannot be converted to Box<Orange>
   *         System.out.println("\tfrom orangeBox2 to appleBox2");
   *         orangeBox2.sprinkleFromBox(appleBox2)
   */

        System.out.println("\nAnd now weight of boxes:");
        System.out.println("\tappleBox1 is : "+appleBox1.getWeight());
        System.out.println("\tappleBox2 is : "+appleBox2.getWeight());
        System.out.println("\tappleBox3 is : "+appleBox3.getWeight());
        System.out.println("\torangeBox1 is : "+orangeBox1.getWeight());
        System.out.println("\torangeBox2 is : "+orangeBox2.getWeight());
        System.out.println("\torangeBox3 is : "+orangeBox3.getWeight());
    }
}
