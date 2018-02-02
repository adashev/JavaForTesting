package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {  click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contact, boolean creation) {
        type(By.name("firstname"),contact.getFirstname());
        type(By.name("lastname"),contact.getLastname());

        if (creation){ //проверка карточки (создание или изменение). При изменении нет раскр.списка групп.Лекция 3.8
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroup());
            //выбираем значение в раскрыв. списке групп
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        type(By.name("address"),contact.getAddress());
        type(By.name("mobile"), contact.getMobile());
        type(By.name("email"), contact.getEmail());
    }

    public void initContactModification(int index) { //int index добавили по мотивам 4.4
        //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));//по мотивам 4.4 меняем на нижеследующую
        wd.findElements(By.xpath(".//td[8]/a/img")).get(index).click();//
    }

    public void updateContactCreation() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void deleteSelectedContact() {
        //click(By.xpath("//div[@id='content']/form[2]/input[2]")); // вариант удаления контакта через заход в его карточку
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));//deleteSelectedContact переписана в рамках для выполнения задания 9
        wd.switchTo().alert().accept();
    }

    public void createContact (ContactData contact, boolean creation){
        fillContactForm (contact, creation);
        submitContactCreation();
    }

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size(); //считает текущее число контактов на странице (создано в рамках в 4.3)
    }

    public void selectContact(int index) { //int index добавили по мотивам 4.4
        wd.findElements(By.name("selected[]")).get(index).click();//поменяли с wd.findElement_ по мотивам 4.4
//      wd.findElement(By.name("selected[]")).click();

    }


    public List<ContactData> getContactList() {// создали по мотивам 4.5
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            ContactData contact = new ContactData(firstname, lastname, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
