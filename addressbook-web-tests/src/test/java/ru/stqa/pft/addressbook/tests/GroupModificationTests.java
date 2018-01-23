package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupModificationTests extends TestBase {
    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();

        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test10", null, null));
        }
        //int before = app.getGroupHelper().getGroupCount(); //кол-во групп ДО модификации группы (добавлено 4.3; в 4.5 перенесено после проверки предусловий)
        List<GroupData> before = app.getGroupHelper().getGroupList();//считываем текущий список групп на странице до удаления группы (4.5)
        app.getGroupHelper().selectGroup(before.size() - 1);//параметр before - 1 добавляем в 4.4, чтобы изменять не первую, а последнюю группу в списке
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test-A", "test20", "test30"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnGroupPage();
        //int after = app.getGroupHelper().getGroupCount(); //кол-во групп ПОСЛЕ модификации данной группы (добавлено в 4.3)
        List<GroupData> after = app.getGroupHelper().getGroupList();//считываем  список групп на странице после созданий новой группы (4.5)
        Assert.assertEquals(after.size(), before.size());//проверка итогового кол-ва групп (добавлено в 4.3)
    }
}
