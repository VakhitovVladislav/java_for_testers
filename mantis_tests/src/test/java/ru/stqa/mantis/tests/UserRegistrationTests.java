package ru.stqa.mantis.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase {
    public static Stream<String> randomUser(){
        return Stream.of(CommonFunctions.randomSting(8));
    }

    @ParameterizedTest
    @MethodSource("randomUser")
    void canRegisterUser(String username) throws InterruptedException {
        var email = String.format("%s@localhost", username);
        var password = "password";
        app.jamesCli().addUser(email, password); // создать пользователя (адрес на почтовом сервере) (JamesHelper)
        app.client().singupNewAccount(username, email); //заполняем форму создания и отправляем (браузер)

        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        var url = CommonFunctions.extractUrl(messages.get(0).content());
        app.client().finishRegistration(url, password);
        app.http().login(username, password); //проверяем, что пользователь может залогиниться(HttpSessionHelper)
        app.http().isLoggedIn();
    }
}
