package org.test.backend.util;

import org.test.backend.repository.ScientistRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PopulateDb {

    private ScientistRepository scientistRepository;

    private  PopulateDb(){
        this.scientistRepository=ScientistRepository.getInstance();
    }

    protected void addAll(Map<String, String> scientistsAndTheirDiscovery ){
        this.scientistRepository.addAll(scientistsAndTheirDiscovery);

    }
    protected void deleteAll(){
        this.scientistRepository.deleteAll();
    }

    protected void createIndexOnNewId(){
        this.scientistRepository.createIndexOnNewId();
    }

    protected void dropIndexOnNewId(){
        this.scientistRepository.deleteIndexOnNewId();
    }

    protected void dropSeq(){
        this.scientistRepository.dropSeq();
    }

    public static void main(String[] args) {
        PopulateDb populateDb=new PopulateDb();

        Map<String, String> scientistsAndTheirDiscovery=new HashMap<>();

        scientistsAndTheirDiscovery.put("Ада Лавлейс","первая в мире программа");
        scientistsAndTheirDiscovery.put("Альберт Эйнштейн","теория относительности");
        scientistsAndTheirDiscovery.put("Константин Циолковский","теория космонавтики");
        scientistsAndTheirDiscovery.put("Мария Кюри","радиоактивность");
        scientistsAndTheirDiscovery.put("Никола Тесла","переменный ток");
        scientistsAndTheirDiscovery.put("Михаил Ломоносов","физическая химия");

        String firstString="";
        String secondString="";

        for(int i=0;i<1000000;i++){
            firstString=GFG.getAlphaNumericString(20);
            secondString=GFG.getAlphaNumericString(20);
            scientistsAndTheirDiscovery.put(firstString,secondString);
        }
        populateDb.dropSeq();
        populateDb.dropIndexOnNewId();
        populateDb.deleteAll();
        populateDb.addAll(scientistsAndTheirDiscovery);
        populateDb.createIndexOnNewId();
    }
}
