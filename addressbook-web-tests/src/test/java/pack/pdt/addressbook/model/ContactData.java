package pack.pdt.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String company;
  private final String address;
  private final String workPhone;
  private final String email;
  private String group;

  public ContactData(String firstname, String lastname, String company, String address, String workPhone, String email, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.company = company;
    this.address = address;
    this.workPhone = workPhone;
    this.email = email;
    this.group = group;
  }

  public String getFirstname() { return firstname; }

  public String getLastname() { return lastname; }

  public String getCompany() { return company; }

  public String getAddress() {
    return address;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() { return group; }
}
