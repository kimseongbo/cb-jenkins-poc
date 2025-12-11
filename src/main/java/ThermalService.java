
public class ThermalService {

    public String classifyLevel(int tempCelsius) {
        if (tempCelsius < 0) {
            return "LOW";
        }
        if (tempCelsius <= 40) {
            return "NORMAL";
        }
        if (tempCelsius <= 55) {
            return "WARN";
        }
        return "CRITICAL";
    }

    public boolean isRapidRise(int deltaCelsius) {
        return deltaCelsius >= 10;
    }

    public boolean requiresShutdown(int tempCelsius) {
        return tempCelsius >= 70;
    }
}
