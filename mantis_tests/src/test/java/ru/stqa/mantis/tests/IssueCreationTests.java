package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.IssueData;

public class IssueCreationTests extends TestBase{

    @Test
    void canCreateIssue(){
        app.rest().createIssue(new IssueData()
                .withSummary(CommonFunctions.randomSting(10))
                .withDescription(CommonFunctions.randomSting(20))
                .withProject(1L));
    }
}
