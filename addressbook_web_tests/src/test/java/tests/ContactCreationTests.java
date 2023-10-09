package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {
    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var first_name : List.of("", "first_name")) {
            for (var middle_name : List.of("", "middle_name")) {
                for (var last_name : List.of("", "last_name")) {
                    for (var nick_name : List.of("", "nick_name")) {
                        for (var title : List.of("", "title")) {
                            for (var company : List.of("", "company")) {
                                for (var address : List.of("", "address")) {
                                    for (var home_phone : List.of("", "home_phone")) {
                                        for (var mobile_phone : List.of("", "mobile_phone")) {
                                            for (var work_phone : List.of("", "work_phone")) {
                                                for (var fax_phone : List.of("", "fax_phone")) {
                                                    for (var email : List.of("", "email")) {
                                                        for (var email2 : List.of("", "email2")) {
                                                            for (var email3 : List.of("", "email3")) {
                                                                for (var homepage : List.of("", "homepage")) {
                                                                    for (var address_secondary : List.of("", "address_secondary")) {
                                                                        for (var home : List.of("", "home")) {
                                                                            for (var notes : List.of("", "notes")) {
                                                                                result.add(new ContactData(
                                                                                        first_name,
                                                                                        middle_name,
                                                                                        last_name,
                                                                                        nick_name,
                                                                                        title,
                                                                                        company,
                                                                                        address,
                                                                                        home_phone,
                                                                                        mobile_phone,
                                                                                        work_phone,
                                                                                        fax_phone,
                                                                                        email,
                                                                                        email2,
                                                                                        email3,
                                                                                        homepage,
                                                                                        address_secondary,
                                                                                        home,
                                                                                        notes));
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData(
                    randomSting(i * 10),
                    randomSting(i * 10),
                    randomSting(i * 10),
                    randomSting(i * 10),
                    randomSting(i * 2),
                    randomSting(i * 2),
                    randomSting(i * 2),
                    randomSting(i * 2),
                    randomSting(i * 2),
                    randomSting(i * 2),
                    randomSting(i * 2),
                    randomSting(i * 2),
                    randomSting(i * 2),
                    randomSting(i * 2),
                    randomSting(i * 2),
                    randomSting(i * 2),
                    randomSting(i * 2),
                    randomSting(i * 2)));
        }
        return result;
    }


    public static List<ContactData> negativeContactProvider() {
        return new ArrayList<ContactData>(List.of(
                new ContactData("firts_name'", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""),
                new ContactData("", "middle_name'", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""),
                new ContactData("", "", "last_name'", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""),
                new ContactData("", "", "", "nick_name'", "", "", "", "", "", "", "", "", "", "", "", "", "", ""),
                new ContactData("", "", "", "", "title'", "", "", "", "", "", "", "", "", "", "", "", "", ""),
                new ContactData("", "", "", "", "", "company'", "", "", "", "", "", "", "", "", "", "", "", ""),
                new ContactData("", "", "", "", "", "", "address'", "", "", "", "", "", "", "", "", "", "", ""),
                new ContactData("", "", "", "", "", "", "", "home_phone'", "", "", "", "", "", "", "", "", "", ""),
                new ContactData("", "", "", "", "", "", "", "", "mobile_phone'", "", "", "", "", "", "", "", "", ""),
                new ContactData("", "", "", "", "", "", "", "", "", "work_phone'", "", "", "", "", "", "", "", ""),
                new ContactData("", "", "", "", "", "", "", "", "", "", "fax_phone'", "", "", "", "", "", "", ""),
                new ContactData("", "", "", "", "", "", "", "", "", "", "", "email'", "", "", "", "", "", ""),
                new ContactData("", "", "", "", "", "", "", "", "", "", "", "", "email2'", "", "", "", "", ""),
                new ContactData("", "", "", "", "", "", "", "", "", "", "", "", "", "email3'", "", "", "", ""),
                new ContactData("", "", "", "", "", "", "", "", "", "", "", "", "", "", "homepage'", "", "", ""),
                new ContactData("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "address_secondary'", "", ""),
                new ContactData("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "home'", ""),
                new ContactData("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "notes'")));
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int contactsCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactsCount = app.contacts().getCount();
        Assertions.assertEquals(contactsCount + 1, newContactsCount);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContacts(ContactData contact) {
        int contactsCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactsCount = app.contacts().getCount();
        Assertions.assertEquals(contactsCount, newContactsCount);
    }
}
