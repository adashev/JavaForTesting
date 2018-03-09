package ru.stqa.pft.addressbook.generators;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
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

    @Parameter(names = "-d", description = "Data format")// добавлено в 6.6
    public String format;

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
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file)); // 2) сохраняем сгенирированные контакты в Csv-файл
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException { // в 6.6
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts); // каждый объект-контакт преобразовать в xml-строку
        try (Writer writer = new FileWriter(file)){// в 6.8 автоматич. закрытие файла
            writer.write(xml);
        }
        //writer.close();
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {//в 6.2
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file)){// в 6.8 автоматич. закрытие файла
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s\n", contact.getLastname(), contact.getFirstname(), contact.getAddress()));
            }
        }
        //writer.close();
    }

    private List<ContactData> generateСontacts(int count) {//в 6.2
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withLastname(String.format("Lastname\n%s", i))
                    .withFirstname(String.format("Firstname\n%s", i))
                    .withAddress(String.format("Address д.%s", i)));
        }
        return contacts;
    }
}
