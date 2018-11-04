package pack.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pack.pdt.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testCreationContact() throws Exception {
    List<ContactData> before = app.contact().list();
    app.goTo().addNewContactPage();
    ContactData contact = new ContactData("John", "Doe",
            "Software Testing Company", "Moscow", "89000000001",
            "johnd@stc.com", "test1");
    app.contact().create(contact, true);
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(),before.size() + 1);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
