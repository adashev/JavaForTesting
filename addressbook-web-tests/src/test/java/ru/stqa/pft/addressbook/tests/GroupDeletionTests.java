package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.List;

public class GroupDeletionTests extends TestBase {
    @BeforeMethod //Добавили в 5.2
    public void ensurePreconditions(){
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.getGroupHelper().getGroupList();//считываем текущий список групп на странице до удаления группы (4.5)
        //int before = app.getGroupHelper().getGroupCount(); //кол-во групп ДО удаления группы (добавлено 4.3; в 4.5 перенесено после проверки предусловий)
        app.getGroupHelper().selectGroup(before.size() - 1);//параметр before - 1 добавляем в 4.4, чтобы удалять не первую, а последнюю группу в списке
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnGroupPage();
        //int after = app.getGroupHelper().getGroupCount(); //кол-во групп ПОСЛЕ удаления данной группы (добавлено в 4.3)
        List<GroupData> after = app.getGroupHelper().getGroupList();//считываем  список групп на странице после созданий новой группы (4.5)
        Assert.assertEquals(after.size(), before.size() - 1);//проверка нового кол-ва групп (добавлено в 4.3)

        before.remove(before.size() - 1); //добавили в 4.6 удаление удаленного элемента из списка before
        //for (int i = 0; i < after.size(); i++){ //добавили в 4.6 последовательную сверку всех элементов списков before и after
            // хотим убедиться, что списки полностью одинаковы
        //Assert.assertEquals(before.get(i), after.get(i));
        Assert.assertEquals(before, after);// к этой форме пришли в конце 4.6
        }

    }

