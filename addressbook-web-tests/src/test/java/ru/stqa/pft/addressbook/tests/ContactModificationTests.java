package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
      int before = app.getContactHelper().getContactCount(); //кол-во контактов ДО модифик. (в рамках 4.3)
      if (! app.getContactHelper().isThereContact()) {
          app.getNavigationHelper().gotoAddContactPage();
          app.getContactHelper().createContact(new ContactData("z", "x", "test1", "mm", "7-8", "d@ru"), true);
          app.getNavigationHelper().gotoHomePage();
      }
      
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Ол", "А", null,"M.1", "55 88 99", "m@g.ru"), false);
    app.getContactHelper().updateContactCreation();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount(); //кол-во контактов ПОСЛЕ модифик. (в рамках 4.3)
    Assert.assertEquals(after, before);//проверка кол-ва контактов после модифик.(в рамках 4.3)
  }
}
