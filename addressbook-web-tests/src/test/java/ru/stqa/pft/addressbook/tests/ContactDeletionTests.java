package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount(); //кол-во контактов ДО удаления (в рамках 4.3)
    if (! app.getContactHelper().isThereContact()){
        app.getNavigationHelper().gotoAddContactPage();
  app.getContactHelper().createContact(new ContactData("z", "x", "test1", "mm", "7-8", "d@ru"), true);
        app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount(); //кол-во контактов ПОСЛЕ удаления (в рамках 4.3)
    Assert.assertEquals(after, before - 1);//проверка кол-ва контактов после создания нового (в рамках 4.3)
  }

}
