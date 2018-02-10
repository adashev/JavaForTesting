package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

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

        //int before = app.group().getGroupCount(); //кол-во групп ДО модификации группы (добавлено 4.3; в 4.5 перенесено после проверки предусловий)
        List<GroupData> before = app.group().list();//считываем текущий список групп на странице до удаления группы (4.5)
        int index = before.size() - 1;
        GroupData group = new GroupData()
                .withId(before.get(index).getId()).withName("test1").withHeader("tMOD").withFooter("tMOD");

        app.group().modify(index, group);//свели 5 методов в один в 5.2

        //int after = app.group().getGroupCount(); //кол-во групп ПОСЛЕ модификации данной группы (добавлено в 4.3)
        List<GroupData> after = app.group().list();//считываем  список групп на странице после созданий новой группы (4.5)
        Assert.assertEquals(after.size(), before.size());//проверка итогового кол-ва групп (добавлено в 4.3)

        before.remove(index);//удаляем элемент модифицированный через UI из нашего списка (4.7)
        before.add(group);//вместо удаленного элемента добавляем новый модифицированный (4.7)

        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());//в 4.10 учимся сортировать группы по их id
        before.sort(byId);//в 4.10
        after.sort(byId);//в 4.10
        //Assert.assertEquals(new HashSet(before), new HashSet(after));//сверка списков групп(добав. в 4.8). HashSet для преобразования списка во множество добавлен в начале 4.7
        Assert.assertEquals(before, after);//в 4.10 отказались от HashSet, т.к. списки отсортированы по id
    }
}
