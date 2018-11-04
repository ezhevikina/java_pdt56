package pack.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

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

  @Test
  public void testContactDeletion() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    app.getContactHelper().deleteContact(index);
    app.getNavigationHelper().gotoToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), index);
    before.remove(index);
    Assert.assertEquals(before,after);
  }
}
