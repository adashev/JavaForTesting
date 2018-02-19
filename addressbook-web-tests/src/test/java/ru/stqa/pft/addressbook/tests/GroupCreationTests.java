package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();//считываем текущий список групп на странице до созданий новой группы (4.5)
        GroupData group = new GroupData().withName("gr1A");
        app.group().create(group);// null подставляем, т.к. хотим оставить в этих полях знач-я по умолч. (лекция 3.5)

        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();//считываем  список групп на странице после созданий новой группы (4.5)
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() {// добавили в 5.8
        app.goTo().groupPage();
        Groups before = app.group().all();//считываем текущий список групп на странице до созданий новой группы (4.5)
        GroupData group = new GroupData().withName("gr1A '");
        app.group().create(group);// null подставляем, т.к. хотим оставить в этих полях знач-я по умолч. (лекция 3.5)

        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();//считываем  список групп на странице после созданий новой группы (4.5)
        assertThat(after, equalTo(before));
    }

}
