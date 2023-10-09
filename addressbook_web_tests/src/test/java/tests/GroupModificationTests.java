package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTests extends TestBase{

    @Test
    void canModifyGroup(){
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testGroup = new GroupData().withName("modifyName");
        app.groups().modifyGroup(oldGroups.get(index), testGroup);
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testGroup.withId(oldGroups.get(index).id()));
        newGroups.sort(compareById());
        expectedList.sort(compareById());
        Assertions.assertEquals(newGroups, expectedList);
    }

    private Comparator<GroupData> compareById() {
        return (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
    }
}
