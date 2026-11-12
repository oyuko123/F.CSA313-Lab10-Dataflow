
package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GcdTest {

    @Test
    @DisplayName("T1: G2, G6")
    void gcd_7_0_zeroIterations() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("T2: G1, G3-G5, G7-G13")
    void gcd_12_8_twoIterations() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }
}
