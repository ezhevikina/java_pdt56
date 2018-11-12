package pack.pdt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pack.pdt.addressbook.model.Contacts;
import pack.pdt.addressbook.model.ContactData;
import pack.pdt.addressbook.model.GroupData;

import java.util.List;


public class ContactHelper extends HelperBase {

  private Contacts contactCache = null;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void returnToHomePage() { click(By.linkText("home")); }

  public void fillContactPage(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
//       attach(By.name("photo"), contactData.getPhoto());


    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertEquals(1, contactData.getGroups().size());
      }
      new Select(wd.findElement(By.name("new_group")))
              .selectByVisibleText(contactData.getGroups()
                      .iterator().next().getName());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void selectAllContacts() {
    wd.findElement(By.cssSelector("input[id='MassCB']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void modify(ContactData contact) {
    editSelectedContactById(contact.getId());
    updateContact(contact);
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    submitContactDeletion();
    contactCache = null;
    returnToHomePage();
  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void editSelectedContactById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(ContactData contact) {
    fillContactPage(contact, true);
    submitContactCreation();
    contactCache = null;
  }

  public void updateContact(ContactData contact) {
    fillContactPage(contact, false);
    submitContactModification();
    contactCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String firstname = element.findElement(By.xpath("./td[3]")).getText();
      String lastname = element.findElement(By.xpath("./td[2]")).getText();
      String address = element.findElement(By.xpath("./td[4]")).getText();
      String allPhones = element.findElement(By.xpath("./td[6]")).getText();
      String allEmails = element.findElement(By.xpath("./td[5]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData()
              .withId(id)
              .withFirstname(firstname)
              .withLastname(lastname)
              .withAddress(address)
              .withAllPhones(allPhones)
              .withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }


  public ContactData infoFromEditForm(ContactData contact) {
    editSelectedContactById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String phone = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().
            withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHomePhone(phone)
            .withMobilePhone(mobile).withWorkPhone(work).withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);

  }

  public void addToGroup(ContactData contact, GroupData group) {
    selectGroupFromList("[all]", "group");
    selectContactById(contact.getId());
    new Select(wd.findElement(By.name("to_group"))).selectByValue(Integer.toString(group.getId()));
    click(By.name("add"));
    returnToHomePage();
  }

  public void selectGroupFromList(String group, String element) {
    new Select(wd.findElement(By.name(element))).selectByVisibleText(group);
  }

  public void removeFromGroup(ContactData contact, GroupData group) {
    selectGroupFromList("[all]", "group");
    selectGroupFromList(group.getName(), "group");
    selectAllContacts();
    click(By.name("remove"));
  }
}