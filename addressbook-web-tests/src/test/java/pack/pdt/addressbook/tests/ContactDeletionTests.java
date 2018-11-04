package pack.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    if (! app.getContactHelper().contactExists()) {
      app.getNavigationHelper().gotoAddNewContactPage();
      app.getContactHelper().createContact(new ContactData("John", null,
              null, null, null,
              "johnd@stc.com", "test1"), true);
    }
    app.getNavigationHelper().gotoToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().submitContactDeletion();
    app.getNavigationHelper().gotoToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before,after);
  }
}
