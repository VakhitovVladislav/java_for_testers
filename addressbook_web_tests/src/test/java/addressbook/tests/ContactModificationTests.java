package addressbook.tests;


import addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {
    @Test
    void canModifyContact(){
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("",
                    "firstname", "middlename", "lastname", "nickname", "title",
                    "company", "address", "home", "mobile", "work", "fax", "email",
                    "email2", "email3", "homepage", "address2", "phone2", "notes"));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withName("modifyName");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        newContacts.sort(compareById());
        expectedList.sort(compareById());
        Assertions.assertEquals(newContacts, expectedList);
    }

    private Comparator<ContactData> compareById() {
        return (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
    }

}
