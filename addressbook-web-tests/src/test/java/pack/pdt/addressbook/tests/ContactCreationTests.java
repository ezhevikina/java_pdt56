package pack.pdt.addressbook.tests;

import org.testng.annotations.*;
import pack.pdt.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testCreationContact() throws Exception {
    app.getNavigationHelper().gotoAddNewContactPage();
    app.getContactHelper().createContact(new ContactData("John", "Doe",
            "Software Testing Company", "Moscow", "89000000001",
            "johnd@stc.com", "test1"), true);
  }
}
