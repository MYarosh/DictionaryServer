package ru.MYarosh.DictionaryServer.Services;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.MYarosh.DictionaryServer.Model.DictionaryData;
import ru.MYarosh.DictionaryServer.Model.DictionaryValue;

/**
 * Тест сервиса
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class DictionaryServiceTest {

    private static DictionaryService dictionaryService;
    private static DictionaryData dictionaryData;

    @BeforeAll
    static void setUp() {
        dictionaryData = new DictionaryData(new DictionaryValue("Value",20),"K1");
        dictionaryService = new DictionaryService();
    }

    @Order(1)
    @Test
    void set() {
        DictionaryValue dictionaryValue = dictionaryService.set(dictionaryData);
        System.out.println(dictionaryValue);
        Assert.assertNull(dictionaryValue);
    }

    @Order(2)
    @Test
    void setNext() {
        DictionaryValue dictionaryValue = dictionaryService.set(dictionaryData);
        System.out.println(dictionaryValue);
        Assert.assertNotNull(dictionaryValue);
    }

    @Order(3)
    @Test
    void get() {
        DictionaryValue dictionaryValue = dictionaryService.get(dictionaryData.getKey());
        System.out.println(dictionaryValue);
        Assert.assertNotNull(dictionaryValue);
    }

    @Order(4)
    @Test
    void remove() {
        DictionaryValue dictionaryValue = dictionaryService.remove(dictionaryData.getKey());
        System.out.println(dictionaryValue);
        Assert.assertNotNull(dictionaryValue);
    }
}