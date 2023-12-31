package addressbook.generator;

import addressbook.common.CommonFunctions;
import addressbook.model.ContactData;
import addressbook.model.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {
    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-n"})
    int count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных");
        }
    }

    private Object generateContacts() {
        return generateData(()-> new ContactData()
                .withName(CommonFunctions.randomSting(10))
                .withLastName(CommonFunctions.randomSting(10))
                .withMiddleName(CommonFunctions.randomSting(10))
                .withNickName(CommonFunctions.randomSting(10))
                .withTitle(CommonFunctions.randomSting(10))
                .withCompany(CommonFunctions.randomSting(10))
                .withAddress(CommonFunctions.randomSting(10))
                .withHomePage(CommonFunctions.randomSting(10))
                .withMobilePhone(CommonFunctions.randomSting(10))
                .withWorkPhone(CommonFunctions.randomSting(10))
                .withFaxPhone(CommonFunctions.randomSting(10))
                .withEmail(CommonFunctions.randomSting(10))
                .withEmail2(CommonFunctions.randomSting(10))
                .withEmail3(CommonFunctions.randomSting(10))
                .withHomePage(CommonFunctions.randomSting(10))
                .withHAddressSecondary(CommonFunctions.randomSting(10))
                .withHome(CommonFunctions.randomSting(10))
                .withNotes(CommonFunctions.randomSting(10)));
    }
    private Object generateData(Supplier<Object> dataSupplier){
        return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
    }

    private Object generateGroups() {
        return generateData(() -> new GroupData()
                .withName(CommonFunctions.randomSting(10))
                .withHeader(CommonFunctions.randomSting(10))
                .withFooter(CommonFunctions.randomSting(10)));
    }


    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);
        }
        if ("yaml".equals(format)) {
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        }
        if ("xml".equals(format)) {
            var mapper = new XmlMapper();
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }
    }
}
