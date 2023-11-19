package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class ClientHelper extends HelperBase{

    public ClientHelper(ApplicationManager manager) {
        super(manager);
    }

    public void singupNewAccount(String username){
        click(By.xpath("//a[@href='signup_page.php']"));
        type(By.name("username"), username);
        type(By.name("email"), String.format("%s@localhost", username));
        click(By.xpath("//input[@type='submit']"));
        click(By.xpath("//a[@href='login_page.php']"));
    }
    public void finishRegistration(String urlOfEmail){
        runUrl(urlOfEmail);
        type(By.name("password"), "password");
        type(By.name("password_confirm"), "password");
    }
}
