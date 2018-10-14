package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.LeaveList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyLeaveList;
import seedu.address.model.leave.Approval;
import seedu.address.model.leave.Date;
import seedu.address.model.leave.EmployeeId;
import seedu.address.model.leave.Leave;
import seedu.address.model.person.Address;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.password.Password;
import seedu.address.model.prioritylevel.PriorityLevel;
import seedu.address.model.prioritylevel.PriorityLevelEnum;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Nric("S1234567E"), new Password("Password"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Department("Top Management"),
                new PriorityLevel(PriorityLevelEnum.ADMINISTRATOR.getPriorityLevelCode()),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends", "ADMINISTRATOR", "S1234567E", "Password")),
            new Person(new Name("Bernice Yu"), new Nric("T1234567E"), new Password("Password"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Department("Senior Management"),
                new PriorityLevel(PriorityLevelEnum.MANAGER.getPriorityLevelCode()),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends", "MANAGER", "T1234567E", "Password")),
            new Person(new Name("Charlotte Oliveiro"), new Nric("F1234567E"), new Password("Password"),
                new Phone("93210283"), new Email("charlotte@example.com"), new Department("Middle Management"),
                new PriorityLevel(PriorityLevelEnum.BASIC.getPriorityLevelCode()),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Nric("S5473621G"), new Password("NeuEr2018"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Department("Junior Management"),
                new PriorityLevel(PriorityLevelEnum.BASIC.getPriorityLevelCode()),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Nric("S8570520Q"), new Password("NeuEr2018"),
                new Phone("92492021"), new Email("irfan@example.com"), new Department("Junior Management"),
                new PriorityLevel(PriorityLevelEnum.BASIC.getPriorityLevelCode()),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Nric("F5169584T"), new Password("NeuEr2018"),
                new Phone("92624417"), new Email("royb@example.com"), new Department("Junior Management"),
                new PriorityLevel(PriorityLevelEnum.BASIC.getPriorityLevelCode()),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static Leave[] getSampleLeaves() {
        return new Leave[] {
                new Leave(new EmployeeId("S1234567E"), new Date("01/01/2019"), new Approval("APPROVED")),
                new Leave(new EmployeeId("F1234567E"), new Date("01/12/2019"), new Approval("PENDING")),
                new Leave(new EmployeeId("S8570520Q"), new Date("01/12/2019"), new Approval("PENDING"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyLeaveList getSampleLeaveList() {
        LeaveList leaveList = new LeaveList();
        for (Leave sampleLeave : getSampleLeaves()) {
            leaveList.addRequest(sampleLeave);
        }
        return leaveList;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
