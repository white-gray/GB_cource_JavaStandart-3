import java.util.concurrent.Semaphore;

public class FuelStaion extends GasPool {
    private float fuelVolumeBack = 200f;
    private float fuelNowBack = fuelVolumeBack;

    Semaphore smp = new Semaphore(3);

    public void vorequest(float amount) {
        fuelNowBack +=amount;
    }

    public String info() {
        return "\t\tIn GasPool now is " + fuelNowBack;
    }


//    public float fullFuel (String name, float fuelVolume, float fuelNow) throws InterruptedException {
//                System.out.println("\t"+ name + " get in line for a gas station");
//                smp.acquire();
//                System.out.println("\t"+  name + " is refuiling");
//                fuelNowBack = fuelNowBack - (fuelVolume - fuelNow);
//                System.out.println("\t\tAt FuelStation left " + fuelNowBack);
//                if (fuelNowBack < 2.5f) {
//                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tThe end!");
//                    System.exit(0);}
//                Thread.sleep(5000);
//                System.out.println("\t"+ name + " refueled and go out");
//                smp.release();
//                return fuelVolume;
//    }




    public void fullFuel (String name, float fuelVolume, float fuelNow) {
                    new Thread(() -> {
                        try {
                            System.out.println("\t"+ name + " get in line for a gas station");
                            smp.acquire();
                            System.out.println("\t"+  name + " is refuiling");
                            fuelNowBack = fuelNowBack - (fuelVolume - fuelNow);
                            System.out.println("\t\tAt FuelStation left " + fuelNowBack);
                            if (fuelNowBack < 2.5f) {
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tThe end!");
                            System.exit(0);}
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            System.out.println("\t"+ name + " refueled and go out");
                            smp.release();
                        }
                    }).start();
                }


}
