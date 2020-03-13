package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> beforeList = app.getGroupHelper().getGroupList();


    GroupData group = new GroupData("test1", null, null);
    app.getGroupHelper().createGroup(group);

    List<GroupData> afterList = app.getGroupHelper().getGroupList();
    int max = 0;
    for(GroupData g : afterList) {
      if(g.getId() > max) {
        max = g.getId();
      }
    }

    Comparator<? super GroupData> byId = (Comparator<GroupData>)
            (obj1, obj2) -> Integer.compare(obj1.getId(), obj2.getId());
    // (obj1, obj2) - названия парваметров
    // Integer.compare(obj1.getId(), obj2.getId()) - тело функции

    int max1 = afterList.stream().max(byId).get().getId();

    group.setId(max1);
    beforeList.add(group);
    Assert.assertEquals(new HashSet<Object>(afterList), new HashSet<Object>(beforeList));
//    Assert.assertEquals(afterList.size(), beforeList.size()+1);
  }

}
