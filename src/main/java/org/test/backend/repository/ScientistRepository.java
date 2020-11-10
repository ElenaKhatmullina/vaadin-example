package org.test.backend.repository;


import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import org.test.backend.entity.Scientist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScientistRepository {

    private static ScientistRepository instance;
   private OrientGraphFactory orientGraphFactory = new OrientGraphFactory("remote:localhost/ScientistBase", "root", "root").setupPool(2,10);

    private ScientistRepository(){

    }

    public static ScientistRepository getInstance() {
        if (instance == null) {
            instance = new ScientistRepository();
        }
        return instance;
    }

    public Iterable<Vertex> findAll() {
        OrientGraph orientGraph = orientGraphFactory.getTx();
        Iterable<Vertex> vertices = orientGraph.command(new OCommandSQL("select * from Scientist")).execute();
        orientGraph.shutdown();

            return vertices;

    }
    public List<OrientVertex> findPage(int fromIndex, int limit) {
        OrientGraph orientGraph = orientGraphFactory.getTx();
/*
        Iterable<Vertex> vertices = orientGraph.command(new OCommandSQL("select from Scientist SKIP ? LIMIT ?")).execute(fromIndex, limit);
*/
/*
        Iterable<Vertex> vertices = orientGraph.command(new OCommandSQL("select from Scientist where newId > ? LIMIT ?")).execute(fromIndex, limit);
*/
        List<OrientVertex> vertices=new ArrayList<>();
        Iterable<Vertex> indexVertices = orientGraph.command(new OCommandSQL("SELECT FROM INDEX:indexforNEWID where key > ? LIMIT ?")).execute((fromIndex-1), limit);
        OrientVertex indexVertex;
        for (Object oo : indexVertices) {
            indexVertex = (OrientVertex) oo;
            OrientVertex v1 = indexVertex.getProperty("rid");
            vertices.add(v1);
        }
        orientGraph.shutdown();

        return vertices;
    }
    public void addAll(Map<String, String> scientistsAndTheirDiscovery) {
        OrientGraph orientGraph = orientGraphFactory.getTx();

        orientGraph.command(new OCommandSQL("CREATE SEQUENCE idseq TYPE ORDERED")).execute();


        for (Map.Entry<String, String> entry : scientistsAndTheirDiscovery.entrySet()) {

            OrientVertex vertexScientist = orientGraph.command(new OCommandSQL("insert into Scientist set name = ?, discovery = ?, newId = sequence('idseq').next()")).execute(entry.getKey(),entry.getValue());

            orientGraph.commit();
            System.out.println("новый узел: "+vertexScientist.getProperty("newId")+" - "+vertexScientist.getProperty("name")+" - "+vertexScientist.getProperty("discovery"));
        }
        orientGraph.shutdown();
    }

    public void deleteAll() {
        OrientGraph orientGraph = orientGraphFactory.getTx();

        orientGraph.command(new OCommandSQL("DELETE VERTEX  Scientist ")).execute();
        orientGraph.shutdown();
    }


    public void add(Scientist  scientist) {

        if (scientist == null) {
            return;
        }
        OrientGraph orientGraph = orientGraphFactory.getTx();
        String newName=scientist.getName();
        String newDiscovery=scientist.getDiscovery();
        if(((newName!=null)&&(newDiscovery!=null))&&(!newName.equals(""))&&(!newDiscovery.equals(""))) {
            OrientVertex vertexScientist = orientGraph.command(new OCommandSQL("insert into Scientist set name = ?, discovery = ?, newId = sequence('idseq').next()")).execute(scientist.getName(),scientist.getDiscovery());

/*
            orientGraph.commit();
*/

/*
            updateIndexOnNewId();
*/

            orientGraph.commit();

            System.out.println("добавлен: " + vertexScientist.getProperty("name") + " - " + vertexScientist.getProperty("discovery") + " - " + vertexScientist.getProperty("newId"));
        }
        else{
            System.out.println("null!!!:  "+scientist);
        }
        orientGraph.shutdown();

    }
    public long  getCount()
    {
        OrientGraph orientGraph = orientGraphFactory.getTx();
        Iterable countIterable= orientGraph.command(new OCommandSQL("select count(*) from Scientist")).execute();

        long count=((Vertex)(countIterable.iterator().next())).getProperty("count");
        orientGraph.shutdown();
        return count;
    }

    public int  getIntCount()
    {
        OrientGraph orientGraph = orientGraphFactory.getTx();
        Iterable countIterable= orientGraph.command(new OCommandSQL("select count(*) from Scientist")).execute();

        int count=((Number)((Vertex)(countIterable.iterator().next())).getProperty("count")).intValue();
        orientGraph.shutdown();
        return count;
    }

    public void createIndexOnNewId(){
        OrientGraph orientGraph = orientGraphFactory.getTx();
        orientGraph.command(new OCommandSQL("CREATE INDEX indexforNEWID ON Scientist (newId) UNIQUE")).execute();
        orientGraph.shutdown();

    }
    public void updateIndexOnNewId(){
        OrientGraph orientGraph = orientGraphFactory.getTx();
        orientGraph.command(new OCommandSQL("REBUILD INDEX indexforNEWID")).execute();
        orientGraph.shutdown();


    }
    public void deleteIndexOnNewId(){
        OrientGraph orientGraph = orientGraphFactory.getTx();
        orientGraph.command(new OCommandSQL("DROP INDEX indexforNEWID")).execute();
        orientGraph.shutdown();

    }
    public void dropSeq(){
        OrientGraph orientGraph = orientGraphFactory.getTx();
        orientGraph.command(new OCommandSQL("DROP SEQUENCE idseq")).execute();
        orientGraph.shutdown();

    }
}