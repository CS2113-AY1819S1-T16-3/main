package seedu.address.model.schedule;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class TimeStartTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new TimeStart(null));
    }

    @Test
    public void constructor_invalidTimeStart_throwsIllegalArgumentException() {
        String invalidTimeStart = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new TimeStart(invalidTimeStart));
    }

    @Test
    public void isValidTimeStart() {
        // null start time
        Assert.assertThrows(NullPointerException.class, () -> TimeStart.isValidTimeStart(null));

        // invalid start time
        assertFalse(TimeStart.isValidTimeStart("")); // empty string
        assertFalse(TimeStart.isValidTimeStart(" ")); // spaces only
        assertFalse(TimeStart.isValidTimeStart("eight")); // alphabet
        assertFalse(TimeStart.isValidTimeStart("-@")); // non-numeric characters
        assertFalse(TimeStart.isValidTimeStart("15000")); // non 4 digit time
        assertFalse(TimeStart.isValidTimeStart("-0100")); // negative
        assertFalse(TimeStart.isValidTimeStart("2500")); // non 4 digit time
        assertFalse(TimeStart.isValidTimeStart("1570")); // non 4 digit time

        // valid start time
        assertTrue(TimeStart.isValidTimeStart("1500")); // long address
    }
}
