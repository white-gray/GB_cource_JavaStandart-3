public interface Transport {
    String name = null;
    float fuelVolume = 0;
    float fuel = fuelVolume;
    float fuelConsumption = 0;

    String getName();
    float getFuelNow();
    void setFuel (float fuelNow);
    void iAmGoing ();
    float getFuelMin ();
    float getFuelVolume();

    public void fuelDown();


}
