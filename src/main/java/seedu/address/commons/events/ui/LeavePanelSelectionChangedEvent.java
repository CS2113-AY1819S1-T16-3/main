package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.leave.Leave;

//@@author Hafizuddin-NUS
/**
 * Represents a selection change in the Event List Panel
 */
public class LeavePanelSelectionChangedEvent extends BaseEvent {


    private final Leave newSelection;

    public LeavePanelSelectionChangedEvent(Leave newSelection) {
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public Leave getNewSelection() {
        return newSelection;
    }
}
