package ru.netology.web;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class ApplicationForCardTest {
    @Test
    void validateNamePhoneAndCheckbox1() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Шигапова Нэлли");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button").click();
        $("[data-test-id='order-success']").shouldHave(
                exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void validateNamePhoneAndCheckbox2() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Шигапова-Нэлли");
        $("[data-test-id='phone'] input").setValue("+79000000009");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button").click();
        $("[data-test-id='order-success']").shouldHave(
                exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotNameBeLatin() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Shigapova Nelli");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(
                exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotNameContainsUnderscore() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Шигапова_Нэлли");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(
                exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotPhoneBeMoreThen11() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Шигапова Нэлли");
        $("[data-test-id='phone'] input").setValue("+7000000000000");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(
                exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotFirstCharPhoneBeNotPlus() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Шигапова    Нэлли");
        $("[data-test-id='phone'] input").setValue("79000000000");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(
                exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
