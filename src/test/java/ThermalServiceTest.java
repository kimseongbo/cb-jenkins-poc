
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ThermalServiceTest {

    private final ThermalService thermal = new ThermalService();

    @Test
    void testClassify_low_0001() {
        assertEquals("LOW", thermal.classifyLevel(-10));
    }

    @Test
    void testClassify_lowToNormalBoundary_0002() {
        assertEquals("LOW", thermal.classifyLevel(-1));
    }

    @Test
    void testClassify_normalLowerBoundary_0003() {
        assertEquals("NORMAL", thermal.classifyLevel(0));
    }

    @Test
    void testClassify_normalUpperBoundary_0004() {
        assertEquals("NORMAL", thermal.classifyLevel(40));
    }

    @Test
    void testClassify_warnRange_0005() {
        assertEquals("WARN", thermal.classifyLevel(50));
    }

    @Test
    void testClassify_warnUpperBoundary_0006() {
        assertEquals("WARN", thermal.classifyLevel(55));
    }

    @Test
    void testClassify_criticalRange_0007() {
        assertEquals("CRITICAL", thermal.classifyLevel(60));
    }

    @Test
    void testClassify_shouldFailAsNormal_0008() {
        assertEquals("NORMAL", thermal.classifyLevel(45));
    }

    @Test
    void testClassify_shouldFailAsWarn_0009() {
        assertEquals("WARN", thermal.classifyLevel(-5));
    }

    @Test
    void testClassify_criticalHigh_0010() {
        assertEquals("CRITICAL", thermal.classifyLevel(80));
    }

    @Test
    void testRapidRise_falseSmallDelta_0011() {
        assertFalse(thermal.isRapidRise(3));
    }

    @Test
    void testRapidRise_trueBoundary_0012() {
        assertTrue(thermal.isRapidRise(10));
    }

    @Test
    void testRapidRise_trueHighDelta_0013() {
        assertTrue(thermal.isRapidRise(20));
    }

    @Test
    void testRapidRise_shouldFailAsFalse_0014() {
        assertFalse(thermal.isRapidRise(15));
    }

    @Test
    void testRapidRise_falseZero_0015() {
        assertFalse(thermal.isRapidRise(0));
    }

    @Test
    void testShutdown_falseNormal_0016() {
        assertFalse(thermal.requiresShutdown(50));
    }

    @Test
    void testShutdown_trueBoundary_0017() {
        assertTrue(thermal.requiresShutdown(70));
    }

    @Test
    void testShutdown_trueHigh_00118() {
        assertTrue(thermal.requiresShutdown(90));
    }

    @Test
    void testShutdown_shouldFailAsFalse_0019() {
        assertFalse(thermal.requiresShutdown(80));
    }

    @Test
    void testShutdown_shouldFailAsTrue_0020() {
        assertTrue(thermal.requiresShutdown(60));
    }
}
