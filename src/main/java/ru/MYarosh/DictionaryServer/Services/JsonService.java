package ru.MYarosh.DictionaryServer.Services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import ru.MYarosh.DictionaryServer.Model.DictionaryModel;
import ru.MYarosh.DictionaryServer.Model.DictionaryValue;

import java.io.FileWriter;
import java.util.HashMap;

/**
 * Сервис для работы с сохранением и записью хранилища
 */
@Service
public class JsonService {

    private final Gson gson;

    public JsonService() {
        this.gson = new Gson();
    }

    String dump(DictionaryModel dictionaryModel) throws Exception {
        FileWriter writer = new FileWriter("output.json", false);
        writer.write(gson.toJson(dictionaryModel.getDictionary()));
        return gson.toJson(dictionaryModel.getDictionary());
    }

    DictionaryModel load(String dump) {
        DictionaryModel dictionaryModel = new DictionaryModel();
        dictionaryModel.setDictionary(gson.fromJson(dump, new TypeToken<HashMap<String, DictionaryValue>>(){}.getType()));
        return dictionaryModel;
    }
}
