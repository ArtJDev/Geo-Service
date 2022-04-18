package ru.netology.sender;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderTest {

    @BeforeEach
    public void init() {
        System.out.println("Test started");
    }

    @AfterEach
    public void complete() {
        System.out.println("\nTest completed");
    }

    @ParameterizedTest
    @MethodSource("source")
    void testSend(String ip, Country country) {
        final String IP_ADDRESS_HEADER = "x-real-ip";
        GeoService geoService = new GeoServiceImpl();
        LocalizationService localizationService = new LocalizationServiceImpl();
        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, ip);

        MessageSender messageSender = Mockito.mock(MessageSender.class);
        Mockito.when(messageSender.send(headers)).thenReturn(localizationService.locale(country));

        String result = messageSender.send(headers);
        String expected = messageSenderImpl.send(headers);
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of("172.0.32.11", Country.RUSSIA), Arguments.of("96.44.183.149", Country.USA));
    }
}
