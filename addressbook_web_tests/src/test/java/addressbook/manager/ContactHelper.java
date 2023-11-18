package addressbook.manager;

import addressbook.model.ContactData;
import addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactHelper extends addressbook.manager.HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.first_name());
        type(By.name("middlename"), contact.middle_name());
        type(By.name("lastname"), contact.last_name());
        type(By.name("nickname"), contact.nick_name());
        type(By.name("title"), contact.title());
        //attach(By.xpath("//input[@name=\"photo\"]"), contact.photo());
        type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.home_phone());
        type(By.name("mobile"), contact.mobile_phone());
        type(By.name("work"), contact.work_phone());
        type(By.name("fax"), contact.fax_phone());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
        type(By.name("homepage"), contact.homepage());
        type(By.name("address2"), contact.address_secondary());
        type(By.name("phone2"), contact.secondary_phone());
        type(By.name("notes"), contact.notes());

    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void createContact(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    public void addContactInGroup(ContactData contact, GroupData group) {
        returnToHomePage();
        selectContact(contact);
        selectGroupForAddToGroup(group);
        submitAddContactInGroup();
    }

    private void submitAddContactInGroup() {
        click(By.name("add"));
    }

    public void removeContact(ContactData contact) {
        returnToHomePage();
        selectContact(contact);
        removeSelectedContact();
        returnToHomePage();
    }

    public void removeContact(ContactData contact, GroupData group) {
        returnToHomePage();
        selectGroupFromHomePage(group);
        selectContact(contact);
        removeSelectedContactInGroup();
        returnToHomePage();
    }

    private void selectGroupFromHomePage(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }


    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void selectGroupForAddToGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }


    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        returnToHomePage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href=\"edit.php?id=%s\"] > img", contact.id())));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public boolean isContactPresent() {
        returnToHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@type='button'][@value='Delete']"));
        manager.driver.switchTo().alert().accept();
    }

    private void removeSelectedContactInGroup() {
        click(By.name("remove"));
    }

    public int getCount() {
        returnToHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        returnToHomePage();
        selectAllContacts();
        removeSelectedContact();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getList() {
        returnToHomePage();
        var contacts = new ArrayList<ContactData>();
        var inputList = manager.driver.findElements(By.cssSelector("tbody > tr[name='entry']"));
        for (var input : inputList) {
            var name = input.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var last_name = input.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var checkbox = input.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData()
                    .withId(id)
                    .withName(name)
                    .withLastName(last_name));
        }
        return contacts;
    }


    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public Map<String, String> getEmails() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var emails = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, emails);
        }
        return result;
    }


    public Map<String, String> getAddresses() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var addresses = row.findElements(By.tagName("td")).get(3).getText();
            result.put(id, addresses);
        }
        return result;
    }


    public String getAllInfoContact(ContactData contact) {
        var string = manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../..", contact.id())
        ));
        var phones = string.findElements(By.tagName("td")).get(5).getText();
        var emails = string.findElements(By.tagName("td")).get(4).getText();
        var addresses = string.findElements(By.tagName("td")).get(3).getText();
        return phones + "\n" + emails + "\n" + addresses;
    }
}
