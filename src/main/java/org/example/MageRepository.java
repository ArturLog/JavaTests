package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MageRepository {
    private Map<String, Mage> collection = new HashMap<>();

    public Optional<Mage> find(String name){
        Mage mage = collection.get(name);
        return Optional.ofNullable(mage);
    }

    public void delete(String name){
        if(collection.containsKey(name)){
            collection.remove(name);
            return;
        }
        throw new IllegalArgumentException();
    }

    public void save(Mage mage){
        if (collection.containsKey(mage.getName())){
            throw new IllegalArgumentException();
        }
        else {
            collection.put(mage.getName(), mage);
        }
    }
}
