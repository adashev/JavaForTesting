package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactModificationTests extends TestBase {
  @BeforeMethod //Добавили в 5.2
  public void ensurePreconditions(){
    app.goTo().homePage();// переименовка методов сделана в 5.3
    if (app.contact().all().size() == 0) {
      app.goTo().gotoAddContactPage();
      app.contact().create(new ContactData().withFirstname("создаем").withLastname("x").withGroup("gr1")
              .withGroup("test1").withAddress("Adres")
              .withMobile("7-8").withEmail("d@ru"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();//считываем список контактов на страницы до модиф.
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstname("14февраля").withLastname("x1").withGroup("gr1")
            .withAddress("M.1").withMobile("59").withEmail("@ru");

    app.contact().modify(contact);

    Set<ContactData> after = app.contact().all();//считываем список контактов на странице после модиф.

    before.remove(modifiedContact);//удаляем элемент модифицированный через UI из нашего списка (4.7)
    before.add(contact);//вместо удаленного элемента добавляем новый модифицированный (4.7)
    Assert.assertEquals(before, after);
  }
}
