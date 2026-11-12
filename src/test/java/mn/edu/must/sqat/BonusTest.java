
package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BonusTest {

    @Test
    @DisplayName("AD1: All-Defs - score base and streak")
    void bonus_allDefs_scoreBaseAndStreak() {
        int[] attendance = {1, 0, 1, 1, 1, 1, 1};

        assertEquals(3, DataFlowTargets.bonus(attendance, 8));
    }

    @Test
    @DisplayName("AD2: All-Defs - score five")
    void bonus_allDefs_scoreFive() {
        int[] attendance = {1};

        assertEquals(5, DataFlowTargets.bonus(attendance, 1));
    }
}
