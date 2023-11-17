package addressbook.manager.hbm;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    @Column
    public int id;

    @Column(name = "firstname")
    public String first_name;

    @Column(name = "middlename")
    public String middle_name;

    @Column(name = "lastname")
    public String last_name;

    @Column(name = "nickname")
    public String nick_name;
    @Column(name = "title")
    public String title;
    @Column(name = "company")
    public String company;
    @Column(name = "address")
    public String address;

    @Column(name = "home")
    public String home_phone;

    @Column(name = "mobile")
    public String mobile_phone;

    @Column(name = "work")
    public String work_phone;

    @Column(name = "fax")
    public String fax_phone;
    @Column(name = "email")
    public String email;
    @Column(name = "email2")
    public String email2;
    @Column(name = "email3")
    public String email3;
    @Column(name = "homepage")
    public String homepage;

    @Column(name = "address2")
    public String address_secondary;
    @Column(name = "phone2")
    public String secondary_phone;
    @Column(name = "notes")
    public String notes;

    @Column(name = "deprecated")
    public Date deprecated = new Date();
    public ContactRecord() {
    }

    public ContactRecord(int id, String first_name, String middle_name, String last_name, String nick_name,
                         String title, String company, String address, String home_phone, String mobile_phone,
                         String work_phone, String fax_phone, String email, String email2, String email3,
                         String homepage, String address_secondary, String secondary_phone, String notes) {
        this.id = id;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.nick_name = nick_name;
        this.title = title;
        this.company = company;
        this.address = address;
        this.home_phone = home_phone;
        this.mobile_phone = mobile_phone;
        this.work_phone = work_phone;
        this.fax_phone = fax_phone;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.homepage = homepage;
        this.address_secondary = address_secondary;
        this.secondary_phone = secondary_phone;
        this.notes = notes;
    }
}
