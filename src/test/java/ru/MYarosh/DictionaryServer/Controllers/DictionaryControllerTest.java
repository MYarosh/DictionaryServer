package ru.MYarosh.DictionaryServer.Controllers;

import com.google.gson.Gson;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.MYarosh.DictionaryServer.Model.DictionaryData;
import ru.MYarosh.DictionaryServer.Model.DictionaryValue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тест контроллера
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DictionaryControllerTest {

    @Autowired
    private static DictionaryController dictionaryController;
    private static DictionaryData dictionaryData;
    @Autowired
    private MockMvc mockMvc;

    @BeforeClass
    public static void setUp(){
        dictionaryData = new DictionaryData(new DictionaryValue("Value",20),"K1");
    }

    @Test
    public void setTest() throws Exception{
        this.mockMvc.perform(post("/DictionaryService/Set")
                .contentType("application/json").content(new Gson().toJson(dictionaryData)))
                .andExpect(status().isOk());
    }

    @Test
    public void getTest() throws Exception {
        this.mockMvc.perform(post("/DictionaryService/Get").content(dictionaryData.getKey()))
                .andExpect(status().isOk());
    }
}
