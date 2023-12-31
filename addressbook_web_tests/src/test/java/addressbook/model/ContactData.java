package addressbook.model;

public record ContactData(String id, String first_name, String middle_name, String last_name,
                          String nick_name, String title, String company, String address,
                          String home_phone, String mobile_phone, String work_phone,
                          String fax_phone, String email, String email2, String email3,
                          String homepage, String address_secondary, String secondary_phone, String notes, String photo) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData(String first_name, String middle_name, String last_name, String nick_name,
                       String title, String company, String address, String home_phone, String mobile_phone,
                       String work_phone, String fax_phone, String email, String email2, String email3, String homepage,
                       String address_secondary, String secondary_phone, String notes, String photo) {
        this(null, first_name, middle_name, last_name, nick_name, title, company, address, home_phone, mobile_phone,
                work_phone, fax_phone, email, email2, email3, homepage, address_secondary, secondary_phone, notes, photo);
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withName(String first_name) {
        return new ContactData(this.id, first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withMiddleName(String middle_name) {
        return new ContactData(this.id, this.first_name, middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withLastName(String last_name) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                last_name, this.nick_name, this.title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withNickName(String nick_name) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, nick_name, this.title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withTitle(String title) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withCompany(String company) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withHomePhone(String home_phone) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, this.address, home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withMobilePhone(String mobile_phone) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, this.address, this.home_phone,
                mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withWorkPhone(String work_phone) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, work_phone, this.fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withFaxPhone(String fax_phone) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withHomePage(String homepage) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, this.email3, homepage,
                this.address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withHAddressSecondary(String address_secondary) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                address_secondary, this.secondary_phone, this.notes, this.photo);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, home, this.notes, this.photo);
    }

    public ContactData withNotes(String notes) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, notes, this.photo);
    }
    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.first_name, this.middle_name,
                this.last_name, this.nick_name, this.title,
                this.company, this.address, this.home_phone,
                this.mobile_phone, this.work_phone, this.fax_phone, this.email,
                this.email2, this.email3, this.homepage,
                this.address_secondary, this.secondary_phone, this.notes, photo);
    }

}
