package pack.pdt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (! app.contact().exists()) {
      app.goTo().addNewContactPage();
      app.contact().create(new ContactData()
              .withFirstname("John").withEmail("johnd@stc.com").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
  app.goTo().homePage();
  ContactData contact = app.contact().all().iterator().next();
  ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

  assertThat(contact.getHomePhone(),equalTo(striped(contactInfoFromEditForm.getHomePhone())));
  assertThat(contact.getMobilePhone(),equalTo(striped(contactInfoFromEditForm.getMobilePhone())));
  assertThat(contact.getWorkPhone(),equalTo(striped(contactInfoFromEditForm.getWorkPhone())));
  }

  public String striped(String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }

}
