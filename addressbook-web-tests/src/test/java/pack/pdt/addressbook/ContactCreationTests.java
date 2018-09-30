package pack.pdt.addressbook;

import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testCreationContact() throws Exception {
    gotoAddNewContactPage();
    fillGroupForm(new ContactData("John", "Doe", "Software Testing Company", "Moscow", "89000000001", "johnd@stc.com"));
    submitContactCreation();
    returnToHomePage();
  }

}
