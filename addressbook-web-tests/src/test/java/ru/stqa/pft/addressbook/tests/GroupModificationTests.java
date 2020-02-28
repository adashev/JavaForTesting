package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.HashSet;
import java.util.List;


public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test10", null, null));
    }
    List<GroupData> beforeList = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(beforeList.size()-1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(beforeList.get(beforeList.size()-1).getId() ,"test-Modific", "test20", "test30");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnGroupPage();
    List<GroupData> afterList = app.getGroupHelper().getGroupList();

    beforeList.remove(beforeList.size()-1);
    beforeList.add(group);

    Assert.assertEquals(new HashSet<Object>(afterList), new HashSet<Object>(beforeList));

  }
}
