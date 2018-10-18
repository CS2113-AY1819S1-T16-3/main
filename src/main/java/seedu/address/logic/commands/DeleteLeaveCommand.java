package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.SessionManager;
import seedu.address.model.leave.Leave;
import seedu.address.model.prioritylevel.PriorityLevelEnum;

/**
 * Deletes a leave identified using it's displayed index from the leave book.
 */
public class DeleteLeaveCommand extends Command {

    public static final String COMMAND_WORD = "deleteleave";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the leave identified by the index number used in the displayed leave list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_LEAVE_SUCCESS = "Deleted Leave: %1$s";
    public static final String MESSAGE_INVALID_LEAVE_DELETE = "Not authorized to delete other users' leave application";
    private static boolean isLogin = true;

    private final Index targetIndex;

    public DeleteLeaveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Leave> lastShownList = model.getFilteredLeaveList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LEAVE_DISPLAYED_INDEX);
        }

        Leave leaveToDelete = lastShownList.get(targetIndex.getZeroBased());


        if (isLogin && leaveToDelete.getEmployeeId().nric != SessionManager.getLoggedInSessionNric().nric
                && SessionManager.hasSufficientPriorityLevelForThisSession(PriorityLevelEnum.MANAGER)) {
            throw new CommandException(MESSAGE_INVALID_LEAVE_DELETE);
        }

        model.deleteLeave(leaveToDelete);
        model.commitLeaveList();
        return new CommandResult(String.format(MESSAGE_DELETE_LEAVE_SUCCESS, leaveToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteLeaveCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteLeaveCommand) other).targetIndex)); // state check
    }
}
