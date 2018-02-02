package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        //int before = app.getContactHelper().getContactCount(); //кол-во контактов ДО создания нового (в рамках 4.3)
        List<ContactData> before = app.getContactHelper().getContactList();//считываем текущий список контактов на страницы до создания нового (4.5)
        app.getNavigationHelper().gotoAddContactPage();
        app.getContactHelper().createContact(new ContactData("create", "О", "gr1", "Еж, 1", "4-0", "m@ru"),true);
        app.getNavigationHelper().gotoHomePage();

        //int after = app.getContactHelper().getContactCount(); //кол-во контактов ПОСЛЕ создания нового (в рамках 4.3)
        List<ContactData> after = app.getContactHelper().getContactList();//считываем текущий список контактов на страницы после создания нового (4.5)
        Assert.assertEquals(after.size(), before.size() + 1);//проверка кол-ва контактов после создания нового (в рамках 4.3)

    }

}
