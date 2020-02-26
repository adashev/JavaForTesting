package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test10", null, null));
    }
    int before = app.getGroupHelper().getGroupCount();
    List<GroupData> beforeGroupList = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(beforeGroupList.size()-1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1a", "test20", "test30"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    List<GroupData> afterGroupList = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after, before);
    Assert.assertEquals(afterGroupList.size(), beforeGroupList.size());

  }
}
