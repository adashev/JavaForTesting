package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {  click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contact) {
        type(By.name("firstname"),contact.getFirstname());
        type(By.name("lastname"),contact.getLastname());
        type(By.name("nickname"),contact.getNickname());
        type(By.name("address"),contact.getAddress());
        type(By.name("mobile"), contact.getMobile());
        type(By.name("email"), contact.getEmail());
    }

    public void initContactModification() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void updateContactCreation() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }
}
