package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public SelenideElement mainSignInButton() {
        return $("[href='/login']").as("кнопка логина");
    }
    public SelenideElement loginInput() {
        return $("[id='login_field']").as("логин");
    }
    public SelenideElement passwordInput() {
        return $("[id='password']").as("пароль");
    }
    public SelenideElement signInButton() {
        return $(".js-sign-in-button").as("кнопка авторизации");
    }
    public SelenideElement header() {
        return $(".Header").as("Хедер на главной странице после авторизации");
    }
    public SelenideElement incorrectInfo() {
        return $(".flash.flash-full.flash-error").as("Информация с ошибкой");
    }
}