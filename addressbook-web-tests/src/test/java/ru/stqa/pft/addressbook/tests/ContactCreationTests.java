package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @DataProvider // загружаем данные (6.4)
    public Iterator<Object[]> validContacts() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))){
            String xml = ""; // в 6.6
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();// в 6.6
            xstream.processAnnotations(ContactData.class);// в 6.6
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);// в 6.6
            //return list.iterator();
            return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();// в 6.6;
        }
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        app.goTo().homePage();
        //File photo = new File("src/test/resources/440796.jpg");// добавлено в 6.1
        Contacts before = app.contact().all();//считываем текущий список контактов на страницы до создания нового (4.5)
        app.goTo().gotoAddContactPage();

        //ContactData contact = new ContactData().withFirstname(firstname).withLastname(lastname).withAddress(address);
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
