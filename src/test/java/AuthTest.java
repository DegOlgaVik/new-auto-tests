import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {

    @Test
    public void shouldAuthorizeTest() {
        open("https://github.com/");
        $("[href='/login']").click();
        $("[id='login_field']").sendKeys("DegOlgaVik");
        $("[id='password']").sendKeys("+1Gh11051988");
        $(".js-sign-in-button").click();
        $(".Header").shouldBe(visible);
        $("[aria-label=\"View profile and more\"]").click();
        $("[data-ga-click=\"Header, go to profile, text:your profile\"]").click();
        $("[aria-label=\"KazanExpress\"]").shouldBe(visible);

    }
}