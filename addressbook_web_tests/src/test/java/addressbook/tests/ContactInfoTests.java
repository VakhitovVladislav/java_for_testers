package addressbook.tests;

import addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase{
    @Test
    void testPhones(){
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                        Stream.of(contact.home_phone(), contact.mobile_phone(),contact.work_phone(),contact.secondary_phone())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))));

        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }
    @Test
    void testEmails(){
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.email(), contact.email2(),contact.email3())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))));

        var emails = app.contacts().getEmails();
        Assertions.assertEquals(expected, emails);
    }

    @Test
    void testAddresses(){
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.address())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))));

        var addresses = app.contacts().getAddresses();
        Assertions.assertEquals(expected, addresses);;
    }
}
