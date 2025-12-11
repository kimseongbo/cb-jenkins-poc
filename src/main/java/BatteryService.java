
public class BatteryService {

    public int calculatePercentage(int voltage, int fullVoltage) {
        if (fullVoltage <= 0) return 0;
        int percent = (voltage * 100) / fullVoltage;
        return Math.min(Math.max(percent, 0), 100);
    }

    public boolean isTemperatureSafe(int tempCelsius) {
        return tempCelsius >= 0 && tempCelsius <= 45;
    }

    public String determineHealth(int chargeCycles) {
        if (chargeCycles < 300) {
            return "GOOD";
        }
        if (chargeCycles < 600) {
            return "WARN";
        }
        return "BAD";
    }

    public boolean isCriticalTemperature(int tempCelsius) {
        return tempCelsius >= 50;
    }
}
