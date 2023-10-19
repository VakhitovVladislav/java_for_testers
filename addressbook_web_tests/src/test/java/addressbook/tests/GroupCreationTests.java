package addressbook.tests;

import addressbook.model.GroupData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class GroupCreationTests extends TestBase {
    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
  /*      for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupData()
                            .withName(name)
                            .withHeader(header)
                            .withFooter(footer));
                }
            }
        }*/
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>(){});
        result.addAll(value);
        return result;
    }

    public static List<GroupData> negativeGroupProvider() {
        return new ArrayList<GroupData>(List.of(
                new GroupData("", "Group Name'", "", "")));
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        var expectedGroups = new ArrayList<>(oldGroups);
        expectedGroups.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        newGroups.sort(compareById());
        expectedGroups.sort(compareById());
        Assertions.assertEquals(newGroups, expectedGroups);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        var expectedGroups = new ArrayList<>(oldGroups);
        newGroups.sort(compareById());
        expectedGroups.sort(compareById());
        Assertions.assertEquals(newGroups, expectedGroups);
    }

    private Comparator<GroupData> compareById() {
        return (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
    }

}