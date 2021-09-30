package ru.MYarosh.DictionaryServer.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@AllArgsConstructor
public class DictionaryValue {

    @Getter
    @Setter
    Object value;

    @Getter
    @Setter
    int ttl;

    /**
     * Уменьшает положительное значение ttl до 0
     */
    public void decreaseTtl(){
        if (ttl>0) ttl--;
    }

    @Override
    public String toString(){
        return value.toString()+" "+ttl;
    }
}
