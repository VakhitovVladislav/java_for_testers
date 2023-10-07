package tests;

import model.ContactData;

import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void canCreationContact(){
        app.contacts().createContact(new ContactData("firstname",
                "middlename", "lastname", "nickname", "title","company",
                "address", "home", "mobile", "work", "fax", "email", "email2",
                "email3", "homepage", "address2", "phone2", "notes"));
    }
    @Test
    public void canCreateContactWithEmptyFields() {
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.contacts().createContact(new ContactData().withName("some name"));
    }
}
