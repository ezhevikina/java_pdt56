package pack.pdt.addressbook.tests;

import org.testng.annotations.Test;
import pack.pdt.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().showContactDetails();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("John", "Modified", "Software Testing Company", "Moscow", "89000000001", "johnd@stc.com"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }

  @Test
  public void testContactEdition() {
    app.getContactHelper().initContactEdition();
    app.getContactHelper().fillContactForm(new ContactData("John", "Edited", "Software Testing Company", "Moscow", "89000000001", "johnd@stc.com"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}