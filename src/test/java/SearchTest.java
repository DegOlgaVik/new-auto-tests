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

public class SearchTest {

    @BeforeEach
    public void setup(){
        //1. Открыть страницу https://github.com/junit-team/junit4
        open("https://github.com/junit-team/junit4");
    }

    @Test
    @DisplayName("Переключение на ветку fixtures")
    public void setup1() {
        //2. Кликнуть на кнопку id="branch-select-menu"
        TestPages.repoPage.branchSelectButton()
                .click();
        //3. Выбрать ветку fixtures
        TestPages.repoPage.fixtures()
                .click();
        //4. Проверить переход на страницу fixtures ветки
        TestPages.repoPage.fixturesBranch()
                .shouldBe(visible);
    }

    @MethodSource("incorrectCredentials")
    @ParameterizedTest(name = "{displayName} {0}")
    @DisplayName("Позитивные проверки поиска по релизам:")
    public void positiveSearchChecksTest(String type, String name){
        TestPages.releasePage.tagsButton()
                .click();
        TestPages.releasePage.releaseButton()
                .click();
        TestPages.releasePage.searchButton()
                .sendKeys(name);
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