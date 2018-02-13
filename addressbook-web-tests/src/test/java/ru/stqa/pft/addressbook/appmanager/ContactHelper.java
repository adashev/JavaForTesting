package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void initContactModification(int index) {
        wd.findElements(By.xpath(".//td[8]/a/img")).get(index).click();//
    }

    public void initContactModifiById(int id) { //нажатие на значок "карандаш" в нужной нам строке таблицы
        wd.findElement(By.cssSelector("input[value='"  + id + "']")).click();
        //wd.findElements(By.xpath(".//td[8]/a/img")).get(id).click();
    }

    public void updateContactCreation() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));//deleteSelectedContact переписана в рамках для выполнения задания 9
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact, boolean creation){
        fillContactForm (contact, creation);
        submitContactCreation();
    }

    public void modify(ContactData contact) {////////////////////////////////////////
        initContactModifiById(contact.getId());// нажатие на значок "карандаш" в нужной нам строке таблицы
        fillContactForm(contact, false);
        updateContactCreation();
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
        //app.goTo().homePage();
    }

    public void delete(int index) {
        selectContact(index);
        deleteSelectedContact();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
    }

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size(); //считает текущее число контактов на странице (создано в рамках в 4.3)
    }


    public void selectContact(int index) { //int index добавили по мотивам 4.4
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='"  + id + "']")).click();
    }

    public List<ContactData> list() {// создали по мотивам 4.5
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute ("value"));//по мотивам 4.7. Считываем id контакта
            // ищем один элемент внутри другого
            contacts.add(new ContactData().withId(id)
                    .withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }

    public Set<ContactData> all() {// создали по мотивам 5.5
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute ("value"));//по мотивам 4.7. Считываем id контакта
            // ищем один элемент внутри другого
            contacts.add(new ContactData().withId(id)
                    .withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }


}
