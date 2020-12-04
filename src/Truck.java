public class Truck implements Transport {
    private String name;
    private float fuelVolume = 60f;
    private float fuelNow = fuelVolume;
    private float fuelConsumption = 15;

    public Truck(String name) {
        this.name = name;
    }

    @Override
    public float getFuelNow() {
        return fuelNow;
    }

    @Override
    public void setFuel(float fuelNow) {
        this.fuelNow = fuelNow;
        System.out.println("\t\t\t"+ name + ": my fuelback is full!");
    }

    @Override
    public void fuelDown() {
        fuelNow -= fuelConsumption;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getFuelMin() {
        return fuelConsumption;
    }

    @Override
    public void iAmGoing() {
        System.out.println("\t"+ name + ": I am going!");
    }

    @Override
    public float getFuelVolume() {
        return fuelVolume;
    }
}
