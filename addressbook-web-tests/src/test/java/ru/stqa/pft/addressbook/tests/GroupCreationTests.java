package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();//считываем текущий список групп на странице до созданий новой группы (4.5)
        //int before = app.getGroupHelper().getGroupCount(); //кол-во групп ДО создания новой данной группы (4.3)

        GroupData group = new GroupData("test3", null, null);//вынесли из createGroup() в 4.8
        app.getGroupHelper().createGroup(group);// null подставляем, т.к. хотим оставить в этих полях знач-я по умолч. (лекция 3.5)
        //int after = app.getGroupHelper().getGroupCount(); //кол-во групп ПОСЛЕ создания новой данной группы (4.3)

        List<GroupData> after = app.getGroupHelper().getGroupList();//считываем  список групп на странице после созданий новой группы (4.5)
        Assert.assertEquals(after.size(), before.size() + 1);//проверка нового кол-ва групп (4.3; в 4.5 int поменяли на списки)

        int max = 0;
        for (GroupData g : after){ //в 4.8 добавили поиск максимального id (ищем группу, кот. только что добавили)
            if (g.getId() > max){
                max = g.getId();
            }
        }
        group.setId(max);// присванваем найденный max в качестве индентификатора новой группы
        before.add(group);//добавлено в 4.8 - добавляем в список before созданную группу
        Assert.assertEquals(new HashSet(before), new HashSet(after));//сверка списков групп(добавлено в 4.8)
    }

}
