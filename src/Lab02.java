import java.util.Random;

public class Lab02 {
    public static void main(String[] args) {
        AirPurifier[] airPurifiers = new AirPurifier[10];
        String[] models = { "Default", "Mini", "Pro", "Max" };
        for (int i = 0; i < airPurifiers.length; i++) {
            airPurifiers[i] = new AirPurifier(models[new Random().nextInt(models.length)]);
        }
        for (int i = 0; i < airPurifiers.length; i++) {
            System.out.println("[" + (i+1) + "] : " + airPurifiers[i].getModel());
        }

        System.out.println("Most popular model: " + AirPurifier.getMostPopularModel());

        AirPurifier airPurifier = new AirPurifier();

        System.out.println("Serial Number: " + airPurifier.getSerialNumber());
        System.out.println("Power: " + airPurifier.getPowerStatus() + ", Fan Speed: " + airPurifier.getFanSpeed() + ", Mode: " + airPurifier.getMode());
        System.out.println("Current PM2.5: " + airPurifier.getCurrentPM2_5());

        airPurifier.turnOn();
        System.out.println("Current PM2.5: " + airPurifier.getCurrentPM2_5());
        System.out.println("Power: " + airPurifier.getPowerStatus() + ", Fan Speed: " + airPurifier.getFanSpeed() + ", Mode: " + airPurifier.getMode());
        airPurifier.turnOff();
        System.out.println("Power: " + airPurifier.getPowerStatus() + ", Fan Speed: " + airPurifier.getFanSpeed() + ", Mode: " + airPurifier.getMode());

        airPurifier.turnOn(6);
        System.out.println("Current PM2.5: " + airPurifier.getCurrentPM2_5());
        System.out.println("Power: " + airPurifier.getPowerStatus() + ", Fan Speed: " + airPurifier.getFanSpeed() + ", Mode: " + airPurifier.getMode());
        airPurifier.setFanSpeed(10);
        System.out.println("Power: " + airPurifier.getPowerStatus() + ", Fan Speed: " + airPurifier.getFanSpeed() + ", Mode: " + airPurifier.getMode());
        airPurifier.setMode(AirPurifier.Mode.AUTO);
        System.out.println("Power: " + airPurifier.getPowerStatus() + ", Fan Speed: " + airPurifier.getFanSpeed() + ", Mode: " + airPurifier.getMode());
        airPurifier.setFanSpeed(10);
        System.out.println("Power: " + airPurifier.getPowerStatus() + ", Fan Speed: " + airPurifier.getFanSpeed() + ", Mode: " + airPurifier.getMode());
        airPurifier.setMode(AirPurifier.Mode.SLEEP);
        System.out.println("Power: " + airPurifier.getPowerStatus() + ", Fan Speed: " + airPurifier.getFanSpeed() + ", Mode: " + airPurifier.getMode());
        airPurifier.setFanSpeed(10);
        System.out.println("Power: " + airPurifier.getPowerStatus() + ", Fan Speed: " + airPurifier.getFanSpeed() + ", Mode: " + airPurifier.getMode());
    }
}
