package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.GroupData;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;


public class ContactRemovalTests extends TestBase {
    @Test
    public void canRemoveContact() {
        Allure.step("Checking precodition canRemoveContactTest", step ->{
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("",
                    "firstname", "middlename", "lastname", "nickname", "title",
                    "company", "address", "home", "mobile", "work", "fax", "email",
                    "email2", "email3", "homepage", "address2", "phone2", "notes", "aday"));
        }});
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Allure.step("Validating result canRemoveContactTest", step -> {
        Assertions.assertEquals(newContacts, newContacts);});
    }

    @Test
    public void canRemoveContactFromGroup(){
        Allure.step("Checking precodition canRemoveContactFromGroupTest", step ->{
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        if(app.hbm().getContactCount() == 0){
            app.hbm().createContact(new ContactData("",
                    "firstname", "middlename", "lastname", "nickname", "title",
                    "company", "address", "home", "mobile", "work", "fax", "email",
                    "email2", "email3", "homepage", "address2", "phone2", "notes", "aday"));
        }});
        var rnd = new Random();
        var indexForGroup = rnd.nextInt(app.hbm().getGroupList().size());
        if (app.hbm().getContactsInGroup(app.hbm().getGroupList().get(indexForGroup)).size() == 0){
            app.contacts().addContactInGroup(app.hbm().getContactList().get(0), app.hbm().getGroupList().get(indexForGroup));
        }
        var indexForContact = rnd.nextInt(app.hbm().getContactsInGroup(app.hbm().getGroupList().get(indexForGroup)).size());
        var group = app.hbm().getGroupList().get(indexForGroup);
        var contact = app.hbm().getContactsInGroup(group).get(indexForContact);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().removeContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.remove(indexForContact);
        Allure.step("Validating result canRemoveContactFromGroupTest", step -> {
        Assertions.assertEquals(expectedList, newRelated);
        });

    }
    @Test
    public void canRemoveAllContactsAtOnce(){
        Allure.step("Checking precodition canRemoveAllContactsAtOnceTest", step ->{
        if(app.hbm().getContactCount() == 0){
            app.hbm().createContact(new ContactData("",
                    "firstname", "middlename", "lastname", "nickname", "title",
                    "company", "address", "home", "mobile", "work", "fax", "email",
                    "email2", "email3", "homepage", "address2", "phone2", "notes", "aday"));
        }});
        app.contacts().removeAllContacts();
        Allure.step("Validating result canRemoveAllContactsAtOnceTest", step -> {
        Assertions.assertEquals(0, app.hbm().getContactCount());});
    }
}
