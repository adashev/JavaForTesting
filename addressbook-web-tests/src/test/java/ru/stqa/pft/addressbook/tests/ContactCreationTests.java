package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        //int before = app.getContactHelper().getContactCount(); //кол-во контактов ДО создания нового (в рамках 4.3)
        List<ContactData> before = app.getContactHelper().getContactList();//считываем текущий список контактов на страницы до создания нового (4.5)
        app.getNavigationHelper().gotoAddContactPage();

        ContactData contact = new ContactData("creat", "О", "gr1", "Еж, 1", "4-0", "m@ru");//в 4.8
        app.getContactHelper().createContact(contact,true);
        app.getNavigationHelper().gotoHomePage();

        //int after = app.getContactHelper().getContactCount(); //кол-во контактов ПОСЛЕ создания нового (в рамках 4.3)
        List<ContactData> after = app.getContactHelper().getContactList();//считываем текущий список контактов на страницы после создания нового (4.5)
        Assert.assertEquals(after.size(), before.size() + 1);//проверка кол-ва контактов после создания нового (в рамках 4.3)


        int max = 0;
        for (ContactData g : after){ //в 4.8 добавили цикл поиска максимального id в списке контактов ПОСЛЕ добавления нового
            if (g.getId() > max){
                max = g.getId();
            }
        }
        contact.setId(max); //добавлено по мотивам 4.8
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));//сверка списков после добавл. нового контакта(по мотивам 4.7, 4.8)
        // с помощью HashSet<Object> преобразуем наши списки во множетсва, дабы не морочиться с порядком элементов в списке (по мотивам 4.8)

    }

}
