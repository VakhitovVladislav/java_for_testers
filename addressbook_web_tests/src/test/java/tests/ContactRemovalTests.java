package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;



public class ContactRemovalTests extends TestBase {
    @Test
    public void canRemoveContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("firstname",
                    "middlename", "lastname", "nickname", "title","company",
                    "address", "home", "mobile", "work", "fax", "email", "email2",
                    "email3", "homepage", "address2", "phone2", "notes"));
        }
        app.contacts().removeContact();
    }
    @Test
    public void canRemoveAllContactsAtOnce(){
        if(app.contacts().getCount() == 0){
            app.contacts().createContact(new ContactData("firstname",
                    "middlename", "lastname", "nickname", "title","company",
                    "address", "home", "mobile", "work", "fax", "email", "email2",
                    "email3", "homepage", "address2", "phone2", "notes"));
        }
        app.contacts().removeAllContacts();
    }
}