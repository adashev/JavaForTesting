package ru.stqa.pft.addressbook.generators;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")  //в 6.3
    public int count;

    @Parameter(names = "-f", description = "Target file")  //в 6.3
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();//в 6.3
        JCommander jCommander = new JCommander(generator);//в 6.3
        try { //в 6.3
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
        /*int count = Integer.parseInt(args[0]);//в 6.2
        File file = new File(args[1]);//в 6.2*/
    }

    private void run() throws IOException { //в 6.3
        List<ContactData> contacts = generateСontacts(count);// 1) генерируем требуемое кол-во контактов
        save(contacts, new File(file));// 2) сохраняем сгенирированные контакты в файл
    }

    private void save(List<ContactData> contacts, File file) throws IOException {//в 6.2
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s\n", contact.getLastname(), contact.getFirstname(), contact.getAddress()));
        }
        writer.close();
    }

    private List<ContactData> generateСontacts(int count) {//в 6.2
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withLastname(String.format("Lastname %s", i))
                    .withFirstname(String.format("Firstname %s", i))
                    .withAddress(String.format("Address д.%s", i)));
        }
        return contacts;
    }
}
