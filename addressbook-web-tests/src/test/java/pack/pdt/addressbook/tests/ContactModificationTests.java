package pack.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

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
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData()
            .withFirstname("John").withLastname("Modified").withCompany("Software Testing Company")
            .withAddress("Moscow").withWorkPhone("89000000001").withEmail("johnd@stc.com").withGroup("test1");
    app.contact().showDetails(index);
    app.contact().initModification();
    app.contact().update(index, contact);
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(),before.size());
    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }

  @Test(enabled = false)
  public void testContactEdition() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData()
            .withFirstname("John").withLastname("Edited").withCompany("Software Testing Company")
            .withAddress("Moscow").withWorkPhone("89000000001").withEmail("johnd@stc.com").withGroup("test1");
    app.contact().initEdition(index);
    app.contact().update(index, contact);
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(),before.size());
    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
