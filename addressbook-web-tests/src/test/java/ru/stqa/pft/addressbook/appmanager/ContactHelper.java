package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {  click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contact, boolean creation) {
        type(By.name("firstname"),contact.getFirstname());
        type(By.name("lastname"),contact.getLastname());

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroup());
        } else {
            Assert.assertFalse(isEementPresent(By.name("new_group")));
        }

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
