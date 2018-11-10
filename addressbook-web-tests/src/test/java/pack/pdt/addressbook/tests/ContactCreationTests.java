package pack.pdt.addressbook.tests;

import org.testng.annotations.*;
import pack.pdt.addressbook.model.ContactData;
import pack.pdt.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @Test
  public void testCreationContact() throws Exception {
    Contacts before = app.contact().all();
    app.goTo().addNewContactPage();
    ContactData contact = new ContactData()
            .withFirstname("John").withLastname("Doe").withCompany("Software Testing Company")
            .withAddress("Moscow").withWorkPhone("89000000001").withEmail("johnd@stc.com").withGroup("test1");
    app.contact().create(contact, true);

    assertEquals(app.contact().count(),before.size() + 1);
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact
            .withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
