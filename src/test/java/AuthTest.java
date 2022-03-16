import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Owner("DegOlgaVik")
@Feature("Проверка авторизации")

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
    @Story("Авторизация")
    @DisplayName("Успешная авторизация")
    public void shouldAuthorizeTest() {
        step("Заполнить поля инпута и пароля и нажать кнопку авторизации", () -> {
        //3. Заполнить инпуты логина и пароля
        TestPages.mainPage.loginInput()
                .sendKeys("DegOlgaVik");
        TestPages.mainPage.passwordInput()
                .sendKeys("*1**1***1***");
        //4. Нажать кнопку sign in
        TestPages.mainPage.signInButton()
                .click();
        });

        step("Проверка авторизации", () -> {
        //5. Проверить авторизацию
        TestPages.mainPage.header()
                .shouldBe(visible);
        });

        step("Раскрытие информации и переход на страницу профайла", () -> {
        //6. Нажать на значок аватарки, чтобы он раскрылся
        TestPages.profilePage.headerlink()
                .click();
        //7. Выбрать "Your profile"
        TestPages.profilePage.dropdownItem()
                .click();
        });

        step("Проверка переходна на страницу Your profile", () -> {
        //8. Проверить переход на страницу профайла
        TestPages.profilePage.avatarGroupItem()
                .shouldBe(visible);
        });
    }

    @MethodSource("incorrectCredentials")
    @ParameterizedTest(name = "{displayName} {0}")
    @DisplayName("Авторизация с некорректными данными:")
    public void shouldNotAuthorizeTest(String type, String phone, String password){
        step("Заполнить поля инпута и пароля и нажать кнопку авторизации", () -> {
        TestPages.mainPage.loginInput()
                .sendKeys(phone);
        TestPages.mainPage.passwordInput()
                .sendKeys(password);
        TestPages.mainPage.signInButton()
                .click();
        });

        step("Проверить, что появилась ошибка", () -> {
            TestPages.mainPage.incorrectInfo()
                    .shouldBe(visible);
        });
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