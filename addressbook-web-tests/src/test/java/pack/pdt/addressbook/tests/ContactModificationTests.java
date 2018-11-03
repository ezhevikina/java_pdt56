package pack.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test(enabled = true)
  public void testContactModification() {
    if (! app.getContactHelper().contactExists()) {
      app.getNavigationHelper().gotoAddNewContactPage();
      app.getContactHelper().createContact(new ContactData("John", null,
              null, null, null,
              "johnd@stc.com", "test1"), true);
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().showContactDetails(before - 1);
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("John", "Modified",
            "Software Testing Company", "Moscow", "89000000001",
            "johnd@stc.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before);
  }

  @Test(enabled = false)
  public void testContactEdition() {
    if (! app.getContactHelper().contactExists()) {
      app.getNavigationHelper().gotoAddNewContactPage();
      app.getContactHelper().createContact(new ContactData("John", null,
              null, null, null,
              "johnd@stc.com", "test1"), true);
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().initContactEdition(before - 1);
    app.getContactHelper().fillContactForm(new ContactData("John", "Edited",
            "Software Testing Company", "Moscow", "89000000001",
            "johnd@stc.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before);
  }
}
