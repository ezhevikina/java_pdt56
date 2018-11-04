package pack.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().gotoToHomePage();
    if (! app.getContactHelper().contactExists()) {
      app.getNavigationHelper().gotoAddNewContactPage();
      app.getContactHelper().createContact(new ContactData("John", null,
              null, null, null,
              "johnd@stc.com", "test1"), true);
    }
  }

  @Test(enabled = true)
  public void testContactModification() {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    ContactData contact = new ContactData("John", "Modified",
            "Software Testing Company", "Moscow", "89000000001",
            "johnd@stc.com", null);
    app.getContactHelper().showContactDetails(index);
    app.getContactHelper().initContactModification();
    app.getContactHelper().updateContact(index, contact);
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(),before.size());
    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }

  @Test(enabled = false)
  public void testContactEdition() {
    ContactData contact = new ContactData("John", "Modified",
            "Software Testing Company", "Moscow", "89000000001",
            "johnd@stc.com", null);
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().initContactEdition(before - 1);
    app.getContactHelper().updateContact(before -1, contact);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before);
  }
}
