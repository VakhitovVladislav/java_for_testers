package addressbook.manager;

import addressbook.manager.hbm.ContactRecord;
import addressbook.manager.hbm.GroupRecord;
import addressbook.model.ContactData;
import addressbook.model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory =
                new Configuration()
                        .addAnnotatedClass(ContactRecord.class)
                        .addAnnotatedClass(GroupRecord.class)
                        // PostgreSQL
                        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                        .setProperty(AvailableSettings.USER, "root")
                        .setProperty(AvailableSettings.PASS, "")
                        .buildSessionFactory();

    }

    static List<GroupData> convertGroupList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    public List<GroupData> getGroupList() {
        return convertGroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));

    }



    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }

    private List<ContactData> convertContactList(List<ContactRecord> records) {
        List<ContactData> result = new ArrayList<>();
        for (var record : records){
            result.add(convertContact(record));
        }
        return result;
    }
    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    private ContactData convertContact(ContactRecord record) {
        return new ContactData().withId("" + record.id)
                .withName(record.first_name)
                .withLastName(record.last_name)
                .withNickName(record.nick_name)
                .withTitle(record.title)
                .withCompany(record.company)
                .withAddress(record.address)
                .withHomePhone(record.home_phone)
                .withMobilePhone(record.mobile_phone)
                .withFaxPhone(record.fax_phone)
                .withEmail(record.email)
                .withEmail2(record.email2)
                .withEmail3(record.email3)
                .withHomePage(record.homepage)
                .withHAddressSecondary(record.address_secondary)
                .withHome(record.home)
                .withNotes(record.notes);
    }

    private ContactRecord convertContact(ContactData data) {
        var id = data.id();
        if("".equals(id)){
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.first_name(), data.middle_name(), data.last_name(),
                data.nick_name(), data.title(),
                data.company(), data.address(), data.home_phone(), data.mobile_phone(),
                data.work_phone(), data.fax_phone(), data.email(), data.email2(), data.email3(),
                data.homepage(), data.address_secondary(), data.home(), data.notes());
    }
    public long getContactCount() {
        return  (sessionFactory.fromSession(session -> {
            return session.createQuery("select count(*) from ContactRecord", Long.class).getSingleResult();
        }));

    }
    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContact(contactData));
            session.getTransaction().commit();
        });
    }
}
