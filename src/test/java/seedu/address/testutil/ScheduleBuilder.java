package seedu.address.testutil;

import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.TimeEnd;
import seedu.address.model.schedule.TimeStart;
import seedu.address.model.schedule.Venue;

/**
 * A utility class to help with building Person objects.
 */
public class ScheduleBuilder {

    public static final String DEFAULT_TIME_START = "1100";
    public static final String DEFAULT_TIME_END = "1500";
    public static final String DEFAULT_VENUE = "Level2";

    private TimeStart timeStart;
    private TimeEnd timeEnd;
    private Venue venue;

    public ScheduleBuilder() {
        timeStart = new TimeStart(DEFAULT_TIME_START);
        timeEnd = new TimeEnd(DEFAULT_TIME_END);
        venue = new Venue(DEFAULT_VENUE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public ScheduleBuilder(Schedule scheduleToCopy) {
        timeStart = scheduleToCopy.getTimeStart();
        timeEnd = scheduleToCopy.getTimeEnd();
        venue = scheduleToCopy.getVenue();
    }

    /**
     * Sets the {@code TimeStart} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withTimeStart(String timeStart) {
        this.timeStart = new TimeStart(timeStart);
        return this;
    }

    /**
     * Sets the {@code TimeEnd} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withTimeEnd(String timeEnd) {
        this.timeEnd = new TimeEnd(timeEnd);
        return this;
    }

    /**
     * Sets the {@code Venue} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withVenue(String venue) {
        this.venue = new Venue(venue);
        return this;
    }


    public Schedule build() {
        return new Schedule(timeStart, timeEnd, venue);
    }
}
