package ru.tarabne.tests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.tarabne.data.Locale;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoqaParametrizedTests extends TestBase {

     static Stream<Arguments> listShouldContainsCorrectItems() {
        return Stream.of(
                Arguments.of("Desktop",
                        List.of("Notes","Commands")),
                Arguments.of("Documents",
                        List.of("WorkSpace","Office")),
                Arguments.of("Downloads",
                        List.of("Word File.doc","Excel File.doc"))
                );
    }

    @MethodSource
    @ParameterizedTest(name = "При раскрытии списка {0} должны стать доступны элементы {1}")
    @Tags({
            @Tag("HIGH"),
            @Tag("WEB")
    })
    @DisplayName("Проверка содержимого списков в CheckBoxComponent")
    void listShouldContainsCorrectItems(String list, List<String> expectedItems) {
         open("/checkbox");
         $(byText("Home"))
                 .parent().parent().$("svg").click();
         $(byText(list)).parent().parent().$("svg").click();

         $(byText(list)).parent().parent().parent().$("ol").$$("li")
                .shouldHave(texts(expectedItems));
    }

    @CsvFileSource(resources = "/resultOfCheckboxSelectionShouldBeCorrected.csv")
    @ParameterizedTest(name = "При выделении чекбокса \"{0}\" отображается надпись \"You have selected : {1}\"")
    @Tags({
            @Tag("MEDIUM"),
            @Tag("TEG RADI TEGA")
    })
    @DisplayName("Проверка соответствия выделенного чекбокса значению, отображенному в результате")
    void resultOfCheckboxSelectionShouldBeCorrected(String checkBoxTitle, String expectedValue) {
        open("/checkbox");
        $("button.rct-option-expand-all").click();

        $(byText(checkBoxTitle)).parent().$("svg").click();
        $("span.text-success").shouldHave(text(expectedValue));
    }

    @EnumSource(Locale.class)
    @ParameterizedTest(name = "Данные пользователя в локали {0} после сохранения корректно отображаются после регистрации")
    @Tags({
            @Tag("LOW"),
            @Tag("WEB"),
            @Tag("MINOR"),
            @Tag("USER DATA")
    })
    @DisplayName("Проверка корректности отображения данных пользователя после регистрации")
    void userDataShouldBeCorrectAfterRegistration(Locale locale) {
         open("/text-box");
         $("#userName").setValue(locale.fullName);
         $("#userEmail").setValue(locale.email);
         $("#currentAddress").setValue(locale.currentAddress);
         $("#permanentAddress").setValue(locale.permanentAddress);
         $("#submit").click();

        $("#output #name").parent().shouldHave(text(locale.fullName));
        $("#output #email").parent().shouldHave(text(locale.email));
        $("#output #currentAddress").parent().shouldHave(text(locale.fullName));
        $("#output #permanentAddress").parent().shouldHave(text(locale.permanentAddress));
    }
}
