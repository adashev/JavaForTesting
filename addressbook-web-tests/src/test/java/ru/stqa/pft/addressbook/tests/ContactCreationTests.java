package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();//считываем текущий список контактов на страницы до создания нового (4.5)
        app.goTo().gotoAddContactPage();
        ContactData contact = new ContactData()
                .withFirstname("создание").withLastname("О").withGroup("gr1")
                .withAddress("Е, 1").withMobile("4-0").withEmail("@ru");
        app.contact().create(contact,true);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();//считываем текущий список контактов на страницы после создания нового (4.5)
        Assert.assertEquals(after.size(), before.size() + 1);//проверка кол-ва контактов после создания нового (в рамках 4.3)

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);// по мотивам 4.10
    }

}
