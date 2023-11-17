package addressbook.tests;

import addressbook.common.CommonFunctions;
import addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup(){
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testGroup = new GroupData().withName(CommonFunctions.randomSting(10));
        app.groups().modifyGroup(oldGroups.get(index), testGroup);
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testGroup.withId(oldGroups.get(index).id()));
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
    }


}
