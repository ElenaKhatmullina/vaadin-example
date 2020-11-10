package org.test.backend.service;

import com.orientechnologies.orient.core.id.ORID;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;

import org.test.backend.entity.Scientist;
import org.test.backend.repository.ScientistRepository;

import java.util.ArrayList;
import java.util.List;


public class ScientistService {

    private ScientistRepository scientistRepository=ScientistRepository.getInstance();
    private static ScientistService instance;

    private ScientistService(){

    }
    public static ScientistService getInstance() {
        if (instance == null) {
            instance = new ScientistService();
        }
        return instance;
    }

    public ScientistService( ScientistRepository scientistRepository){
        this.scientistRepository=scientistRepository;
    }

    public List<Scientist> findAll() {
        List <Scientist> scientists=new ArrayList<>();

        Iterable iterable=scientistRepository.findAll();

        for (Object o : iterable) {
            OrientVertex ov = (OrientVertex) o;
            scientists.add(new Scientist(ov));
        }

        return scientists;

    }


    public List<Scientist> findPage(int fromIndex,int limit) {
        List <Scientist> scientists=new ArrayList<>();

        List <OrientVertex> list=scientistRepository.findPage(fromIndex,limit);

        for (OrientVertex ov : list) {
            scientists.add(new Scientist(ov));
            System.out.println("выбрали: "+ov.getId());

        }
        return scientists;

    }

    public void add(Scientist  scientist){
        scientistRepository.add(scientist);

    }
    public long getCount (){
      return  scientistRepository.getCount();
    }
}
