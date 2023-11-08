package addressbook.tests;

import addressbook.common.CommonFunctions;
import addressbook.model.ContactData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        /*for (var first_name : List.of("", "first_name")) {
            for (var last_name : List.of("", "last_name")) {
                for (var photo: List.of("", randomFile("src/test/resources/images")))
                result.add(new ContactData()
                        .withName(first_name)
                        .withLastName(last_name)
                        .withPhoto(photo)
                );
            }
        }*/


        var  mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>(){});
        result.addAll(value);
        return result;
    }


    public static List<ContactData> negativeContactProvider() {
        return new ArrayList<ContactData>(List.of(
                new ContactData().withName("firtsName'"),
                new ContactData().withMiddleName("middle_name'"),
                new ContactData().withLastName("last_name'")/*,
                new ContactData().withNickName("nick_name'"),
                new ContactData().withTitle("title'"),
                new ContactData().withCompany("company'"),
                new ContactData().withAddress("address'"),
                new ContactData().withHomePage("home_phone'"),
                new ContactData().withMobilePhone("mobile_phone'"),
                new ContactData().withWorkPhone("work_phone'"),
                new ContactData().withFaxPhone("fax_phone'"),
                new ContactData().withEmail("email'"),
                new ContactData().withEmail("email2'"),
                new ContactData().withEmail3("email3'"),
                new ContactData().withHomePage("homepage'"),
                new ContactData().withHAddressSecondary("address_secondary'"),
                new ContactData().withHome("home'"),
                new ContactData().withNotes("notes'")*/));
    }

    public static List<ContactData> singleRandomContact() throws IOException {
        return List.of(new ContactData()
                .withName(CommonFunctions.randomSting(10))
                .withLastName(CommonFunctions.randomSting(20))
                .withMiddleName(CommonFunctions.randomSting(30))
                .withTitle(CommonFunctions.randomSting(40))
        );

    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateContact(ContactData contact) {
        var oldContacts = app.jdbc().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.jdbc().getContactList();

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size() - 1).id();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact
                .withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);

    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Assertions.assertEquals(oldContacts, newContacts);
    }
}
