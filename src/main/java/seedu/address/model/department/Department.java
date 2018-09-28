package seedu.address.model.department;

import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Department in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Department {

    // Identity fields
    private final DepartmentName departmentName;

    /**
     * Every field must be present and not null.
     */
    public Department(DepartmentName departmentName) {
        requireAllNonNull(departmentName);
        this.departmentName = departmentName;
    }

    public DepartmentName getDepartmentName() {
        return departmentName;
    }

    /**
     * Returns true if both departments of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two departments.
     */
    public boolean isSameDepartment(Department otherDepartment) {
        if (otherDepartment == this) {
            return true;
        }

        return otherDepartment != null
                && otherDepartment.getDepartmentName().equals(getDepartmentName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Department)) {
            return false;
        }

        Department otherDepartment = (Department) other;
        return otherDepartment.getDepartmentName().equals(getDepartmentName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(departmentName);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDepartmentName());
        return builder.toString();
    }

}
