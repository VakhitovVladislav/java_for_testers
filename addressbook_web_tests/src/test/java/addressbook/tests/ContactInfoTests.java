package addressbook.tests;

import addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase{
    @Test
    void testPhones(){
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                        Stream.of(contact.home_phone(), contact.mobile_phone(),contact.work_phone(),contact.secondary_phone(),
                                        contact.email(), contact.email2(),contact.email3(), contact.address())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }
    @Test
    void testAllInfo(){
        var rnd = new Random();
        var contact = app.hbm().getContactList().get(rnd.nextInt(app.hbm().getContactList().size()));
        var expected = Stream.of(contact.home_phone(), contact.mobile_phone(),contact.work_phone(),contact.secondary_phone(),
                                contact.email(), contact.email2(),contact.email3(), contact.address())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"));
        var allInfo = app.contacts().getAllInfoContact(contact);
        Assertions.assertEquals(expected, allInfo);
    }

}
