package pack.pdt.addressbook.model;

import java.util.Objects;

public class ContactData {

  private int id;
  private final String firstname;
  private final String lastname;
  private final String company;
  private final String address;
  private final String workPhone;
  private final String email;
  private final String group;

  public ContactData(String firstname, String lastname, String company, String address, String workPhone, String email, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.company = company;
    this.address = address;
    this.workPhone = workPhone;
    this.email = email;
    this.group = group;
  }

  public ContactData(int id, String firstname, String lastname, String company, String address, String workPhone, String email, String group) {
    this.id = id;
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

  public int getId() { return id; }

  public void setId(int id) { this.id = id; }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }
}
