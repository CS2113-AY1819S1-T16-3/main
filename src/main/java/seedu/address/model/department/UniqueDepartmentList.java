package seedu.address.model.department;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.department.exceptions.DepartmentNotFoundException;
import seedu.address.model.department.exceptions.DuplicateDepartmentException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of departments that enforces uniqueness between its elements and does not allow nulls.
 * A department is considered unique by comparing using {@code Department#isSameDepartment(Department)}.
 * As such, adding and updating of departments uses Department#isSameDepartment(Department) for equality so as to
 * ensure that the department being added or updated is unique in terms of identity in the UniqueDepartmentList.
 * However, the removal of a department uses Department#equals(Object) so as to ensure that the department with exactly
 * the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Department#isSameDepartment(Department)
 */
public class UniqueDepartmentList implements Iterable<Department> {

    private final ObservableList<Department> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Department toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDepartment);
    }

    /**
     * Adds a department to the list.
     * The department must not already exist in the list.
     */
    public void add(Department toCreate) {
        requireNonNull(toCreate);
        if (contains(toCreate)) {
            throw new DuplicateDepartmentException();
        }
        internalList.add(toCreate);
    }

    /**
     * Replaces the department {@code target} in the list with {@code editedDepartment}.
     * {@code target} must exist in the list.
     * The department identity of {@code editedDepartment} must not be the same as another existing department
     * in the list.
     */
    public void setDepartment(Department target, Department editedDepartment) {
        requireAllNonNull(target, editedDepartment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DepartmentNotFoundException();
        }

        if (!target.isSameDepartment(editedDepartment) && contains(editedDepartment)) {
            throw new DuplicateDepartmentException();
        }

        internalList.set(index, editedDepartment);
    }

    /**
     * Removes the equivalent department from the list.
     * The department must exist in the list.
     */
    public void remove(Department toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DepartmentNotFoundException();
        }
    }

    public void setPersons(UniqueDepartmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code departments}.
     * {@code departments} must not contain duplicate departments.
     */
    public void setDepartments(List<Department> departments) {
        requireAllNonNull(departments);
        if (!departmentsAreUnique(departments)) {
            throw new DuplicateDepartmentException();
        }

        internalList.setAll(departments);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Department> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    @Override
    public Iterator<Department> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDepartmentList // instanceof handles nulls
                && internalList.equals(((UniqueDepartmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code departments} contains only unique departments.
     */
    private boolean departmentsAreUnique(List<Department> departments) {
        for (int i = 0; i < departments.size() - 1; i++) {
            for (int j = i + 1; j < departments.size(); j++) {
                if (departments.get(i).isSameDepartment(departments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
