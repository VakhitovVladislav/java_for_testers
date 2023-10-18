package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.first_name());
        type(By.name("middlename"), contact.middle_name());
        type(By.name("lastname"), contact.last_name());
        type(By.name("nickname"), contact.nick_name());
        type(By.name("title"), contact.title());
        attach(By.xpath("//input[@name=\"photo\"]"), contact.photo());
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
        type(By.name("phone2"), contact.home());
        type(By.name("notes"), contact.notes());

    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact(ContactData contact) {
        returnToHomePage();
        selectContact(contact);
        removeSelectedContact();
        returnToHomePage();

    }

    public void modifyContact(ContactData contact ,ContactData modifiedContact) {
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

    public List<ContactData> getList(){
        returnToHomePage();
        var contacts = new ArrayList<ContactData>();
        var inputList = manager.driver.findElements(By.cssSelector("tbody > tr[name='entry']"));
        for (var input: inputList) {
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
}
