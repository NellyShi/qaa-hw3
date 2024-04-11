package ru.netology.web;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class ApplicationForCardTest {
    @Test
    void shouldNotNameBeLatin() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Shigapova Nelli");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement] .checkbox__box").click();
        $("button").click();
        $(".Success_successBlock__2L3Cw").shouldNot(exist);
    }

    @Test
    void shouldNotNameBeUnderscore() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Шигапова_Нэлли");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement] .checkbox__box").click();
        $("button").click();
        $(".Success_successBlock__2L3Cw").shouldNot(exist);
    }

    @Test
    void shouldNameBeCyrillicAndSpace() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Шигапова    Нэлли");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement] .checkbox__box").click();
        $("button").click();
        $(".Success_successBlock__2L3Cw").should(exist);
    }

    @Test
    void shouldNotPhoneBeMoreThen11() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Шигапова    Нэлли");
        $("[data-test-id=phone] input").setValue("+7000000000000");
        $("[data-test-id=agreement] .checkbox__box").click();
        $("button").click();
        $(".Success_successBlock__2L3Cw").shouldNot(exist);
    }

    @Test
    void shouldNotFirstCharPhoneBeNotPlus() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Шигапова    Нэлли");
        $("[data-test-id=phone] input").setValue("79000000000");
        $("[data-test-id=agreement] .checkbox__box").click();
        $("button").click();
        $(".Success_successBlock__2L3Cw").shouldNot(exist);
    }

    @Test
    void shouldBeAgreementCheckBoxSet() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Шигапова    Нэлли");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("button").click();
        $(".Success_successBlock__2L3Cw").shouldNot(exist);
    }
}
