package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
        if (creation){ //проверка карточки (создание или изменение). При изменении нет раскр.списка групп.Лекция 3.8
            if (contact.getGroup() != null) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroup()); //выбираем значение в раскр. списке групп
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        type(By.name("firstname"),contact.getFirstname());
        type(By.name("lastname"),contact.getLastname());
        type(By.name("address"),contact.getAddress());
        type(By.name("mobile"), contact.getMobilePhone());
        type(By.name("email"), contact.getEmail());
        attach(By.name("photo"), contact.getPhoto()); // -3^57
    }


    public void initContactModifiById(int id) { //нажатие на значок "карандаш" в нужной нам (id) строке таблицы
        // подробный разбор разных вариантов построения локатора в 5.9
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));//находим чекбокс нужной строки (по id)
        WebElement row = checkbox.findElement(By.xpath("./../.."));//поднимаемся на 2 уровня вверх: ячейка-строка(находим нужную строку)
        List<WebElement> cells = row. findElements(By.tagName("td"));// берем список всех ячеек найденной строки
        cells.get(7).findElement(By.tagName("a")).click ();//находим тэг a(гиперссылка) внутри 7-й ячейки
        //wd.findElement(By.cssSelector("a[href='" + "edit.php?id=" + id + "']")).click();
        //wd.findElement(By.xpath("//tbody/tr/td/input[@id='" + id + "']/../../td[8]")).click();

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

    public void modify(ContactData contact) {//////////
        initContactModifiById(contact.getId());// нажатие на значок "карандаш" в нужной нам строке таблицы
        fillContactForm(contact, false);
        updateContactCreation();
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
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

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='"  + id + "']")).click();
    }

    /*public List<ContactData> list() {// создали по мотивам 4.5
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
    }*/

    public Contacts all() {// создали по мотивам 5.5
        Contacts contacts = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            //String[] allPhones = cells.get(5).getText().split("\n");//в 5.10 "разрезаем" полученную строчку на части (на отдельные номера телефонов)
            // "\n" - регулярное ваыражение (шаблон поиска)
            //withAllPhones - добавлен в 5.11. В 5.10 было  .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
            String address = cells.get(3).getText();// для задания-11
            String allMails = cells.get(4).getText();// для задания-11

            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAllPhones(allPhones)
                    .withAddress(address)
                    .withAllMails(allMails));
            /*String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute ("value"));//по мотивам 4.7. Считываем id контакта
            // ищем один элемент внутри другого
            contacts.add(new ContactData().withId(id)
                    .withFirstname(firstname).withLastname(lastname));*/
        }
        return contacts;
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModifiById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();   //для Задания №11
        String email = wd.findElement(By.name("email")).getAttribute("value");   //для Задания №11
        String email2 = wd.findElement(By.name("email2")).getAttribute("value"); //для Задания №11
        String email3 = wd.findElement(By.name("email3")).getAttribute("value"); //для Задания №11

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }
}
