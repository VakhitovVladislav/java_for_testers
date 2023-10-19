package addressbook.tests;

import addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;


public class ContactRemovalTests extends TestBase {
    @Test
    public void canRemoveContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData(
                    "firstname", "middlename", "lastname", "nickname", "title",
                    "company", "address", "home", "mobile", "work", "fax", "email",
                    "email2", "email3", "homepage", "address2", "phone2", "notes", ""));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, newContacts);
    }
    @Test
    public void canRemoveAllContactsAtOnce(){
        if(app.contacts().getCount() == 0){
            app.contacts().createContact(new ContactData("",
                    "firstname", "middlename", "lastname", "nickname", "title",
                    "company", "address", "home", "mobile", "work", "fax", "email",
                    "email2", "email3", "homepage", "address2", "phone2", "notes"));
        }
        app.contacts().removeAllContacts();
    }
}
