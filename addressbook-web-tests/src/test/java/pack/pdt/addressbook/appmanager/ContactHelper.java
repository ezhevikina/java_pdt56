package pack.pdt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pack.pdt.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("company"),contactData.getCompany());
    type(By.name("address"),contactData.getAddress());
    type(By.name("work"),contactData.getWorkPhone());
    type(By.name("email"),contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }
  // TODO: add 0 groups case

  public void initEdition(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void submitContactModification() {
    click(By.xpath("//input[@name='update'][2]"));
  }

  public void showDetails(int index) {
    wd.findElements(By.xpath("//img[@alt='Details']")).get(index).click();
  }

  public void initModification() {
    click(By.xpath("//input[@name='modifiy']"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public boolean exists() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create(ContactData contactData, boolean creation) {
    fillContactForm(contactData,creation);
    submitContactCreation();
    returnToHomePage();
  }

  public void delete(int index) {
    selectContact(index);
    deleteContact();
    submitContactDeletion();
  }

  public void update(int index, ContactData contact) {
    fillContactForm(contact,false);
    submitContactModification();
    returnToHomePage();
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String firstname = element.findElement(By.xpath("./td[3]")).getText();
      String lastname = element.findElement(By.xpath("./td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, firstname, lastname, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
