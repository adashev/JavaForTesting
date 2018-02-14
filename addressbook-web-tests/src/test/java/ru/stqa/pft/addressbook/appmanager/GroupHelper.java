package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
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
        returnGroupPage();
    }

    public void modify(GroupData group) {// добавили в 5.2
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        returnGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }
    public int getGroupCount() {//создана в 4.3
        return wd.findElements(By.name("selected[]")).size(); //считает текущее число групп на странице (напр., в 4.3)
    } //т.е. все теги, у кот. есть атрибут name="selected[]"

    /*public List<GroupData> list() {  //добавили в 4.5
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();

            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));//добав. в 4.7. В 4.8 добавили приведение к int
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }*/

    public Set<GroupData> all() {  //добавили в 5.5
        Set<GroupData> groups = new HashSet<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));//добав. в 4.7. В 4.8 добавили приведение к int
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }


}
