package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
      if (! app.getContactHelper().isThereContact()) {
          app.getNavigationHelper().gotoAddContactPage();
          app.getContactHelper().createContact(new ContactData("zz", "xx", "test1", "mm", "77-8", "d@r.ru"), true);
          app.getNavigationHelper().gotoHomePage();
      }
      
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Ол", "А", null,"M.1", "55 88 99", "m@g.ru"), false);
    app.getContactHelper().updateContactCreation();
  }
}
