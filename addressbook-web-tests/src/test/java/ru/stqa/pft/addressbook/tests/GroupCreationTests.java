package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider // загружаем данные (6.4, 6.5)
    public Iterator<Object[]> validGroups() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))){
            String xml = ""; // в 6.6
            String line = reader.readLine();
            while (line != null) {// в 6.6
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();// в 6.6
            xstream.processAnnotations(GroupData.class);// в 6.6
            List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);// в 6.6
            return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();// в 6.6
        }
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {

        app.goTo().groupPage();
        Groups before = app.group().all();//считываем текущий список групп на странице до созданий новой группы (4.5)
        app.group().create(group);// null подставляем, т.к. хотим оставить в этих полях знач-я по умолч. (лекция 3.5)

        Groups after = app.group().all();//считываем  список групп на странице после созданий новой группы (4.5)

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

}
