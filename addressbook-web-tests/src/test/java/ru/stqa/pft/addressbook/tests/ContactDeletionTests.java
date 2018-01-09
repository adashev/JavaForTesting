package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getContactHelper().initContactModification();
    app.getContactHelper().deleteSelectedContact();
  }

}
