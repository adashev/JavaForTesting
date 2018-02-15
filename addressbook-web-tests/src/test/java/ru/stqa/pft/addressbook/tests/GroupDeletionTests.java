package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() {
        Groups before = app.group().all();//считываем текущий список групп на странице до удаления группы (4.5)
        GroupData deletedGroup = before.iterator().next(); // получим первый попавшийся элемент множества
        app.group().delete(deletedGroup);
        Groups after = app.group().all();//считываем  список групп на странице после созданий новой группы (4.5)
        Assert.assertEquals(after.size(), before.size() - 1);//проверка нового кол-ва групп (добавлено в 4.3)
        assertThat(after, equalTo(before.without(deletedGroup)));

        }

}

