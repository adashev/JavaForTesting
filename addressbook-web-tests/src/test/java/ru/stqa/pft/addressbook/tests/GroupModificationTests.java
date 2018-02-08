package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {
    @BeforeMethod //Добавили в 5.2
    public void ensurePreconditions(){
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
    }
    @Test
    public void testGroupModification() {

        //int before = app.getGroupHelper().getGroupCount(); //кол-во групп ДО модификации группы (добавлено 4.3; в 4.5 перенесено после проверки предусловий)
        List<GroupData> before = app.getGroupHelper().getGroupList();//считываем текущий список групп на странице до удаления группы (4.5)
        int index = before.size() - 1;
        GroupData group = new GroupData(before.get(index).getId(), "test1", "testMOD", "testMOD");// в 4.7 вынесли new GroupData из fillGroupForm

        app.getGroupHelper().modifyGroup(index, group);//свели 5 методов в один в 5.2

        //int after = app.getGroupHelper().getGroupCount(); //кол-во групп ПОСЛЕ модификации данной группы (добавлено в 4.3)
        List<GroupData> after = app.getGroupHelper().getGroupList();//считываем  список групп на странице после созданий новой группы (4.5)
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
