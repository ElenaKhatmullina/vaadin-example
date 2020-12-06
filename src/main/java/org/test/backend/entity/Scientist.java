/*
package org.test.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;

public class Scientist {

    @JsonProperty("name")
    protected String name;
    @JsonProperty("discovery")
    protected String discovery;
    @JsonProperty("newId")
    protected long newId;
    @JsonIgnore
    protected OrientVertex orientVertex;

    public Scientist() {
    }

    public Scientist(OrientVertex orientVertex) {
        this.orientVertex = orientVertex;
        this.name = orientVertex.getProperty("name");
        this.discovery = orientVertex.getProperty("discovery");
        System.out.println("orientVertex: "+orientVertex.getProperty("name")+" - "+orientVertex);
        this.newId=orientVertex.getProperty("newId");

    }
    public Scientist(String name,String discovery,long newId) {
        this.name = name;
        this.discovery = discovery;
        this.newId=newId;
    }
    public String getName() {
        return name;
    }

    public void setName(OrientVertex orientVertex) {
        this.name = orientVertex.getProperty("name");
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDiscovery() {
        return discovery;
    }

    public void setDiscovery(OrientVertex orientVertex) {
        this.discovery = orientVertex.getProperty("discovery");
    }
    public void setDiscovery(String discovery) {
        this.discovery = discovery;
    }

    public long getnewId() {
        return newId;
    }

    public void setnewId(OrientVertex orientVertex) {
        this.newId = orientVertex.getProperty("newId");
    }
    public void setnewId(long newId) {
        this.newId = newId;
    }

    */
/*public OrientVertex getOrientVertex() {
        return orientVertex;
    }

    public void setOrientVertex(OrientVertex orientVertex) {
        this.orientVertex = orientVertex;
    }*//*

}
*/
