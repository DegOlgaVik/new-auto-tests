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
@Feature("Поиск по репозиторию")

public class SearchTest {

    @BeforeEach
    public void setup(){
        //1. Открыть страницу https://github.com/junit-team/junit4
        open("https://github.com/junit-team/junit4");
    }

    @Test
    @Story("Переход на ветку fixtures")
    @DisplayName("Переключение на ветку fixtures")
    public void setup1() {
        step("Выбор ветки fixtures", () -> {
        //2. Кликнуть на кнопку id="branch-select-menu"
        TestPages.repoPage.branchSelectButton()
                .click();
        //3. Выбрать ветку fixtures
        TestPages.repoPage.fixtures()
                .click();
        });

        step("Проверка перехода на ветку fixtures", () -> {
        //4. Проверить переход на страницу fixtures ветки
        TestPages.repoPage.fixturesBranch()
                .shouldBe(visible);
        });
    }

    @MethodSource("incorrectCredentials")
    @ParameterizedTest(name = "{displayName} {0}")
    @Story("Поиск по номерам и буквам релизов")
    @DisplayName("Позитивные проверки поиска по релизам:")
    public void positiveSearchChecksTest(String type, String name){
        step("Переход на страницу тегов", () -> {
        TestPages.releasePage.tagsButton()
                .click();
        });
        step("Переход на страницу релизов", () -> {
        TestPages.releasePage.releaseButton()
                .click();
        });
        step("Поиск по номеру и буквам в названии", () -> {
        TestPages.releasePage.searchButton()
                .sendKeys(name);
        });
    }

    static Stream<Arguments> incorrectCredentials() {
        return Stream.of(
                arguments(
                        "по номеру",
                        "4.13.1"
                ),
                arguments(
                        "буквам в названии",
                        "RC"
                )
        );
    }
}