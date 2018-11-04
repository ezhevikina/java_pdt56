package pack.pdt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.ContactData;
import pack.pdt.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (! app.contact().exists()) {
      app.goTo().addNewContactPage();
      app.contact().create(new ContactData().withFirstname("John").withEmail("johnd@stc.com").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    Contacts after = app.contact().all();

    assertEquals(after.size(), before.size() -1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
