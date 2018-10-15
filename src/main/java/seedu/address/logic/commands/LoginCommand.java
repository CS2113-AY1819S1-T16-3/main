package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;

import java.util.List;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;

import seedu.address.model.Model;
import seedu.address.model.SessionManager;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.password.Password;

//@@author jylee-git
/**
 * Enables the use of application by logging in using the specified personnel's NRIC and password.
 */
public class LoginCommand extends Command {
    public static final String COMMAND_WORD = "login";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enables the use of the application by logging in"
            + " using the specified personnel's NRIC and password.\n"
            + "Parameters: " + PREFIX_NRIC + "NRIC " + PREFIX_PASSWORD + "PASSWORD\n"
            + "Example:" + COMMAND_WORD + " " + PREFIX_NRIC + "S9724688J "
            + PREFIX_PASSWORD + "NeUeR2018";

    public static final String INVALID_LOGIN_CREDENTIALS = "Login failed. Incorrect NRIC and/or password.";
    public static final String LOGIN_SUCCESS = "Login successful. You are logged in as: %s";
    public static final String ALREADY_LOGGED_IN = "You are already logged in. Logout first before logging in again.";

    private Nric loginNric;
    private Password loginPassword;
    private Person personToBeLoggedIn;
    private Nric nricToBeLoggedIn;

    public LoginCommand(Nric loginNric, Password loginPassword) {
        requireNonNull(loginNric);
        requireNonNull(loginPassword);

        this.loginNric = loginNric;
        this.loginPassword = loginPassword;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        if (SessionManager.isLoggedIn()) {
            throw new CommandException(ALREADY_LOGGED_IN);
        }
        if (!isLoginCredentialsValid(model)) {
            throw new CommandException(INVALID_LOGIN_CREDENTIALS);
        } else {
            SessionManager.loginToSession(model, nricToBeLoggedIn);
            return new CommandResult(String.format(LOGIN_SUCCESS, personToBeLoggedIn.getNric()));
        }
    }

    /**
     * Returns true if login NRIC and Password is equal to the one in AddressBook.
     * Returns false if login NRIC tallies but login password is wrong, or NRIC is not found.
     */
    private boolean isLoginCredentialsValid(Model model) {
        // Grabs the list of ALL people in the address book.
        List<Person> allPersonsList = model.getAddressBook().getPersonList();

        for (Person currPerson : allPersonsList) {
            if ((currPerson.getNric()).toString().equals(loginNric.toString())) {
                if ((currPerson.getPassword()).toString().equals(loginPassword.toString())) {
                    personToBeLoggedIn = currPerson;
                    nricToBeLoggedIn = currPerson.getNric();
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }
}
