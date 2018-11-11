package pack.pdt.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pack.pdt.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("json")){
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unknown file format" + format);
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("Arnold the %s", i))
              .withLastname(String.format("His Majesty"))
              .withPhoto(new File("src/test/resources/profilepicture.jpg"))
              .withMobilePhone("+7(925)0007689")
              .withHomePhone("(495)5557638")
              .withWorkPhone("89000004400")
              .withAddress("Soho Garden, London")
              .withCompany("Juno")
              .withEmail("hisemail@fakemail.com")
              .withEmail2("hissecondemail@fakemail.com")
              .withGroup("test 1"));
    }
    return contacts;
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    //TODO: try to Exclude fields and objects based on a particular annotation
    // https://google.github.io/gson/apidocs/com/google/gson/ExclusionStrategy.html
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }
}
