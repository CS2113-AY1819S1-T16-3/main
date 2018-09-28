package seedu.address.model.department;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Department's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDepartmentName(String)}
 */
public class DepartmentName {

    public static final String MESSAGE_NAME_CONSTRAINTS =
            "Department names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullDepartmentName;

    /**
     * Constructs a {@code Name}.
     *
     * @param departmentname A valid department name.
     */
    public DepartmentName(String departmentname) {
        requireNonNull(departmentname);
        checkArgument(isValidDepartmentName(departmentname), MESSAGE_NAME_CONSTRAINTS);
        fullDepartmentName = departmentname;
    }

    /**
     * Returns true if a given string is a valid department name.
     */
    public static boolean isValidDepartmentName(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullDepartmentName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DepartmentName // instanceof handles nulls
                && fullDepartmentName.equals(((DepartmentName) other).fullDepartmentName)); // state check
    }

    @Override
    public int hashCode() {
        return fullDepartmentName.hashCode();
    }

}
