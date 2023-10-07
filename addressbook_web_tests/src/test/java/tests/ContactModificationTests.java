package tests;


import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class ContactModificationTests extends TestBase{
    @Test
    void canModifyContact(){
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("firstname",
                    "middlename", "lastname", "nickname", "title","company",
                    "address", "home", "mobile", "work", "fax", "email", "email2",
                    "email3", "homepage", "address2", "phone2", "notes"));
        }
        app.contacts().modifyContact( new ContactData().withName("modifyName"));
    }

}
