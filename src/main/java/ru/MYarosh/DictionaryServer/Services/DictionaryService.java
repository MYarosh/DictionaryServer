package ru.MYarosh.DictionaryServer.Services;

import org.springframework.stereotype.Service;
import ru.MYarosh.DictionaryServer.Model.DictionaryModel;
import ru.MYarosh.DictionaryServer.Model.DictionaryData;
import ru.MYarosh.DictionaryServer.Model.DictionaryValue;

/**
 * Сервис для работы с хранилищем
 */
@Service
public class DictionaryService {

    private DictionaryModel dictionaryModel = new DictionaryModel();
    private final JsonService jsonService = new JsonService();
    private final TtlService ttlService = new TtlService();

    /**
     * Возвращает значение объекта по ключу. Если ключа нет, то вернет {@code null}
     * @param dictionaryKey - ключ объекта
     * @return - значение объекта
     */
    public DictionaryValue get(String dictionaryKey) {
        ttlService.decreaseTtlAndDelete0Ttl(dictionaryModel);
        return dictionaryModel.getDictionary().get(dictionaryKey);
    }

    /**
     * Записывает значение в хранилище
     * @param dictionaryData - запись в хранилище
     * @return - значение предыдущей записи по ключу или {@code null}
     */
    public DictionaryValue set(DictionaryData dictionaryData) {
        ttlService.decreaseTtlAndDelete0Ttl(dictionaryModel);
        return dictionaryModel.getDictionary().put(dictionaryData.getKey(), dictionaryData.getValue());
    }

    /**
     * Удаляет запись в хранилище
     * @param dictionaryKey - ключ, удаляемой записи
     * @return - значение удаленной записи или {@code null}
     */
    public DictionaryValue remove(String dictionaryKey) {
        ttlService.decreaseTtlAndDelete0Ttl(dictionaryModel);
        return dictionaryModel.getDictionary().remove(dictionaryKey);
    }

    /**
     * Создает дамп хранилища в JSON
     * @return - дамп хранилища в JSON
     * @throws Exception - ошибка записи хранилища в JSON
     */
    public String dump() throws Exception {
        ttlService.decreaseTtlAndDelete0Ttl(dictionaryModel);
        return jsonService.dump(dictionaryModel);
    }

    /**
     * Загружает хранилище из JSON
     * @param dump - хранилище в JSON
     * @return - сообщение об успехе/провале загрузки
     */
    public String load(String dump) {
        System.out.println("Dump: " + dump);
        try {
            dictionaryModel = jsonService.load(dump);
            return "Загрузка прошла успешно.";
        } catch (Exception e){
            e.printStackTrace();
            return "Загрузка не удалась.";
        }
    }
}
