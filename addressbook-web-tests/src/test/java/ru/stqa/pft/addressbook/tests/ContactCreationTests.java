package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount(); //кол-во контактов ДО создания нового (в рамках 4.3)
        app.getNavigationHelper().gotoAddContactPage();
        app.getContactHelper().createContact(new ContactData("К17", "О", "gr1", "Онеж, 1", "4-90", "m@m.ru"),true);
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount(); //кол-во контактов ПОСЛЕ создания нового (в рамках 4.3)
        Assert.assertEquals(after, before + 1);//проверка кол-ва контактов после создания нового (в рамках 4.3)

    }

}
