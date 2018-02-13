package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();//считываем текущий список групп на странице до созданий новой группы (4.5)
        GroupData group = new GroupData().withName("gr1A");
        app.group().create(group);// null подставляем, т.к. хотим оставить в этих полях знач-я по умолч. (лекция 3.5)
        Set<GroupData> after = app.group().all();//считываем  список групп на странице после созданий новой группы (4.5)
        Assert.assertEquals(after.size(), before.size() + 1);//проверка нового кол-ва групп (4.3; в 4.5 int поменяли на списки)

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);//добавлено в 4.8 - добавляем в список before созданную группу
        Assert.assertEquals(before, after);//в 4.10 отказались от HashSet (приведение списка ко множеству), т.к. списки отсортированы по id
    }

}
