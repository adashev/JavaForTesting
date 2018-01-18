package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereContact()){
        app.getNavigationHelper().gotoAddContactPage();
  app.getContactHelper().createContact(new ContactData("zz", "xx", "test1", "mm", "77-88", "do@r.ru"), true);
        app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().gotoHomePage();
  }

}
