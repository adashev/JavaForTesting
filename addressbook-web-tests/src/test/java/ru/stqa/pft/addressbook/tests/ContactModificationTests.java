package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
  @BeforeMethod //Добавили в 5.2
  public void ensurePreconditions(){
    app.goTo().homePage();// переименовка методов сделана в 5.3
    if (app.contact().all().size() == 0) {
      app.goTo().gotoAddContactPage();
      app.contact().create(new ContactData().withFirstname("создаем").withLastname("x").withGroup("gr1")
              .withGroup("test1").withAddress("Adres")
              .withMobilePhone("7-8").withEmail("d@ru"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();//считываем список контактов на страницы до модиф.
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstname("14февраля").withLastname("x1").withGroup("gr1")
            .withAddress("M.1").withMobilePhone("59").withEmail("@ru");

    app.contact().modify(contact);

    Contacts after = app.contact().all();//считываем список контактов на странице после модиф.

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    // -1:33
  }
}
