import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AuthTest {

    @BeforeEach
    public void setup(){
        //1. Открыть страницу https://github.com/
        open("https://github.com/");
        //2. Кликнуть на кнопку sign in
        TestPages.mainPage.mainSignInButton()
                .click();
    }

    @Test
    @DisplayName("Успешная авторизация")
    public void shouldAuthorizeTest() {
        //3. Заполнить инпуты логина и пароля
        TestPages.mainPage.loginInput()
                .sendKeys("D**O***V**");
        TestPages.mainPage.passwordInput()
                .sendKeys("*1**11**1***");
        //4. Нажать кнопку sign in
        TestPages.mainPage.signInButton()
                .click();
        //5. Проверить авторизацию
        TestPages.mainPage.header()
                .shouldBe(visible);
        //6. Нажать на значок аватарки, чтобы он раскрылся
        TestPages.profilePage.headerlink()
                .click();
        //7. Выбрать "Your profile"
        TestPages.profilePage.dropdownItem()
                .click();
        //8. Проверить переход на страницу профайла
        TestPages.profilePage.avatarGroupItem()
                .shouldBe(visible);
    }

    @MethodSource("incorrectCredentials")
    @ParameterizedTest(name = "{displayName} {0}")
    @DisplayName("Авторизация с некорректными данными:")
    public void shouldNotAuthorizeTest(String type, String phone, String password){
        TestPages.mainPage.loginInput()
                .sendKeys(phone);
        TestPages.mainPage.passwordInput()
                .sendKeys(password);
        TestPages.mainPage.signInButton()
                .click();
        TestPages.mainPage.incorrectInfo()
                .shouldBe(visible);
    }

    static Stream<Arguments> incorrectCredentials() {
        return Stream.of(
                arguments(
                        "зарегистрированный номер телефона и пароль от чужого аккаунта",
                        "9999999999",
                        "123456789Qq"

                ),
                arguments(
                        "незарегистрированный номер телефона и пароль от чужого аккаунта",
                        "9100000000",
                        "123456789Qq"
                )
        );
    }

}