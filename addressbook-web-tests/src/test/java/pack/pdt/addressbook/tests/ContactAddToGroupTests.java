package pack.pdt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.ContactData;
import pack.pdt.addressbook.model.GroupData;
import pack.pdt.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class ContactAddToGroupTests extends TestBase {

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
  public void testAddContactToGroup() {
    ContactData contact = app.db().contacts().iterator().next();
    Groups allGroups = app.db().groups();
    GroupData addedGroup = allGroups.iterator().next();
    if (allGroups.equals(contact.getGroups())) {
      app.contact().removeFromGroup(contact, addedGroup);
    }
    allGroups.removeAll(contact.getGroups());
    app.goTo().homePage();
    app.contact().addToGroup(contact, addedGroup);
    app.db().refresh(contact);
    assertThat(contact.getGroups(), hasItem(addedGroup));
  }
}
