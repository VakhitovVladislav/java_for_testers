package addressbook.tests;

import addressbook.common.CommonFunctions;
import addressbook.model.GroupData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
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
        var  mapper = new YAMLMapper();
        var value = mapper.readValue(new File("groups.yaml"), new TypeReference<List<GroupData>>(){});
        result.addAll(value);
        return result;
    }
    public static List<GroupData> singleRandomGroup() throws IOException {
        return List.of(new GroupData()
                .withName(CommonFunctions.randomSting(10))
                .withHeader(CommonFunctions.randomSting(20))
                .withFooter(CommonFunctions.randomSting(30)));
    }

    public static List<GroupData> negativeGroupProvider() {
        return new ArrayList<GroupData>(List.of(
                new GroupData("", "Group Name'", "", "")));
    }

    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroup(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();
        var expectedGroups = new ArrayList<>(oldGroups);
        newGroups.sort(compareById());
        var maxId = newGroups.get(newGroups.size() - 1).id();
        expectedGroups.add(group.withId(maxId));
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