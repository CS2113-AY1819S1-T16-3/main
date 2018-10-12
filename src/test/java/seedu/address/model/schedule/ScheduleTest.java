package seedu.address.model.schedule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static seedu.address.testutil.TypicalSchedules.CLEANING;
import static seedu.address.testutil.TypicalSchedules.OFFICE;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.ScheduleBuilder;

public class ScheduleTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void equals() {
        // same values -> return true
        Schedule cleaningCopy = new ScheduleBuilder(CLEANING).build();
        assertEquals(CLEANING, cleaningCopy);

        // same object -> returns true
        assertEquals(CLEANING, CLEANING);

        // null -> returns false
        assertNotEquals(CLEANING, null);

        // different type -> returns false
        assertNotEquals(CLEANING, 5);

        // different schedule -> returns false
        assertNotEquals(CLEANING, OFFICE);

        // different time start -> returns false
        Schedule editedSchedule = new ScheduleBuilder(CLEANING).withTimeStart("2300").build();
        assertNotEquals(CLEANING, editedSchedule);

        // different time end -> returns false
        editedSchedule = new ScheduleBuilder(CLEANING).withTimeEnd("2300").build();
        assertNotEquals(CLEANING, editedSchedule);

        // different venue -> returns false
        editedSchedule = new ScheduleBuilder(CLEANING).withVenue("Pantry").build();
        assertNotEquals(CLEANING, editedSchedule);
    }
}
