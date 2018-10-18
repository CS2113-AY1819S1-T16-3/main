package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showLeaveAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalLeave.getTypicalLeaveList;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.leave.Leave;

//@@author Hafizuddin-NUS
/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteLeaveCommand}.
 */
public class DeleteLeaveCommandTest {

    private Model model = new ModelManager(getTypicalLeaveList(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Leave leaveToDelete = model.getFilteredLeaveList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(INDEX_FIRST_PERSON);
        deleteLeaveCommand.setIsLogin(false);

        String expectedMessage = String.format(DeleteLeaveCommand.MESSAGE_DELETE_LEAVE_SUCCESS, leaveToDelete);

        ModelManager expectedModel = new ModelManager(model.getLeaveList(), new UserPrefs());
        expectedModel.deleteLeave(leaveToDelete);
        expectedModel.commitLeaveList();

        assertCommandSuccess(deleteLeaveCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredLeaveList().size() + 1);
        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(outOfBoundIndex);

        assertCommandFailure(deleteLeaveCommand, model, commandHistory, Messages.MESSAGE_INVALID_LEAVE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showLeaveAtIndex(model, INDEX_FIRST_PERSON);

        Leave leaveToDelete = model.getFilteredLeaveList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(INDEX_FIRST_PERSON);
        deleteLeaveCommand.setIsLogin(false);

        String expectedMessage = String.format(DeleteLeaveCommand.MESSAGE_DELETE_LEAVE_SUCCESS, leaveToDelete);

        Model expectedModel = new ModelManager(model.getLeaveList(), new UserPrefs());
        expectedModel.deleteLeave(leaveToDelete);
        expectedModel.commitLeaveList();
        showNoLeave(expectedModel);

        assertCommandSuccess(deleteLeaveCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showLeaveAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLeaveList().getRequestList().size());

        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(outOfBoundIndex);

        assertCommandFailure(deleteLeaveCommand, model, commandHistory, Messages.MESSAGE_INVALID_LEAVE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteLeaveCommand deleteFirstCommand = new DeleteLeaveCommand(INDEX_FIRST_PERSON);
        DeleteLeaveCommand deleteSecondCommand = new DeleteLeaveCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteLeaveCommand deleteFirstCommandCopy = new DeleteLeaveCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoLeave(Model model) {
        model.updateFilteredLeaveList(p -> false);

        assertTrue(model.getFilteredLeaveList().isEmpty());
    }
}
