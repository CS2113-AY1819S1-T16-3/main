package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.commands.SetScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.Schedule;

/**
 * Parses input arguments and creates a new SetScheduleCommand object
 */
public class SetScheduleCommandParser implements Parser<SetScheduleCommand> {

    /**
     * Parses the given {@code String args} of arguments in the context of the SetScheduleCommand
     * and returns an SetScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TIME_START,
                PREFIX_TIME_END, PREFIX_VENUE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetScheduleCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_TIME_START).isPresent()
                && argMultimap.getValue(PREFIX_TIME_END).isPresent()
                && argMultimap.getValue(PREFIX_VENUE).isPresent()) {
            Schedule schedule = ParserUtil.parseSchedule(
                    argMultimap.getValue(PREFIX_TIME_START).get(),
                    argMultimap.getValue(PREFIX_TIME_END).get(),
                    argMultimap.getValue(PREFIX_VENUE).get());

            Set<Schedule> scheduleList = new HashSet<>();
            scheduleList.add(schedule);

            editPersonDescriptor.setSchedule(scheduleList);
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(ScheduleCommand.MESSAGE_SCHEDULE_FAIL);
        }

        return new SetScheduleCommand(index, editPersonDescriptor);
    }
}
