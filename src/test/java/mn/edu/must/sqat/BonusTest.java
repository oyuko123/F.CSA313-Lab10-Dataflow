
package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
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


    @Test
    @DisplayName("DP1: Attendance null branch")
    void bonus_du_attendanceNull() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DataFlowTargets.bonus(null, 0)
        );
    }

    @Test
    @DisplayName("DP2: Negative threshold branch")
    void bonus_du_negativeThreshold() {
        assertThrows(
            IllegalArgumentException.class,
            () -> DataFlowTargets.bonus(new int[]{}, -1)
        );
    }

    @Test
    @DisplayName("DP3: Empty attendance and zero score")
    void bonus_du_emptyAttendanceZeroScore() {
        assertEquals(0, DataFlowTargets.bonus(new int[]{}, 1));
    }

    @Test
    @DisplayName("DP4: Empty attendance and threshold zero")
    void bonus_du_emptyAttendanceThresholdZero() {
        assertEquals(5, DataFlowTargets.bonus(new int[]{}, 0));
    }

    @Test
    @DisplayName("DP5: First positive attendance")
    void bonus_du_firstPositiveAttendance() {
        assertEquals(0, DataFlowTargets.bonus(new int[]{1}, 2));
    }

    @Test
    @DisplayName("DP6: Consecutive positive attendance")
    void bonus_du_consecutiveAttendance() {
        assertEquals(5, DataFlowTargets.bonus(new int[]{1, 1}, 2));
    }

    @Test
    @DisplayName("DP7: Streak reset after absence")
    void bonus_du_streakReset() {
        assertEquals(0, DataFlowTargets.bonus(new int[]{1, 1, 0, 1}, 5));
    }

    @Test
    @DisplayName("DP8: Score base definition reaches addition")
    void bonus_du_scoreBaseToAddition() {
        assertEquals(3, DataFlowTargets.bonus(
            new int[]{1, 1, 1, 1, 1}, 6
        ));
    }

    @Test
    @DisplayName("DP9: Score five definition reaches addition")
    void bonus_du_scoreFiveToAddition() {
        assertEquals(8, DataFlowTargets.bonus(
            new int[]{1, 1, 1, 1, 1}, 5
        ));
    }

}
