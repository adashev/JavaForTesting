package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> beforeGroupList = app.getGroupHelper().getGroupList();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    List<GroupData> afterGroupList = app.getGroupHelper().getGroupList();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(afterGroupList.size(), beforeGroupList.size()+1);
  }

}
