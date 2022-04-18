package ru.netology.i18n;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class LocalizationServiceTest {
    LocalizationService localizationService = new LocalizationServiceImpl();

    @BeforeEach
    public void init() {
        System.out.println("Test started");
    }

    @AfterEach
    public void complete() {
        System.out.println("Test completed");
    }

    @Test
    void testLocale() {
        String expected = "Добро пожаловать";
        String result = localizationService.locale(Country.RUSSIA);

        Assertions.assertEquals(expected, result);
    }

}