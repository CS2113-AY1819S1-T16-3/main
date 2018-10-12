package seedu.address.model.schedule;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class TimeEndTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new TimeEnd(null));
    }

    @Test
    public void constructor_invalidTimeEnd_throwsIllegalArgumentException() {
        String invalidTimeEnd = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new TimeEnd(invalidTimeEnd));
    }

    @Test
    public void isValidTimeEnd() {
        // null end time
        Assert.assertThrows(NullPointerException.class, () -> TimeEnd.isValidTimeEnd(null));

        // invalid end time
        assertFalse(TimeEnd.isValidTimeEnd("")); // empty string
        assertFalse(TimeEnd.isValidTimeEnd(" ")); // spaces only
        assertFalse(TimeEnd.isValidTimeEnd("eight")); // alphabet
        assertFalse(TimeEnd.isValidTimeEnd("-@")); // non-numeric characters
        assertFalse(TimeEnd.isValidTimeEnd("15000")); // non 4 digit time
        assertFalse(TimeEnd.isValidTimeEnd("-0100")); // negative
        assertFalse(TimeEnd.isValidTimeEnd("2500")); // non 4 digit time
        assertFalse(TimeEnd.isValidTimeEnd("1570")); // non 4 digit time

        // valid end time
        assertTrue(TimeEnd.isValidTimeEnd("1500")); // long address
    }
}
