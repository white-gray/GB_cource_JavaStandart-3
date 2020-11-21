import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class doArray <T> {
    private T[] array;

    public doArray(T... array){
        this.array = array;
    }


    public T[] changeAttay(int dot1, int dot2) {
        Object q;
        q = array[dot1];
        array[dot1] = array[dot2];
        array[dot2] = (T) q;
        return  array;
    }

    public ArrayList toArrayList () {
                List<Object> arrayList ;
                arrayList = new ArrayList<>();
                Collections.addAll(arrayList, array);
            return (ArrayList) arrayList;
    }


    public void showArray () {
            for (int q=0; q < array.length; q++) {
                System.out.print(array[q]+"\t");
            }
        System.out.println("\n__________________________________________________________________\n");
    }
}
