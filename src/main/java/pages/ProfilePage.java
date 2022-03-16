package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    public SelenideElement headerlink() {
        return $("[aria-label=\"View profile and more\"]").as("кнопка аватарки");
    }
    public SelenideElement dropdownItem() {
        return $("[data-ga-click=\"Header, go to profile, text:your profile\"]").as("выбор из списка моего профайла");
    }
    public SelenideElement avatarGroupItem() {
        return $("[aria-label=\"KazanExpress\"]").as("Лейбэл организации");
    }
}
