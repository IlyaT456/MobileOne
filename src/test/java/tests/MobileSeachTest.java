package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class MobileSeachTest extends TestBase {
    @Test
    @Tag("android")
    @DisplayName("Тест на поиск JavaScript")
    void searchWikkiTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("JavaScript");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_container"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("android")
    @DisplayName("Тест на поиск java")
    void openArticleWithError() {
        step("enter the search java", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Java");
        });
        step("Opens the first link", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title")).first().click()
        );
    }

    @Tag("ios")
    @DisplayName("iOS test")
    @Test
    void outputTextTest() {
        step("iOS test", () -> {
            $(id("Text Button")).click();
            $(id("Text Input")).sendKeys("qa.guru homework");
            $(id("Text Input")).pressEnter();
            $(id("Text Output")).shouldHave(text("qa.guru homework"));
        });
    }
}


