package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {
    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();// случайным образом выбираем какой-то контакт
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }

}