package org.example;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MageController {
    private MageRepository repository;
    public MageController(MageRepository repository) {
        this.repository = repository;
    }
    public String find(String name) {
        Optional<Mage> opt = repository.find(name);
        if(opt.isEmpty()){
            return "not found";
        }else{
            return opt.get().toString();
        }
    }
    public String delete(String name) {
        try{
            repository.delete(name);
            return "done";
        }catch (IllegalArgumentException e){
            return "not found";
        }
    }
    public String save(String name, int level) {
        try{
            repository.save(new Mage(name, level));
            return "done";
        }catch(IllegalArgumentException e){
            return "bad request";
        }
    }
}
