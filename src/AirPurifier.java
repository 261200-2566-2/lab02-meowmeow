import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

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
    private int topFanSpeed = 10;
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

    public AirPurifier(String model, int topFanSpeed) {
        this(model);
        this.topFanSpeed = topFanSpeed;
    }

    public void turnOn() {
        if (filterLife <= 0) {
            System.out.println("Filter needs to be replaced.");
            power = false;
        }
        setFanSpeed(topFanSpeed / 2);
        power = true;
        filterLife--;
    }

    public void turnOn(int fanSpeed) {
        if (filterLife <= 0) {
            System.out.println("Filter needs to be replaced.");
            power = false;
        }
        power = true;
        setFanSpeed(fanSpeed);
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
                setFanSpeed(topFanSpeed);
            } else if (currentPM2_5 > 25) {
                setFanSpeed(topFanSpeed / 2);
            } else {
                setFanSpeed(topFanSpeed / 4);
            }
        } else if (mode == Mode.SLEEP) {
            if (currentPM2_5 > 50) {
                setFanSpeed(topFanSpeed / 2);
            } else if (currentPM2_5 > 25) {
                setFanSpeed(topFanSpeed / 4);
            } else {
                setFanSpeed(topFanSpeed / 8);
            }
        } else {
            if (fanSpeed > topFanSpeed) {
                this.fanSpeed = topFanSpeed;
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
}