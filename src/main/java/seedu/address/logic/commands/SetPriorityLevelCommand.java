package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITYLEVEL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.SessionManager;
import seedu.address.model.person.Person;
import seedu.address.model.prioritylevel.PriorityLevel;
import seedu.address.model.prioritylevel.PriorityLevelEnum;

/**
 * This command sets the priority level of other users.
 */
public class SetPriorityLevelCommand extends Command {

    public static final String COMMAND_WORD = "setplvl";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the priority level of the person identified "
            + " by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_PRIORITYLEVEL + "PRIORITYLEVEL\n"
            + "Example: " + COMMAND_WORD + " 2 " + PREFIX_PRIORITYLEVEL + "3";

    private static final String MESSAGE_CANNOT_EDIT_OWN_PLVL = "You can't edit your own priority level.";
    private static final String MESSAGE_CHANGE_PLVL_SUCCESS = "Successfully changed the priority level of %s to %s";

    private final Index index;
    private final PriorityLevel priorityLevel;

    public SetPriorityLevelCommand (Index index, PriorityLevel priorityLevel) {
        requireAllNonNull (index, priorityLevel);
        this.index = index;
        this.priorityLevel = priorityLevel;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!SessionManager.isLoggedIn()) {
            throw new CommandException(SessionManager.NOT_LOGGED_IN);
        }
        /**
         * Throws exception if user does not have the required access level.
         */
        if (!SessionManager.hasSufficientPriorityLevelForThisSession(PriorityLevelEnum.ADMINISTRATOR)) {
            throw new CommandException(String.format(PriorityLevel.INSUFFICIENT_PRIORITY_LEVEL,
                    PriorityLevelEnum.ADMINISTRATOR));
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (personToEdit == SessionManager.getLoggedInPersonDetails(model)) {
            throw new CommandException(MESSAGE_CANNOT_EDIT_OWN_PLVL);
        }

        Person editedPerson = new Person(
                personToEdit.getName(),
                personToEdit.getNric(),
                personToEdit.getPassword(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getDepartment(),
                priorityLevel,
                personToEdit.getAddress(),
                personToEdit.getTags());

        model.updatePerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_CHANGE_PLVL_SUCCESS,
                editedPerson.getName(), priorityLevel));
    }
}
