package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @BeforeMethod //Добавили в 5.2
  public void ensurePreconditions(){
    app.goTo().homePage();// переименовка методов сделана в 5.3
    if (app.contact().list().size() == 0) {
      app.goTo().gotoAddContactPage();
      app.contact().create(new ContactData().withFirstname("создаем").withLastname("x")
              .withGroup("test1").withAddress("Adres")
              .withMobile("7-8").withEmail("d@ru"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {
    //int before = app.contact().getContactCount(); //кол-во контактов ДО модифик. (в рамках 4.3)
    List<ContactData> before = app.contact().list();//считываем список контактов на страницы до модиф.(4.5)
    int index = before.size() - 1;
    ContactData contact = new ContactData().withId(before.get(index).getId())
            .withFirstname("изменяем").withLastname("x1").withAddress("M.1").withMobile("59").withEmail("@ru");

    app.contact().modify(index, contact);

    //int after = app.contact().getContactCount(); //кол-во контактов ПОСЛЕ модифик. (в рамках 4.3)
    List<ContactData> after = app.contact().list();//считываем список контактов на странице после модиф. (4.5)
    Assert.assertEquals(after.size(), before.size());//проверка кол-ва контактов после модифик.(в рамках 4.3)
    before.remove(index);//удаляем элемент модифицированный через UI из нашего списка (4.7)
    before.add(contact);//вместо удаленного элемента добавляем новый модифицированный (4.7)

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());// по мотивам 4.10
    before.sort(byId);// по мотивам 4.10
    after.sort(byId);// по мотивам 4.10
    Assert.assertEquals(before, after);// по мотивам 4.10
    //Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));//сверка циклов после модифик.(по мотивам 4.7)
    // с помощью HashSet<Object> преобразуем наши списки во множетсва, дабы не морочиться с порядком элементов в списке
  }
}
