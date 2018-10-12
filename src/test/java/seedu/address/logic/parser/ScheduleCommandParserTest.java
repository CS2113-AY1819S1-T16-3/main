package seedu.address.logic.parser;

import org.junit.Test;

import seedu.address.model.schedule.Schedule;
import seedu.address.testutil.ScheduleBuilder;

public class ScheduleCommandParserTest {
    private ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Schedule expectedSchedule = new ScheduleBuilder().build();
    }
}
