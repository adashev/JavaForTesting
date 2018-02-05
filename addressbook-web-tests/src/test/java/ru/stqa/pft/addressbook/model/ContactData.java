package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String id; // добавлен по мотивам 4.7
    private final String firstname;
    private final String lastname;
    private String group;
    private final String address;
    private final String mobile;
    private final String email;

    public ContactData(String id, String firstname, String lastname, String group, String address, String mobile, String email) {
        this.id = id; // добавлен по мотивам 4.7
        this.firstname = firstname;
        this.lastname = lastname;
        this.group = group;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
    }

    public ContactData(String firstname, String lastname, String group, String address, String mobile, String email) {
        // второй конструктор (без id добавлен в 4.7)
        this.id = null;
        this.firstname = firstname;
        this.lastname = lastname;
        this.group = group;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
    }
    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
