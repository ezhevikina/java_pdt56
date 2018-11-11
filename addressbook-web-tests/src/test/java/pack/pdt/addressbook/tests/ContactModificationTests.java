package pack.pdt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.ContactData;
import pack.pdt.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.goTo().addNewContactPage();
      app.contact().create(new ContactData()
              .withFirstname("John")
              .withLastname("Snow")
              .withEmail("johns@stc.com")
              .withEmail3("jj@fakemail.com")
              .withGroup("test1")
              .withHomePhone("+7 (495) 057-99-00")
              .withMobilePhone("8 900 000 0000")
              .withAddress("South Corner, 5"));
    }
  }

  /*
  @Test(enabled = false)
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("John1").withLastname("Edited1")
            .withCompany("Software Testing Company").withAddress("Moscow")
            .withWorkPhone("89000000001").withEmail("johnd@stc.com").withGroup("test1");
    app.contact().showDetailsById(modifiedContact.getId());
    app.contact().initModification();
    app.contact().update(contact);

    assertEquals(app.contact().count(),before.size());
    Contacts after = app.contact().all();
    assertThat(before, equalTo(after.without(contact).withAdded(modifiedContact)));
  }
*/

  @Test(enabled = true)
  public void testContactEdition() {
    Contacts before = app.db().contacts();
    ContactData editedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(editedContact.getId()).withFirstname("John").withLastname("Edfited1")
            .withWorkPhone("89000000001").withEmail("johnd@stc.com");
    app.goTo().homePage();
    app.contact().initEditionById(editedContact.getId());
    app.contact().update(contact);
    app.goTo().homePage();

    assertEquals(app.contact().count(),before.size());
    Contacts after = app.db().contacts();
    assertThat(before, equalTo(after.without(contact).withAdded(editedContact)));
  }

}
