package seedu.address.model.schedule;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class VenueTest {
    @Test

    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Venue(null));
    }

    @Test
    public void constructor_invalidVenue_throwsIllegalArgumentException() {
        String invalidVenue = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Venue(invalidVenue));
    }

    @Test
    public void isValidVenue() {
        // null venue
        Assert.assertThrows(NullPointerException.class, () -> Venue.isValidVenue(null));

        // invalid venue
        assertFalse(Venue.isValidVenue("")); // empty string
        assertFalse(Venue.isValidVenue(" ")); // spaces only

        // valid venue
        assertTrue(Venue.isValidVenue("Level 2"));
        assertTrue(Venue.isValidVenue("-")); // one character
        assertTrue(Venue.isValidVenue("YIH")); // long venue
    }
}
