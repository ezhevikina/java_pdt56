package pack.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (! app.contact().exists()) {
      app.goTo().addNewContactPage();
      app.contact().create(new ContactData()
              .withFirstname("John").withEmail("johnd@stc.com").withGroup("test1"), true);
    }
  }

  @Test(enabled = true)
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withFirstname("John").withLastname("Modified").withCompany("Software Testing Company")
            .withAddress("Moscow").withWorkPhone("89000000001").withEmail("johnd@stc.com").withGroup("test1");
    app.contact().showDetailsById(modifiedContact.getId());
    app.contact().initModification();
    app.contact().update(contact);
    Set<ContactData> after = app.contact().all();

    Assert.assertEquals(after.size(),before.size());
    before.remove(modifiedContact);
    before.add(contact);
    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    Assert.assertEquals(before,after);
  }

  @Test(enabled = true)
  public void testContactEdition() {
    Set<ContactData> before = app.contact().all();
    ContactData editedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withFirstname("John").withLastname("Edited").withCompany("Software Testing Company")
            .withAddress("Moscow").withWorkPhone("89000000001").withEmail("johnd@stc.com").withGroup("test1");
    app.contact().initEditionById(editedContact.getId());
    app.contact().update(contact);
    Set<ContactData> after = app.contact().all();

    Assert.assertEquals(after.size(),before.size());
    before.remove(editedContact);
    before.add(contact);
    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    Assert.assertEquals(before,after);
  }
}
