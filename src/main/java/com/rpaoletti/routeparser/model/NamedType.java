package com.rpaoletti.routeparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NamedType {

    private String name;
    private String XMLType;
    private List<NamedType> typeSet;

    private String type;

    public NamedType(
            @JsonProperty String name,
            @JsonProperty String XMLType,
            @JsonProperty List<NamedType> typeSet,
            @JsonProperty String type
    ) {
        this.name = name;
        this.XMLType = XMLType;
        this.typeSet = typeSet;
        this.type = type;
    }

    public String getName() {
        return this.name;
    };

    public String getXMLType() {
        return XMLType;
    }

    public List<NamedType> getTypeSet() {
        return typeSet;
    }

    public boolean isSimple() {
        return type.equals("simple");
    };

    @Override
    public String toString() {
        if(type.equals("simple")) {
            return "NamedType{" +
                    "name='" + name + '\'' +
                    ", XMLType='" + XMLType + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }else{
            return "NamedType{" +
                    "name='" + name + '\'' +
                    ", typeSet=" + typeSet +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
}