package seedu.address.storage;

import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.leave.Approval;
import seedu.address.model.leave.Date;
import seedu.address.model.leave.EmployeeId;
import seedu.address.model.leave.Leave;

//@@author Hafizuddin-NUS
/**
 * JAXB-friendly version of the Leave.
 */
public class XmlAdaptedLeave {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Leave's %s field is missing!";

    @XmlElement(required = true)
    private String nric;
    @XmlElement(required = true)
    private String date;
    @XmlElement(required = true)
    private String status;

    /**
     * Constructs an XmlAdaptedLeave.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedLeave() {}

    /**
     * Constructs an {@code XmlAdaptedLeave} with the given person details.
     */
    public XmlAdaptedLeave(String nric, String date, String status) {
        this.nric = nric;
        this.date = date;
        this.status = status;
    }

    /**
     * Converts a given Leave into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedLeave
     */
    public XmlAdaptedLeave(Leave source) {
        nric = source.getEmployeeId().nric;
        date = source.getDate().date;
        status = source.getApproval().status;
    }

    /**
     * Converts this jaxb-friendly adapted leave object into the model's Leave object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Leave
     */

    public Leave toModelType() throws IllegalValueException {
        // final List<Tag> personTags = new ArrayList<>();
        //for (XmlAdaptedTag tag : tagged) {
        // personTags.add(tag.toModelType());
        // }
        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EmployeeId.class.getSimpleName()));
        }
        if (!EmployeeId.isValidEmployeeId(nric)) {
            throw new IllegalValueException(EmployeeId.MESSAGE_NRIC_CONSTRAINTS);
        }
        final EmployeeId modelEmployeeId = new EmployeeId(nric);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_DATE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Approval.class.getSimpleName()));
        }
        if (!Approval.isValidApproval(status)) {
            throw new IllegalValueException(Approval.MESSAGE_APPROVAL_CONSTRAINTS);
        }
        final Approval modelApproval = new Approval(status);

        // final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Leave(modelEmployeeId, modelDate, modelApproval);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedLeave)) {
            return false;
        }

        XmlAdaptedLeave otherLeave = (XmlAdaptedLeave) other;
        return Objects.equals(nric, otherLeave.nric)
                && Objects.equals(date, otherLeave.date)
                && Objects.equals(status, otherLeave.status);
    }


}
