package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.stream.Stream;

public class UserCreationTests extends TestBase{

    DeveloperMailUser user;

    public static Stream<String> randomUser(){
        return Stream.of(CommonFunctions.randomSting(8));
    }

/*    @ParameterizedTest
    @MethodSource("randomUser")
    void canCreateUser(String user){
        var email = String.format("%s@localhost", user);
        var password = "password";
        app.jamesApi().addUser(email, password);

        app.client().singupNewAccount(user);

        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
        var url = CommonFunctions.extractUrl(messages.get(0).content());
        app.client().finishRegistration(url, password);
        app.http().login(user, password);
    }*/
    @Test
    void canCreateUserDevMail(){
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

        app.client().singupNewAccount(user.name(), email);

        var message = app.developerMail().receive(user, Duration.ofSeconds(10));
        var url = CommonFunctions.extractUrl(message);
        app.client().finishRegistration(url, password);
        app.http().login(user.name(), password);
    }

    @AfterEach
    void deleteMailuser(){
        app.developerMail().deleteUser(user);
    }
}
