package com.rpaoletti.mismatchdetector.architecture;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleNamedType implements NamedType {

    private String name;
    private String XMLType;

    public SimpleNamedType(
            @JsonProperty String name,
            @JsonProperty String XMLType
    ) {
        this.name = name;
        this.XMLType = XMLType;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getXMLType() {
        return XMLType;
    }

    @Override
    public boolean isSimple() {
        return true;
    }

    @Override
    public String toString() {
        return "SimpleNamedType{" +
                "name='" + name + '\'' +
                ", XMLType='" + XMLType + '\'' +
                '}';
    }
}
