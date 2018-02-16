package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.xpath("//div[@id='content']/form/input[5]"));
    }

    public void selectGroupById(int id) { // метод добавили в 5.5
        wd.findElement(By.cssSelector("input[value='"  + id + "']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }
    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null; //добавили в 5.7
        returnGroupPage();
    }

    public void modify(GroupData group) {// добавили в 5.2
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null; //добавили в 5.7
        returnGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        groupCache = null; //добавили в 5.7
        returnGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }
    public int getGroupCount() {//создана в 4.3
        return wd.findElements(By.name("selected[]")).size(); //считает текущее число групп на странице (напр., в 4.3)
    } //т.е. все теги, у кот. есть атрибут name="selected[]"

    private Groups groupCache = null; //добавили в 5.7

    public Groups all() {  //добавили в 5.7
        if (groupCache != null) { //добавили в 5.7
            return new Groups (groupCache);
        }

        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups (groupCache);
    }


}
