package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.goTo().homePage();

        //int before = app.contact().getContactCount(); //кол-во контактов ДО создания нового (в рамках 4.3)
        List<ContactData> before = app.contact().list();//считываем текущий список контактов на страницы до создания нового (4.5)
        app.goTo().gotoAddContactPage();
        ContactData contact = new ContactData()
                .withFirstname("создаем").withLastname("О").withGroup("gr1")
                .withAddress("Е, 1").withMobile("4-0").withEmail("@ru");
        app.contact().create(contact,true);
        app.goTo().homePage();
        //int after = app.contact().getContactCount(); //кол-во контактов ПОСЛЕ создания нового (в рамках 4.3)
        List<ContactData> after = app.contact().list();//считываем текущий список контактов на страницы после создания нового (4.5)
        Assert.assertEquals(after.size(), before.size() + 1);//проверка кол-ва контактов после создания нового (в рамках 4.3)

        contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()); //добавлено по мотивам 4.8
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
