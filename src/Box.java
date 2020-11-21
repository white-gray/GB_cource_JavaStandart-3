import java.util.ArrayList;

public class Box <T extends Fruit>{

    private ArrayList<T> box = new ArrayList<>();

    public float getWeight(){
        float weight = 0.0f;
        for(Fruit item : box){
            weight += item.getWeight();
        }return weight;
    }

    public boolean compare(Box anotherBox) {
        if(getWeight() == anotherBox.getWeight()) return true;
        return false;
    }
    public void sprinkleFromBox(Box <T> anotherBox){
        box.addAll(anotherBox.box);
        anotherBox.box.clear();
    }


    public void addFruit (T fruit,int howMuch) {
        for (int i = 0; i < howMuch; i++) {
            box.add(fruit);
        }
    }
}

