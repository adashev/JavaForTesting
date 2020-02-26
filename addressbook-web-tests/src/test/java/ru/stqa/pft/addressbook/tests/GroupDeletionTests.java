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
    List<GroupData> beforeGrList = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(beforeGrList.size()-1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    List<GroupData> afterGroupList = app.getGroupHelper().getGroupList();

    beforeGrList.remove(beforeGrList.size()-1);
    for(int i=0; i < beforeGrList.size(); i++){
      Assert.assertEquals(afterGroupList.get(i), beforeGrList.get(i));
    }
    Assert.assertEquals(afterGroupList.size(), beforeGrList.size());
  }

}
