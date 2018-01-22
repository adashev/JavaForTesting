package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupModificationTests extends TestBase {
    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount(); //кол-во групп ДО модификации группы (добавлено в 4.3)
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test10", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1a", "test20", "test30"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnGroupPage();
        int after = app.getGroupHelper().getGroupCount(); //кол-во групп ПОСЛЕ модификации данной группы (добавлено в 4.3)
        Assert.assertEquals(after, before);//проверка итогового кол-ва групп (добавлено в 4.3)
    }
}
