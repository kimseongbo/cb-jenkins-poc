
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BatteryServiceTest {

    private final BatteryService battery = new BatteryService();

    @Test
    void testCalculatePercentage_full_0001() {
        assertEquals(100, battery.calculatePercentage(4200, 4200));
    }

    @Test
    void testCalculatePercentage_half_0002() {
        assertEquals(50, battery.calculatePercentage(2100, 4200));
    }

    @Test
    void testCalculatePercentage_zeroVoltage_0003() {
        assertEquals(0, battery.calculatePercentage(0, 4200));
    }

    @Test
    void testCalculatePercentage_overVoltage_0004() {
        assertEquals(100, battery.calculatePercentage(4500, 4200));
    }

    @Test
    void testCalculatePercentage_negativeVoltage_0005() {
        assertEquals(0, battery.calculatePercentage(-100, 4200));
    }

    @Test
    void testCalculatePercentage_smallFullVoltage_0006() {
        assertEquals(30, battery.calculatePercentage(3000, 10000));
    }

    @Test
    void testCalculatePercentage_largeFullVoltage_0007() {
        assertEquals(20, battery.calculatePercentage(1000, 5000));
    }

    @Test
    void testCalculatePercentage_zeroFullVoltage_0008() {
        assertEquals(0, battery.calculatePercentage(3000, 0));
    }

    @Test
    void testIsTemperatureSafe_minBoundary_0009() {
        assertTrue(battery.isTemperatureSafe(0));
    }

    @Test
    void testIsTemperatureSafe_maxBoundary_0010() {
        assertTrue(battery.isTemperatureSafe(45));
    }

    @Test
    void testIsTemperatureSafe_belowRange_0011() {
        assertFalse(battery.isTemperatureSafe(-1));
    }

    @Test
    void testIsTemperatureSafe_aboveRange_0012() {
        assertFalse(battery.isTemperatureSafe(46));
    }

    @Test
    void testDetermineHealth_goodLowerBoundary_0013() {
        assertEquals("GOOD", battery.determineHealth(0));
    }

    @Test
    void testDetermineHealth_goodUpperBoundary_0014() {
        assertEquals("GOOD", battery.determineHealth(299));
    }

    @Test
    void testDetermineHealth_warnLowerBoundary_0015() {
        assertEquals("WARN", battery.determineHealth(300));
    }

    @Test
    void testDetermineHealth_warnUpperBoundary_0016() {
        assertEquals("WARN", battery.determineHealth(599));
    }

    @Test
    void testDetermineHealth_badBoundary_0017() {
        assertEquals("BAD", battery.determineHealth(600));
    }

    @Test
    void testDetermineHealth_badHighCycles_0018() {
        assertEquals("BAD", battery.determineHealth(1200));
    }

    @Test
    void testCriticalTemperature_trueBoundary_0019() {
        assertTrue(battery.isCriticalTemperature(50));
    }

    @Test
    void testCriticalTemperature_falseBelowBoundary_0020() {
        assertFalse(battery.isCriticalTemperature(49));
    }
}
