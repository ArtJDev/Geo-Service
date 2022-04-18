package ru.netology.geo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceTest {

    @BeforeEach
    public void init() {
        System.out.println("Test started");
    }

    @AfterEach
    public void complete() {
        System.out.println("Test completed");
    }

    @Test
    void testByIp() {
        GeoService sut = new GeoServiceImpl();
        final String MOSCOW_IP = "172.0.32.11";

        Location first = new Location(null, Country.RUSSIA, null, 0);
        Location second = sut.byIp(MOSCOW_IP);
        Country expected = first.getCountry();
        Country result = second.getCountry();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testByCoordinates() {
        GeoService sut = new GeoServiceImpl();
        Assertions.assertThrows(RuntimeException.class, () -> sut.byCoordinates(25.46, 55.18));
    }
}