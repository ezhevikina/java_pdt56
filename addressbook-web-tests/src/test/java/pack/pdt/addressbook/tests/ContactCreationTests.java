package pack.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pack.pdt.addressbook.model.ContactData;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testCreationContact() throws Exception {
    Set<ContactData> before = app.contact().all();
    app.goTo().addNewContactPage();
    ContactData contact = new ContactData()
            .withFirstname("John").withLastname("Doe").withCompany("Software Testing Company")
            .withAddress("Moscow").withWorkPhone("89000000001").withEmail("johnd@stc.com").withGroup("test1");
    app.contact().create(contact, true);
    Set<ContactData> after = app.contact().all();

    Assert.assertEquals(after.size(),before.size() + 1);
    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
