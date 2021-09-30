package ru.MYarosh.DictionaryServer.Controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.MYarosh.DictionaryServer.Model.DictionaryData;
import ru.MYarosh.DictionaryServer.Model.DictionaryValue;
import ru.MYarosh.DictionaryServer.Services.DictionaryService;

/**
 * Контроллер, который распределяет REST запросы на нужные методы сервиса
 */
@RestController
@RequestMapping("/DictionaryService")
public class DictionaryController {


    private final DictionaryService dictionaryService;

    /**
     * Конструктор контроллера
     * @param dictionaryService - сервис, которому будут распределены запросы
     */
    public DictionaryController(DictionaryService dictionaryService){
        this.dictionaryService = dictionaryService;
    }

    /**
     * Метод обрабатывает запрос на получение объекта из хранилища
     * @param key - ключ объекта
     * @return - возвращает значение объекта
     */
    @PostMapping("/Get")
    public DictionaryValue get(@RequestBody String key){
        return dictionaryService.get(key);
    }

    /**
     * Метод обрабатывает запрос на добавление/обновление объекта в хранилище
     * @param dictionaryData - добавляемый объект
     * @return - возвращает null при новой записи, старое значение при перезаписи,
     * пустое значение при некорректном объекте
     */
    @PostMapping("/Set")
    public DictionaryValue set(@RequestBody DictionaryData dictionaryData){
        if (dictionaryData.getValue().getTtl() == 0){
            dictionaryData.getValue().setTtl(-1);
        }
        if ((dictionaryData.getKey() == null) || (dictionaryData.getValue() == null)
                || (dictionaryData.getValue().getValue() == null))
            return new DictionaryValue(null,0);
        return dictionaryService.set(dictionaryData);
    }

    /**
     * Метод обрабатывает запрос на удаление объекта
     * @param key - ключ объекта
     * @return - значение удаленного объекта
     */
    @PostMapping("/Remove")
    public DictionaryValue remove(@RequestBody String key){
        return dictionaryService.remove(key);
    }

    /**
     * Метод обрабатывает запрос на создание дампа хранилища
     * @return - содержимое хранилища в JSON
     * @throws Exception - ошибка записи в JSON
     */
    @PostMapping("/Dump")
    public ResponseEntity<byte[]> dump() throws Exception {

        byte[] contents = dictionaryService.dump().getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.json");
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    /**
     * Метод обрабатывает запрос на загрузку хранилища
     * @param dump - содержимое хранилища в JSON
     * @return - сообщение об успехе/провале загрузки
     */
    @PostMapping("/Load")
    public String load(@RequestBody String dump){
        return dictionaryService.load(dump);
    }
}
