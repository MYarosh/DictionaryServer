package ru.MYarosh.DictionaryServer.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * Представляет собой модель хранилища
 */
public class DictionaryModel {
    @Getter
    @Setter
    private HashMap<String, DictionaryValue> dictionary;

    public DictionaryModel(){
        dictionary = new HashMap<>();
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
