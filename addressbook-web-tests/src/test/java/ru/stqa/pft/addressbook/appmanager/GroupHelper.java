package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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
    public void selectGroup(int index) { //параметр int index добавили в 4.4
        wd.findElements(By.name("selected[]")).get(index).click();
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

    public void modify(int index, GroupData group) {// добавили в 5.2
        selectGroup(index);//параметр before - 1 добавлbkb в 4.4, чтобы изменять не первую, а последнюю группу в списке
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnGroupPage();
    }

    public void delete(int index) {
       selectGroup(index);//параметр before - 1 добавляем в 4.4, чтобы удалять не первую, а последнюю группу в списке
       deleteSelectedGroups();
       returnGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }
    public int getGroupCount() {//создана в 4.3
        return wd.findElements(By.name("selected[]")).size(); //считает текущее число групп на странице (напр., в 4.3)
    } //т.е. все теги, у кот. есть атрибут name="selected[]"

    public List<GroupData> list() {  //добавили в 4.5
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();

            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));//добав. в 4.7. В 4.8 добавили приведение к int
            GroupData group = new GroupData(id, name, null, null);
            groups.add(group);
        }
        return groups;

    }
}
