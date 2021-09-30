package ru.MYarosh.DictionaryServer.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Представляет объект хранения вместе с ключом
 */
@AllArgsConstructor
public
class DictionaryData {

    @Getter
    @Setter
    private DictionaryValue value;
    @Getter
    @Setter
    private String key;

    @Override
    public String toString(){
        return key+" "+value;
    }
}
