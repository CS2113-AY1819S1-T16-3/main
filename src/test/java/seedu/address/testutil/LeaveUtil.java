package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_APPROVAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import seedu.address.logic.commands.AddLeaveCommand;
import seedu.address.model.leave.Leave;

/**
 * A utility class for Leave.
 */
public class LeaveUtil {
    /**
     * Returns an add leave command string for adding the {@code leave}.
     */
    public static String getAddLeaveCommand(Leave leave) {
        return AddLeaveCommand.COMMAND_WORD + " " + getLeaveDetails(leave);
    }

    /**
     * Returns the part of command string for the given {@code leave}'s details.
     */
    public static String getLeaveDetails(Leave leave) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NRIC + leave.getEmployeeId().nric + " ");
        sb.append(PREFIX_DATE + leave.getDate().date + " ");
        sb.append(PREFIX_APPROVAL + leave.getApproval().status + " ");
        return sb.toString();
    }

}


