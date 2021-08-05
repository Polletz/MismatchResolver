package com.rpaoletti.routeparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class IntegrationNode {

    private int id;
    private String sort;
    private List<NamedType> inputs;
    private List<NamedType> outputs;

    public IntegrationNode(
            @JsonProperty int id,
            @JsonProperty String sort,
            @JsonProperty List<NamedType> inputs,
            @JsonProperty List<NamedType> outputs
    ) {
        this.id = id;
        this.sort = sort;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public IntegrationNode(
            @JsonProperty String sort,
            @JsonProperty List<NamedType> inputs,
            @JsonProperty List<NamedType> outputs
    ) {
        this.sort = sort;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getSort() {
        return sort;
    }

    public List<NamedType> getInputs() {
        return inputs;
    }

    public List<NamedType> getOutputs() {
        return outputs;
    }

    public void insertInput(NamedType input){
        inputs.add(input);
    }

    public void insertOutput(NamedType output){
        outputs.add(output);
    }

    @Override
    public String toString() {
        return "IntegrationNode{" +
                "id=" + id +
                ", sort='" + sort + '\'' +
                ", inputs=" + inputs +
                ", outputs=" + outputs +
                '}';
    }
}
