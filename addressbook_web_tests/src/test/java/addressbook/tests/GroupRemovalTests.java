package addressbook.tests;

import addressbook.model.GroupData;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class GroupRemovalTests extends TestBase {
    @Test
    public void canRemoveGroup() {
        Allure.step("Checking precodition RemovalTest", step ->{
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
            }
        });
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Allure.step("Validating result RemovalTest", step -> {
            Assertions.assertEquals(newGroups, expectedList);
        });
    }
    @Test
    public void canRemoveAllGroupsAtOnce() {
        Allure.step("Checking precodition RemovalAtOnceTest", step -> {
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
            }
        });
        app.groups().removeAllGroups();
        Allure.step("Validating result RemovalAtOnceTest", step -> {
            Assertions.assertEquals(0, app.hbm().getGroupCount());
        });
    }
}
