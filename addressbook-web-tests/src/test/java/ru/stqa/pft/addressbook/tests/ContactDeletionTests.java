package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod //Добавили в 5.2
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.goTo().gotoAddContactPage();
      app.contact().create(new ContactData().withFirstname("z").withLastname("x")
              .withGroup("test1").withAddress("Addres").withMobile("7-8").withEmail("d@ru"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactDeletion() {
    //int before = app.contact().getContactCount(); //кол-во контактов ДО удаления (в рамках 4.3)
    List<ContactData> before = app.contact().list();//считываем список контактов на страницы до удаления(4.5)
    //app.contact().initContactModification(); //вариант удаления контакта через заход в его карточку

    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().homePage();
    //int after = app.contact().getContactCount(); //кол-во контактов ПОСЛЕ удаления (в рамках 4.3)
    List<ContactData> after = app.contact().list();//считываем список контактов на странице после удаления (4.5)
    Assert.assertEquals(after.size(), before.size() - 1);//проверка кол-ва контактов после создания нового (в рамках 4.3)

    before.remove(index);
      //for (int i = 0; i < after.size(); i++) { //добавили в 4.6 последовательную сверку всех элементов списков before и after
      //хотим убедиться, что списки полностью одинаковы
    Assert.assertEquals(before, after);
      //}
  }
}
