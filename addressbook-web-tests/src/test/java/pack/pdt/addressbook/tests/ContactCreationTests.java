package pack.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pack.pdt.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testCreationContact() throws Exception {
    int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().gotoAddNewContactPage();
    app.getContactHelper().createContact(new ContactData("John", "Doe",
            "Software Testing Company", "Moscow", "89000000001",
            "johnd@stc.com", "test1"), true);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before + 1);
  }
}
