package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() throws InterruptedException {
        String username = (CommonFunctions.randomSting(8));
        var email = String.format("%s@localhost", username);
        app.jamesCli().addUser(email, "password"); // создать пользователя (адрес на почтовом сервере) (JamesHelper)
        app.client().singupNewAccount(username); //заполняем форму создания и отправляем (браузер)

        var messages = app.mail().receive(email, "password", Duration.ofSeconds(60));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*]");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end()); //извлекаем ссылку из письма(реализованно)
            app.client().finishRegistration(url);    //проходим по ссылке и завершаем регистрацию (браузер)
            app.http().login(username, "password");//проверяем, что пользователь может залогиниться(HttpSessionHelper)
            app.http().isLoggedIn();
        }
    }
}
