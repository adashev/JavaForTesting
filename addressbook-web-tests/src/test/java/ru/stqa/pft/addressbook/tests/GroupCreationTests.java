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
    group.setId(max);
    beforeList.add(group);
    Assert.assertEquals(new HashSet<Object>(afterList), new HashSet<Object>(beforeList));
//    Assert.assertEquals(afterList.size(), beforeList.size()+1);
  }

}
