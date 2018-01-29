package ru.stqa.pft.addressbook.model;

public class GroupData {
    private int id; //добавили в 4.7. со String на int поменяли в 4.8 + там же убрали final
    private final String name;
    private final String header;
    private final String footer;

    public GroupData(int id, String name, String header, String footer) {
        this.id = id;//добавили в 4.7
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    public GroupData(String name, String header, String footer) {//это второй конструктор. его добавили в 4.7
        this.id = 0;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    public int getId() { //добавили в 4.7
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public String getHeader() {
        return header;
    }
    public String getFooter() {
        return footer;
    }

    @Override
    public String toString() { // в 4.7 добавили в него id
        return "GroupData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (id != groupData.id) return false;
        return name != null ? name.equals(groupData.name) : groupData.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
