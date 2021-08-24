package com.rpaoletti.routeparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NamedType {

    private String name;
    private String xmltype;
    private List<NamedType> typeset;
    private String type;

    public NamedType(
            @JsonProperty String name,
            @JsonProperty String xmltype,
            @JsonProperty List<NamedType> typeset,
            @JsonProperty String type
    ) {
        this.name = name;
        this.xmltype = xmltype;
        this.typeset = typeset;
        this.type = type;
    }

    public String getName() {
        return this.name;
    };

    public String getXmltype() {
        return xmltype;
    }

    public List<NamedType> getTypeset() {
        return typeset;
    }

    public boolean isSimple() {
        return type.equals("simple");
    };

    @Override
    public String toString() {
        if(type.equals("simple")) {
            return "NamedType{" +
                    "name='" + name + '\'' +
                    ", xmltype='" + xmltype + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }else{
            return "NamedType{" +
                    "name='" + name + '\'' +
                    ", typeset=" + typeset +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
}