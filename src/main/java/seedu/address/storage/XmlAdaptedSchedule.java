package seedu.address.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.TimeEnd;
import seedu.address.model.schedule.TimeStart;
import seedu.address.model.schedule.Venue;

/**
 * JAXB-friendly version of the Schedule.
 */
public class XmlAdaptedSchedule {

    @XmlElement
    private String venue;
    @XmlElement
    private String startTime;
    @XmlElement
    private String endTime;

    /**
     * Constructs an XmlAdaptedTag.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedSchedule() {}

    /**
     * Constructs a {@code XmlAdaptedSchedule} with the given {@code schedule}.
     */
    public XmlAdaptedSchedule(String startTime, String endTime, String venue) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
    }

    /**
     * Converts a given Schedule into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created
     */
    public XmlAdaptedSchedule(Schedule source) {
        venue = source.getVenue().value;
        endTime = source.getTimeEnd().value;
        startTime = source.getTimeStart().value;
    }

    /**
     * Converts this jaxb-friendly adapted tag object into the model's Tag object.
     *
     */
    public Schedule toModelType() {
        TimeStart timeStart = new TimeStart(this.startTime);
        TimeEnd timeEnd = new TimeEnd(this.endTime);
        Venue venue = new Venue(this.venue);

        return new Schedule(timeStart, timeEnd, venue);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedSchedule)) {
            return false;
        }

        return venue.equals(((XmlAdaptedSchedule) other).venue);
    }
}
