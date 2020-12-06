package org.test.backend.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.tinkerpop.blueprints.impls.orient.OrientVertex

class Scientist() {

    var name:String=""
    var discovery:String=""
    var newId :Long=0
    @JsonIgnore
    var orientVertex :OrientVertex= OrientVertex()


    constructor( _orientVertex: OrientVertex=OrientVertex())
    :this(){
        this.orientVertex=_orientVertex
        this.name = orientVertex.getProperty("name")?:""
        this.discovery = orientVertex.getProperty("discovery")?:""
        this.newId = orientVertex.getProperty("newId")?:0
    }

}

