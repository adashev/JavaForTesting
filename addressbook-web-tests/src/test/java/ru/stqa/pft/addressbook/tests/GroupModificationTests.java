package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase {
    @BeforeMethod //Добавили в 5.2
    public void ensurePreconditions(){
        app.goTo().groupPage(); // вместо .getNavigationHelper().gotoGroupPage() в 5.3
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }
    @Test
    public void testGroupModification() {

        Set<GroupData> before = app.group().all();//считываем текущий список групп на странице до удаления группы (4.5)
        GroupData modifiedGroup = before.iterator().next(); // получим первый попавшийся элемент множества
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("tMOD").withFooter("tMOD");

        app.group().modify(group);//свели 5 методов в один в 5.2

        //int after = app.group().getGroupCount(); //кол-во групп ПОСЛЕ модификации данной группы (добавлено в 4.3)
        Set<GroupData> after = app.group().all();//считываем  список групп на странице после созданий новой группы (4.5)
        Assert.assertEquals(after.size(), before.size());//проверка итогового кол-ва групп (добавлено в 4.3)

        before.remove(modifiedGroup);//удаляем элемент модифицированный через UI из нашего списка (4.7)
        before.add(group);//вместо удаленного элемента добавляем новый модифицированный (4.7)

        Assert.assertEquals(before, after);//в 4.10 отказались от HashSet, т.к. списки отсортированы по id
    }
}
