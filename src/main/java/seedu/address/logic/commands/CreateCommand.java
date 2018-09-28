package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Creates a department to the address book.
 */
public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a department to the address book. "
            + "Parameters: "
            + "DEPARTMENT\n"
            + "Example: " + COMMAND_WORD + " "
            + "Junior Management";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_DEPARTMENT = "This department already exists in the address book";

    private final Department toCreate;

    /**
     * Creates an CreateCommand to add the specified {@code Department}
     */
    public CreateCommand(Department department) {
        requireNonNull(department);
        toCreate = department;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toCreate)) {
            throw new CommandException(MESSAGE_DUPLICATE_DEPARTMENT);
        }

        model.createDepartment(toCreate);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toCreate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateCommand // instanceof handles nulls
                && toCreate.equals(((CreateCommand) other).toCreate));
    }
}
