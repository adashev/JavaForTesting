package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereContact()){
        app.getNavigationHelper().gotoAddContactPage();
  app.getContactHelper().createContact(new ContactData("z", "x", "test1", "mm", "7-8", "d@ru"), true);
        app.getNavigationHelper().gotoHomePage();
    }
    //int before = app.getContactHelper().getContactCount(); //кол-во контактов ДО удаления (в рамках 4.3)
    List<ContactData> before = app.getContactHelper().getContactList();//считываем список контактов на страницы до удаления(4.5)
    //app.getContactHelper().initContactModification(); //вариант удаления контакта через заход в его карточку

    app.getContactHelper().selectContact(before.size() - 1);//вариант удаления контакта со списка контактов
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().gotoHomePage();
    //int after = app.getContactHelper().getContactCount(); //кол-во контактов ПОСЛЕ удаления (в рамках 4.3)
    List<ContactData> after = app.getContactHelper().getContactList();//считываем список контактов на странице после удаления (4.5)
    Assert.assertEquals(after.size(), before.size() - 1);//проверка кол-ва контактов после создания нового (в рамках 4.3)
  }

}
