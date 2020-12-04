public class GasPool {
    float fuelVolumeBack = 200;
    float fuelNowBack = fuelVolumeBack;

    public void vorequest(float amount) {
        fuelNowBack +=amount;
    }

    public String info() {
        return "\t\tIn GasPool now is " + fuelNowBack;
    }
}
