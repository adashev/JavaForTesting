package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.GroupData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {
    public static void main(String[] args) throws IOException {
        // в качестве параметров передаем кол-во групп и путь к файлу
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<GroupData> groups = generateGroups(count);// 1) генерируем требуемое кол-во групп
        save(groups, file); // 2) сохраняем сгенирированные группы в файл
    }

    private static void save(List<GroupData> groups, File file) throws IOException {// сохраняем сгенирированные группы в файл
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (GroupData group : groups) {
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
        }
        writer.close();
    }

    private static List<GroupData> generateGroups(int count) { // генерируем требуемое кол-во групп
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("test %s", i))
                    .withHeader (String.format("header %s", i)).withFooter (String.format("footer %s", i)));
        }
        return groups;
    }
}
