package pack.pdt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.Contacts;
import pack.pdt.addressbook.model.GroupData;
import pack.pdt.addressbook.model.Groups;
import pack.pdt.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.db().contacts().size() == 0){
      Groups groups = app.db().groups();
      app.goTo().addNewContactPage();
      app.contact().create(new ContactData()
              .withFirstname("John")
              .withLastname("Doe")
              .withAddress("Moscow")
              .withHomePhone("+7 (495) 057-99-00")
              .withMobilePhone("8 900 000 0000")
              .withWorkPhone("8 496 333 33 33")
              .withEmail("test@test.ee")
              .withEmail2("test@test.ru")
              .withEmail3("test@test.com")
              .inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("John")
            .withLastname("Modified")
            .withAddress("Brooklyn")
            .withHomePhone("+7 (000) 057-99-00")
            .withMobilePhone("8 900 555 0000")
            .withWorkPhone("8 496 3353 33 33")
            .withEmail("test@fakemail.com")
            .withEmail2("test@test.ru")
            .withEmail3("test@test.il");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactsListInUI();
  }
}