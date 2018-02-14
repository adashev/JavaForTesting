package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod //Добавили в 5.2
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().gotoAddContactPage();
      app.contact().create(new ContactData().withFirstname("z").withLastname("x")
              .withGroup("test1").withAddress("Addres").withMobile("7-8").withEmail("d@ru"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactDeletion() {
    Set<ContactData> before = app.contact().all();//считываем список контактов на страницы до удаления(4.5)
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();//считываем список контактов на странице после удаления (4.5)
    Assert.assertEquals(after.size(), before.size() - 1);//проверка кол-ва контактов после создания нового (в рамках 4.3)

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }
}
