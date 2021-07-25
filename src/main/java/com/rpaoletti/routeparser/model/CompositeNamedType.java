package com.rpaoletti.routeparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CompositeNamedType implements NamedType {

    private String name;
    private List<NamedType> typeSet;

    public CompositeNamedType(
            @JsonProperty String name,
            @JsonProperty List<NamedType> typeSet
    ) {
        this.name = name;
        this.typeSet = typeSet;
    }

    public CompositeNamedType(
            @JsonProperty String name
    ) {
        this.name = name;
    }

    public List<NamedType> getTypeSet() {
        return typeSet;
    }

    public void addType(NamedType t){
        typeSet.add(t);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    @Override
    public String toString() {
        return "CompositeNamedType{" +
                "name='" + name + '\'' +
                ", typeSet=" + typeSet +
                '}';
    }
}
