package pack.pdt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (! app.contact().exists()) {
      app.goTo().addNewContactPage();
      app.contact().create(new ContactData()
              .withFirstname("John")
              .withLastname("Snow")
              .withEmail("johns@stc.com")
              .withEmail3("jj@fakemail.com")
              .withGroup("test1")
              .withHomePhone("+7 (495) 057-99-00")
              .withMobilePhone("8 900 000 0000")
              .withAddress("South Corner, 5"));
    }
  }

  @Test
  public void testContactAddress() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }
}
