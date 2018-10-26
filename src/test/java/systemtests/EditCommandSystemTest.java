//package systemtests;
//
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
//import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
//import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
//import static seedu.address.testutil.TypicalPersons.ALICE;
//import static seedu.address.testutil.TypicalPersons.AMY;
//import static seedu.address.testutil.TypicalPersons.BENSON;
//import static seedu.address.testutil.TypicalPersons.BOB;
//import static seedu.address.testutil.TypicalPersons.KEYWORD_MATCHING_MEIER;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import seedu.address.commons.core.Messages;
//import seedu.address.commons.core.index.Index;
//import seedu.address.logic.commands.Command;
//import seedu.address.logic.commands.EditCommand;
//import seedu.address.logic.commands.RedoCommand;
//import seedu.address.logic.commands.UndoCommand;
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.model.Model;
//import seedu.address.model.person.Address;
//import seedu.address.model.person.Email;
//import seedu.address.model.person.Name;
//import seedu.address.model.person.Person;
//import seedu.address.model.person.Phone;
//import seedu.address.model.prioritylevel.PriorityLevelEnum;
//import seedu.address.model.tag.Tag;
//import seedu.address.session.Session;
//import seedu.address.session.SessionManager;
//import seedu.address.testutil.PersonBuilder;
//
//public class EditCommandSystemTest extends AddressBookSystemTest {
//
//    SessionManager sessionManager;
//
//    @Override
//    @Before
//    public void setUp() {
//        super.setUp();
//        sessionManager = SessionManager.getInstance(getModel());
//    }
//
//    @Test
//    public void edit() throws CommandException {
//        Model model = getModel();
//
//        /* ----------------- Performing edit operation while an unfiltered list is being shown ---------------------- */
//        /* PRE-CONDITION FOR EDITING A PERSON's PROFILE: The NRIC of the person to be edited MUST be the same. */
//
//        /* Case: edit all fields, command with leading spaces, trailing spaces and multiple spaces between each field
//         * -> edited
//         * PRE-CONDITION: NRIC field MUST be the same
//         */
//        String command = " " + EditCommand.COMMAND_WORD + "  " + NAME_DESC_BOB + "  "
//                + PHONE_DESC_BOB + " " + EMAIL_DESC_BOB + "  " + ADDRESS_DESC_BOB + " " + TAG_DESC_HUSBAND + " ";
//        Person editedPerson = new PersonBuilder(ALICE).withName(BOB.getName().toString())
//                .withTags(VALID_TAG_HUSBAND).build();
//        Person personToEdit = new PersonBuilder(ALICE).build();
//        assertCommandSuccess(command, personToEdit, editedPerson);
//
//        /* Case: undo editing the last person in the list -> last person restored */
//        command = UndoCommand.COMMAND_WORD;
//        String expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
//        assertCommandSuccess(command, model, expectedResultMessage);
//
//        /* Case: redo editing the last person in the list -> last person edited again */
//        command = RedoCommand.COMMAND_WORD;
//        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
//        model.updatePerson(
//                getModel().getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPerson);
//        assertCommandSuccess(command, model, expectedResultMessage);
//
//        /* Case: edit a person with new values same as existing values -> edited */
//        Person editedBob = new PersonBuilder(BOB).withNric(ALICE.getNric().toString()).withPriorityLevel(0)
//                .withTags(VALID_TAG_HUSBAND).build();
//        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
//                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND;
//        assertCommandSuccess(command, index, editedBob);
//
//
//        /* Case: edit a person with new values same as another person's values but with different name -> edited */
//        assertTrue(getModel().getAddressBook().getPersonList().contains(editedBob));
//        index = INDEX_SECOND_PERSON;
//        assertNotEquals(getModel().getFilteredPersonList().get(index.getZeroBased()), BOB);
//        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
//                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND;
//        editedPerson = new PersonBuilder(BOB).withName(VALID_NAME_AMY).withNric(BENSON.getNric().toString())
//                .withPriorityLevel(0).build();
//        assertCommandSuccess(command, index, editedPerson);
//
//        /* Case: edit a person with new values same as another person's values but with different phone and email
//         * -> edited
//         */
//        index = INDEX_SECOND_PERSON;
//        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_BOB + PHONE_DESC_AMY + EMAIL_DESC_AMY
//                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND;
//        editedPerson = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY).withPriorityLevel(0)
//                .withNric(BENSON.getNric().toString()).withEmail(VALID_EMAIL_AMY).build();
//        assertCommandSuccess(command, index, editedPerson);
//
//        /* Case: clear tags -> cleared */
//        index = INDEX_FIRST_PERSON;
//        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + " " + PREFIX_TAG.getPrefix();
//        Person personToEdit = getModel().getFilteredPersonList().get(index.getZeroBased());
//        editedPerson = new PersonBuilder(personToEdit).withTags().build();
//        assertCommandSuccess(command, index, editedPerson);
//
//        /* ------------------ Performing edit operation while a filtered list is being shown ------------------------ */
//
//        /* Case: filtered person list, edit index within bounds of address book and person list -> edited */
//        showPersonsWithName(KEYWORD_MATCHING_MEIER);
//        index = INDEX_FIRST_PERSON;
//        assertTrue(index.getZeroBased() < getModel().getFilteredPersonList().size());
//        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + " " + NAME_DESC_BOB;
//        personToEdit = getModel().getFilteredPersonList().get(index.getZeroBased());
//        editedPerson = new PersonBuilder(personToEdit).withName(VALID_NAME_BOB).build();
//        assertCommandSuccess(command, index, editedPerson);
//
//        /* Case: filtered person list, edit index within bounds of address book but out of bounds of person list
//         * -> rejected
//         */
//        showPersonsWithName(KEYWORD_MATCHING_MEIER);
//        int invalidIndex = getModel().getAddressBook().getPersonList().size();
//        assertCommandFailure(EditCommand.COMMAND_WORD + " " + invalidIndex + NAME_DESC_BOB,
//                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//
//        /* --------------------- Performing edit operation while a person card is selected -------------------------- */
//
//        /* Case: selects first card in the person list, edit a person -> edited, card selection remains unchanged but
//         * browser url changes
//         */
//        showAllPersons();
//        index = INDEX_FIRST_PERSON;
//        selectPerson(index);
//        Person editedAlice = new PersonBuilder(AMY).withNric(ALICE.getNric().toString()).withPriorityLevel(0)
//                .withPassword(ALICE.getPassword().toString()).build();
//        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
//                + ADDRESS_DESC_AMY + TAG_DESC_FRIEND;
//        // this can be misleading: card selection actually remains unchanged but the
//        // browser's url is updated to reflect the new person's name
//        assertCommandSuccess(command, index, editedAlice, index);
//
//        /* --------------------------------- Performing invalid edit operation -------------------------------------- */
//
//        /* Case: invalid index (0) -> rejected */
//        assertCommandFailure(EditCommand.COMMAND_WORD + " 0" + NAME_DESC_BOB,
//                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
//
//        /* Case: invalid index (-1) -> rejected */
//        assertCommandFailure(EditCommand.COMMAND_WORD + " -1" + NAME_DESC_BOB,
//                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
//
//        /* Case: invalid index (size + 1) -> rejected */
//        invalidIndex = getModel().getFilteredPersonList().size() + 1;
//        assertCommandFailure(EditCommand.COMMAND_WORD + " " + invalidIndex + NAME_DESC_BOB,
//                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//
//        /* Case: missing index -> rejected */
//        assertCommandFailure(EditCommand.COMMAND_WORD + NAME_DESC_BOB,
//                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
//
//        /* Case: missing all fields -> rejected */
//        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(),
//                EditCommand.MESSAGE_NOT_EDITED);
//
//        /* Case: invalid name -> rejected */
//        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + INVALID_NAME_DESC,
//                Name.MESSAGE_NAME_CONSTRAINTS);
//
//        /* Case: invalid phone -> rejected */
//        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + INVALID_PHONE_DESC,
//                Phone.MESSAGE_PHONE_CONSTRAINTS);
//
//        /* Case: invalid email -> rejected */
//        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + INVALID_EMAIL_DESC,
//                Email.MESSAGE_EMAIL_CONSTRAINTS);
//
//        /* Case: invalid address -> rejected */
//        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + INVALID_ADDRESS_DESC,
//                Address.MESSAGE_ADDRESS_CONSTRAINTS);
//
//        /* Case: invalid tag -> rejected */
//        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + INVALID_TAG_DESC,
//                Tag.MESSAGE_TAG_CONSTRAINTS);
//    }
//
//    private void assertCommandSuccess(String command, Person personToEdit, Person editedPerson)
//            throws CommandException {
//        Model expectedModel = getModel();
//        expectedModel.updatePerson(personToEdit, editedPerson);
//
//        sessionManager.destroy();
//        sessionManager = SessionManager.getInstance(getModel());
//        sessionManager.loginToSession(personToEdit.getNric(), personToEdit.getPassword());
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);
//        assertCommandSuccess(command, expectedModel, expectedMessage);
//    }
//    /**
//     * Executes {@code command} and in addition,<br>
//     * 1. Asserts that the command box displays an empty string.<br>
//     * 2. Asserts that the result display box displays {@code expectedResultMessage}.<br>
//     * 3. Asserts that the browser url and selected card update accordingly depending on the card at
//     * {@code expectedSelectedCardIndex}.<br>
//     * 4. Asserts that the status bar's sync status changes.<br>
//     * 5. Asserts that the command box has the default style class.<br>
//     * Verifications 1 and 2 are performed by
//     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
//     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
//     * @see AddressBookSystemTest#assertSelectedCardChanged(Index)
//     */
//    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
//        executeCommand(command);
//        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
//        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
//        assertCommandBoxShowsDefaultStyle();
//        assertStatusBarUnchangedExceptSyncStatus();
//    }
//
//    /**
//     * Executes {@code command} and in addition,<br>
//     * 1. Asserts that the command box displays {@code command}.<br>
//     * 2. Asserts that result display box displays {@code expectedResultMessage}.<br>
//     * 3. Asserts that the browser url, selected card and status bar remain unchanged.<br>
//     * 4. Asserts that the command box has the error style.<br>
//     * Verifications 1 and 2 are performed by
//     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
//     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
//     */
//    private void assertCommandFailure(String command, String expectedResultMessage) {
//        Model expectedModel = getModel();
//
//        executeCommand(command);
//        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
//        assertSelectedCardUnchanged();
//        assertCommandBoxShowsErrorStyle();
//        assertStatusBarUnchanged();
//    }
//}
