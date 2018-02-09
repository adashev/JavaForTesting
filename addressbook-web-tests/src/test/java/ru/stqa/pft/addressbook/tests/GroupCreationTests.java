package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();

        List<GroupData> before = app.group().list();//считываем текущий список групп на странице до созданий новой группы (4.5)
        //int before = app.group().getGroupCount(); //кол-во групп ДО создания новой данной группы (4.3)

        GroupData group = new GroupData("gr1", null, null);//вынесли из create() в 4.8
        app.group().create(group);// null подставляем, т.к. хотим оставить в этих полях знач-я по умолч. (лекция 3.5)

        //int after = app.group().getGroupCount(); //кол-во групп ПОСЛЕ создания новой данной группы (4.3)

        List<GroupData> after = app.group().list();//считываем  список групп на странице после созданий новой группы (4.5)
        Assert.assertEquals(after.size(), before.size() + 1);//проверка нового кол-ва групп (4.3; в 4.5 int поменяли на списки)

        /*int max = 0;
        for (GroupData g : after){ //в 4.8 добавили цикл поиска максимального id в списке групп ПОСЛЕ добавления новой
            if (g.getId() > max){
                max = g.getId();
            }
        }*/

        //в 4.9 добавили компоратор (сравниватель). Компоратор - описание правил сравненеия объектов
        //Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    //int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();//в 4.9 добавили это вычисление макс-ого объекта
    //и получение его идентификатора
        //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());// присв-ем найденный max в кач. инденф-ра новой группы
        before.add(group);//добавлено в 4.8 - добавляем в список before созданную группу

        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());//в 4.10 учимся сортировать группы по их id
        before.sort(byId);//в 4.10
        after.sort(byId);//в 4.10
        //Assert.assertEquals(new HashSet(before), new HashSet(after));//сверка списков групп(добавлено в 4.8)
        Assert.assertEquals(before, after);//в 4.10 отказались от HashSet (приведение списка ко множеству), т.к. списки отсортированы по id
    }

}
