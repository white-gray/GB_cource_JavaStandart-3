import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static Transport car1 = new Car("car1");
    private static Transport car2 = new Car("car2");
    private static Transport car3 = new Car("car3");
    private static Transport car4 = new Car("car4");
    private static Transport track1 = new Truck("track1");
    private static Transport track2 = new Truck("track2");
    private static Transport track3 = new Truck("track3");
    private static Transport bus1 = new Bus("bus1");
    private static Transport bus2 = new Bus("bus2");
    private static Transport bus3 = new Bus("bus3");


    public static void main(String[] args) {

        List<Transport> transport = new ArrayList(10);
        transport.add(car1);
        transport.add(car2);
        transport.add(car3);
        transport.add(car4);
        transport.add(track1);
        transport.add(track2);
        transport.add(track3);
        transport.add(bus1);
        transport.add(bus2);
        transport.add(bus3);

        ExecutorService executorService = Executors.newFixedThreadPool(11);
        FuelStaion fuelStaion = new FuelStaion();


        executorService.execute(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        wasteOfFuel(transport, executorService, fuelStaion);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }});

        for (Transport q : transport) {
            executorService.execute(q::iAmGoing);
        }


    }

    private static void wasteOfFuel( List<Transport> transport, ExecutorService executorService, FuelStaion fuelStaion) throws InterruptedException {
        for (Transport q : transport) {
            q.fuelDown();
            System.out.println(q.getName() +": have " +q.getFuelNow() + " fuel now");
            if (q.getFuelNow() < q.getFuelMin()){
                fuelStaion.fullFuel(q.getName(), q.getFuelVolume(), q.getFuelMin());
                 q.setFuel(q.getFuelVolume());
            }
            if (q.getFuelNow() < 0) {
                stop(executorService);
            }
        }
    }

    public static void stop(ExecutorService executorService) {
       executorService.shutdown();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tThe end!");
        System.exit(0);

    }
}
