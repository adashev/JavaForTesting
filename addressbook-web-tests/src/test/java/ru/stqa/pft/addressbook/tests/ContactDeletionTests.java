package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod //Добавили в 5.2
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().gotoAddContactPage();
      app.contact().create(new ContactData().withFirstname("z").withLastname("x")
              .withGroup("test1").withAddress("Addres").withMobilePhone("7-8").withEmail("d@ru"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = (Contacts) app.contact().all();//считываем список контактов на страницы до удаления(4.5)
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();

    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = (Contacts) app.contact().all();//считываем список контактов на странице после удаления (4.5)
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
