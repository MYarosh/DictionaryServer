package ru.MYarosh.DictionaryServer.Services;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.MYarosh.DictionaryServer.Model.DictionaryModel;
import ru.MYarosh.DictionaryServer.Model.DictionaryValue;

import java.util.HashMap;

/**
 * Сервис для работы с ttl
 */
@Service
public class TtlService {

    void decreaseTtlAndDelete0Ttl(@NonNull DictionaryModel dictionaryModel){
        decreaseTtl(dictionaryModel);
        delete0Ttl(dictionaryModel);
    }

    /**
     * Уменьшает положительное значение ttl каждой записи на 1
     * @param dictionaryModel - хранилище
     */
    private void decreaseTtl(@NonNull DictionaryModel dictionaryModel){
        for (HashMap.Entry<String, DictionaryValue> entry:
             dictionaryModel.getDictionary().entrySet()) {
            entry.getValue().decreaseTtl();
        }
    }

    /**
     * Удаляет записи с нулевым ttl
     * @param dictionaryModel - хранилище
     */
    private void delete0Ttl(@NonNull DictionaryModel dictionaryModel){
        for (HashMap.Entry<String, DictionaryValue> entry:
                dictionaryModel.getDictionary().entrySet()) {
            if (entry.getValue().getTtl()==0){
                dictionaryModel.getDictionary().remove(entry.getKey());
            }
        }
    }
}
