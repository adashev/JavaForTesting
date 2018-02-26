package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        File photo = new File("src/test/resources/440796.jpg");// добавлено в 6.1
        Contacts before = app.contact().all();//считываем текущий список контактов на страницы до создания нового (4.5)
        app.goTo().gotoAddContactPage();
        ContactData contact = new ContactData()
                .withFirstname("с фото").withLastname("О").withPhoto(photo)
                .withAddress("Е, 1").withMobile("4-0").withEmail("@ru");
        app.contact().create(contact,true);
        app.goTo().homePage();

        Contacts after = app.contact().all();//считываем текущий список контактов на страницы после создания нового (4.5)

        assertThat(after.size(), equalTo(before.size() + 1));

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()); // присваиваем контакту нужный id
        assertThat(after, equalTo(before.withAdded(contact)));
        //(after, equalTo(before.withAdded();
    }

    /*@Test // экспиремент в 6.1
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/440796.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }*/
}
