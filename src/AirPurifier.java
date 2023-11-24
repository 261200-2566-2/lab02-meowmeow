import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AirPurifier {
    public static List<String> models = new ArrayList<String>();
    public static List<Integer> modelCounts = new ArrayList<Integer>();

    public static void createNewAirPurifier(String model) {
        if (AirPurifier.models.contains(model)) {
            int modelIndex = AirPurifier.models.indexOf(model);
            AirPurifier.modelCounts.set(modelIndex, AirPurifier.modelCounts.get(modelIndex) + 1);
        } else {
            AirPurifier.models.add(model);
            AirPurifier.modelCounts.add(1);
        }
    }

    public static String getMostPopularModel() {
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < AirPurifier.modelCounts.size(); i++) {
            if (AirPurifier.modelCounts.get(i) > max) {
                max = AirPurifier.modelCounts.get(i);
                maxIndex = i;
            }
        }
        return AirPurifier.models.get(maxIndex);
    }

    enum Mode {
        AUTO,
        SLEEP,
        MANUAL
    }

    private String model;
    private String serialNumber;
    private boolean power;
    private int fanSpeed;
    private int maxFanSpeed = 10;
    private int filterLife = 100;
    private int currentPM2_5 = 0;
    private Mode mode = Mode.MANUAL;

    public AirPurifier() {
        this("Default");
    }

    public AirPurifier(String model) {
        this.model = model;
        serialNumber = (UUID.randomUUID().toString()).split("-")[0].toUpperCase() + model.toUpperCase();
        AirPurifier.createNewAirPurifier(model);
    }

    public AirPurifier(String model, int maxFanSpeed) {
        this(model);
        this.maxFanSpeed = maxFanSpeed;
    }

    public void turnOn() {
        if (filterLife <= 0) {
            System.out.println("Filter needs to be replaced.");
            power = false;
        }
        currentPM2_5 = new Random().nextInt(60);
        power = true;
        setFanSpeed(maxFanSpeed / 2);
        filterLife--;
    }

    public void turnOn(int fanSpeed) {
        if (filterLife <= 0) {
            System.out.println("Filter needs to be replaced.");
            power = false;
        }
        currentPM2_5 = new Random().nextInt(60);
        power = true;
        setFanSpeed(fanSpeed);
        filterLife--;
    }

    public void turnOff() {
        power = false;
    }

    public void setFanSpeed(int fanSpeed) {
        if (power == false) {
            System.out.println("Air purifier is off.");
            return;
        }
        if (mode == Mode.AUTO) {
            if (currentPM2_5 > 50) {
                this.fanSpeed = maxFanSpeed;
            } else if (currentPM2_5 > 25) {
                this.fanSpeed = maxFanSpeed / 2;
            } else {
                this.fanSpeed = maxFanSpeed / 4;
                System.out.println(fanSpeed);
            }
        } else if (mode == Mode.SLEEP) {
            if (currentPM2_5 > 50) {
                this.fanSpeed = maxFanSpeed / 2;
            } else if (currentPM2_5 > 25) {
                this.fanSpeed = maxFanSpeed / 4;
            } else {
                this.fanSpeed = maxFanSpeed / 8;
            }
        } else if (mode == Mode.MANUAL) {
            if (fanSpeed > maxFanSpeed) {
                this.fanSpeed = maxFanSpeed;
            } else if (fanSpeed < 0) {
                this.fanSpeed = 0;
            } else {
                this.fanSpeed = fanSpeed;
            }
        }
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        setFanSpeed(fanSpeed);
    }

    public String getModel() {
        return model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public boolean getPowerStatus() {
        return power;
    }

    public int getFanSpeed() {
        return fanSpeed;
    }

    public Mode getMode() {
        return mode;
    }
    
    public int getFilterLife() {
        return filterLife;
    }

    public int getCurrentPM2_5() {
        return currentPM2_5;
    }
}