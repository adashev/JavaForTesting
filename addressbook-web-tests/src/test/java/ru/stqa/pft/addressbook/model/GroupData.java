package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class GroupData {
    private final String name;
    private final String header;
    private final String footer;

    public GroupData(String name, String header, String footer) {
        this.name = name;
        this.header = header;
        this.footer = footer;
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GroupData groupData = (GroupData) obj;
        return Objects.equals(name, groupData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "GroupData{"+ "name='" +name+ '\'' +'}';
    }
}
