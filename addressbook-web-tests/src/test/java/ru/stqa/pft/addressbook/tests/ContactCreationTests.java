package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {
    @Test(enabled = false)
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        //int before = app.getContactHelper().getContactCount(); //кол-во контактов ДО создания нового (в рамках 4.3)
        List<ContactData> before = app.getContactHelper().getContactList();//считываем текущий список контактов на страницы до создания нового (4.5)
        app.getNavigationHelper().gotoAddContactPage();

        ContactData contact = new ContactData("creat8", "О", "gr1", "Еж, 1", "4-0", "m@ru");//в 4.8
        app.getContactHelper().createContact(contact,true);
        app.getNavigationHelper().gotoHomePage();

        //int after = app.getContactHelper().getContactCount(); //кол-во контактов ПОСЛЕ создания нового (в рамках 4.3)
        List<ContactData> after = app.getContactHelper().getContactList();//считываем текущий список контактов на страницы после создания нового (4.5)
        Assert.assertEquals(after.size(), before.size() + 1);//проверка кол-ва контактов после создания нового (в рамках 4.3)


        /*int max = 0;
        for (ContactData g : after){ //в 4.8 добавили цикл поиска максимального id в списке контактов ПОСЛЕ добавления нового
            if (g.getId() > max){
                max = g.getId();
            }
        }*/
        //добавлено по мотивам 4.9. Создаем объект анонимного класса
        //Comparator<? super ContactData> byId = (Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        //int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId(); //добавлено по мотивам 4.9. Исполь-ем анонимную функцию

        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()); //добавлено по мотивам 4.8
        //after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId() - лямда-выражение реализовано по мотивам 4.9
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());// по мотивам 4.10
        before.sort(byId);// по мотивам 4.10
        after.sort(byId);// по мотивам 4.10
        Assert.assertEquals(before, after);// по мотивам 4.10
        //Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));//сверка списков после добавл. контакта(по мотивам 4.7, 4.8)
        // с помощью HashSet<Object> преобразуем наши списки во множетсва, дабы не морочиться с порядком элементов в списке (по мотивам 4.8)

    }

}
