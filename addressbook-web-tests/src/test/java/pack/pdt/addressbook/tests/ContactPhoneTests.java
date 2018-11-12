package pack.pdt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pack.pdt.addressbook.model.ContactData;
import pack.pdt.addressbook.model.GroupData;
import pack.pdt.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      app.goTo().addNewContactPage();
      app.contact().create(new ContactData()
              .withFirstname("hello")
              .withLastname("hello")
              .withAddress("hello")
              .withHomePhone("8 495 555 55 55")
              .withMobilePhone("+7 922 222-22-22")
              .withWorkPhone("+7(495)003 0077")
              .withEmail("test@test.ee")
              .withEmail2("test@test.ru")
              .withEmail3("test@test.com")
              .inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }
}