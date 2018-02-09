package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.List;

public class GroupDeletionTests extends TestBase {
    @BeforeMethod //Добавили в 5.2
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.group().list();//считываем текущий список групп на странице до удаления группы (4.5)
        //int before = app.group().getGroupCount(); //кол-во групп ДО удаления группы (добавлено 4.3; в 4.5 перенесено после проверки предусловий)
        int index = before.size() - 1;

        app.group().delete(index);
        //int after = app.group().getGroupCount(); //кол-во групп ПОСЛЕ удаления данной группы (добавлено в 4.3)
        List<GroupData> after = app.group().list();//считываем  список групп на странице после созданий новой группы (4.5)
        Assert.assertEquals(after.size(), before.size() - 1);//проверка нового кол-ва групп (добавлено в 4.3)

        before.remove(index); //добавили в 4.6 удаление удаленного элемента из списка before
        //for (int i = 0; i < after.size(); i++){ //добавили в 4.6 последовательную сверку всех элементов списков before и after
            // хотим убедиться, что списки полностью одинаковы
        //Assert.assertEquals(before.get(i), after.get(i));
        Assert.assertEquals(before, after);// к этой форме пришли в конце 4.6
        }

}

