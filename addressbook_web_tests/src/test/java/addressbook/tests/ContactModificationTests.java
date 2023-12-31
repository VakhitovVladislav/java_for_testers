package addressbook.tests;


import addressbook.model.ContactData;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {
    @Test
    void canModifyContact(){
        Allure.step("Checking precodition canModifyContactTest", step ->{
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("",
                    "firstname", "middlename", "lastname", "nickname", "title",
                    "company", "address", "home", "mobile", "work", "fax", "email",
                    "email2", "email3", "homepage", "address2", "phone2", "notes", "aday"));
        }});
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withName("modifyName");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        newContacts.sort(compareById());
        expectedList.sort(compareById());
        Allure.step("Validating result canModifyContactTest", step -> {
        Assertions.assertEquals(newContacts, expectedList);});
    }

    private Comparator<ContactData> compareById() {
        return (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
    }

}
