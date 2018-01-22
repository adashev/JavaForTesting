package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount(); //кол-во групп ДО удаления группы (добавлено в 4.3)
        if (! app.getGroupHelper().isThereAGroup()){//проверка: если ни одной группы нет, то создать группу
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnGroupPage();
        int after = app.getGroupHelper().getGroupCount(); //кол-во групп ПОСЛЕ удаления данной группы (добавлено в 4.3)
        Assert.assertEquals(after, before - 1);//проверка нового кол-ва групп (добавлено в 4.3)
    }

}
