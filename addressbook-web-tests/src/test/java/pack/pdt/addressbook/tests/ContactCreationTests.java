package pack.pdt.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.*;
import pack.pdt.addressbook.model.ContactData;
import pack.pdt.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType()); // means List<ContactData>.class
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testCreationContact(ContactData contact) throws Exception {
    Contacts before = app.contact().all();
    app.goTo().addNewContactPage();
    /*File photo = new File("src/test/resources/profilepicture.jpg");
    ContactData contact = new ContactData()
            .withFirstname("John")
            .withLastname("Doe")
            .withCompany("Software Testing Company")
            .withAddress("Moscow")
            .withWorkPhone("89000000001")
            .withEmail("johnd@stc.com")
            .withGroup("test1")
            .withPhoto(photo);*/
    app.contact().create(contact, true);

    assertEquals(app.contact().count(),before.size() + 1);
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact
            .withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
