package pack.pdt.addressbook.tests;

import org.testng.annotations.*;
import pack.pdt.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testCreationContact() throws Exception {
    app.gotoAddNewContactPage();
    app.fillGroupForm(new ContactData("John", "Doe", "Software Testing Company", "Moscow", "89000000001", "johnd@stc.com"));
    app.submitContactCreation();
    app.returnToHomePage();
  }

}
