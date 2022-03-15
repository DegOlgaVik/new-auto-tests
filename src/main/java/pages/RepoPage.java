package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class RepoPage {

    public SelenideElement branchSelectButton() {
        return $("[id='branch-select-menu']").as("кнопка выбора ветки");
    }
    public SelenideElement fixtures() {
        return $("[href=\"https://github.com/junit-team/junit4/tree/fixtures\"]").as("ветка fixtures");
    }
    public SelenideElement fixturesBranch() {
        return $("[href='/junit-team/junit4/commit/424424c505c180f42eaac323862224648cbee38e']").as("ветка fixtures");
    }
}
