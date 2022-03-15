package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ReleasePage {

    public SelenideElement tagsButton() {
        return $("[data-pjax=\"#repo-content-pjax-container\"].ml-3").as("теги");
    }
    public SelenideElement releaseButton() {
        return $("[data-selected-links=\"repo_releases /junit-team/junit4/releases\"]").as("релизы");
    }
    public SelenideElement searchButton() {
        return $("[type='search']").as("строка поиска");
    }
}
