package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
      if (! app.getContactHelper().isThereContact()) {
          app.getNavigationHelper().gotoAddContactPage();
          app.getContactHelper().createContact(new ContactData("z", "x", "test1", "mm", "7-8", "d@ru"), true);
          app.getNavigationHelper().gotoHomePage();
      }
    //int before = app.getContactHelper().getContactCount(); //кол-во контактов ДО модифик. (в рамках 4.3)
    List<ContactData> before = app.getContactHelper().getContactList();//считываем список контактов на страницы до модиф.(4.5)
    app.getContactHelper().initContactModification(before.size() - 1);
    app.getContactHelper().fillContactForm(new ContactData("MOD", "MOD", null,"M.1", "5-9", "m@ru"), false);
    app.getContactHelper().updateContactCreation();
    app.getNavigationHelper().gotoHomePage();
    //int after = app.getContactHelper().getContactCount(); //кол-во контактов ПОСЛЕ модифик. (в рамках 4.3)
    List<ContactData> after = app.getContactHelper().getContactList();//считываем список контактов на странице после модиф. (4.5)
    Assert.assertEquals(after.size(), before.size());//проверка кол-ва контактов после модифик.(в рамках 4.3)
  }
}
