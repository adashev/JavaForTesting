package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {
  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test10", null, null));
    }
    int before = app.getGroupHelper().getGroupCount();
    List<GroupData> beforeGroupList = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(beforeGroupList.size()-1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    List<GroupData> afterGroupList = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after, beforeGroupList.size()-1);
    Assert.assertEquals(afterGroupList.size(), beforeGroupList.size()-1);
  }

}
