package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;
import java.util.stream.Stream;

public class UserCreationTests extends TestBase{

    DeveloperMailUser user;

    public static Stream<String> randomUser(){
        return Stream.of(CommonFunctions.randomSting(8));
    }

    @ParameterizedTest
    @MethodSource("randomUser")
    void canCreateUser(String user){
        var email = String.format("%s@localhost", user);
        var password = "password";
        app.jamesApi().addUser(email, password);

        app.client().singupNewAccount(user, email);

        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
        var url = CommonFunctions.extractUrl(messages.get(0).content());
        app.client().finishRegistration(url, password);
        app.http().login(user, password);
    }
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

    @Test
    void canCreateUserByApiMantis(){
        var username = CommonFunctions.randomSting(5);
        var password = CommonFunctions.randomSting(5);
        var email = String.format("%s@localhost", username);
        var classicPassword = "password";
        app.jamesApi().addUser(email, password);
        app.rest().createUser(new UserData()
                .withUserName(username)
                .withEmail(email)
                .withRealName(username)
                .withPassword(password)); //Сценарий начинает регистрацию нового пользователя в Mantis, используя REST API.
        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        var url = CommonFunctions.extractUrl(messages.get(0).content());
        app.client().finishRegistration(url, classicPassword);
        app.http().login(username, classicPassword); //проверяем, что пользователь может залогиниться(HttpSessionHelper)
        app.http().isLoggedIn();
        /*
        Mantis отправляет письмо на указанный адрес, тест должен получить это письмо,
        извлечь из него ссылку для подтверждения, пройти по этой ссылке и завершить регистрацию.
        Затем тест должен проверить, что пользователь может войти в систему с новым паролем.
        Этот шаг можно выполнить на уровне протокола HTTP.*/
    }

    //@AfterEach
    //void deleteMailUser(){
        //app.developerMail().deleteUser(user);
    //}
}
