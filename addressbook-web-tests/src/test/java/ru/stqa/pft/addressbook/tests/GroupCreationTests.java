package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount(); //кол-во групп ДО создания новой данной группы (добавлено в 4.3)
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));// null подставляем, т.к. что хотим оставить в этих полях знач-я по умолч. (лекция 3.5)
        int after = app.getGroupHelper().getGroupCount(); //кол-во групп ПОСЛЕ создания новой данной группы (добавлено в 4.3)
        Assert.assertEquals(after, before + 1);//проверка нового кол-ва групп (добавлено в 4.3)
    }

}
