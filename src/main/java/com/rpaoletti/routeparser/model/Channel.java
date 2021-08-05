package com.rpaoletti.routeparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Channel {

    private final int source;
    private NamedType sourceType;
    private final int dest;
    private NamedType destType;

    public Channel(
            @JsonProperty int source,
            @JsonProperty NamedType sourceType,
            @JsonProperty int dest,
            @JsonProperty NamedType destType
    ) {
        this.source = source;
        this.sourceType = sourceType;
        this.dest = dest;
        this.destType = destType;
    }

    public int getSource() {
        return source;
    }

    public NamedType getSourceType() {
        return sourceType;
    }

    public int getDest() {
        return dest;
    }

    public NamedType getDestType() {
        return destType;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "source=" + source +
                ", sourceType=" + sourceType +
                ", dest=" + dest +
                ", destType=" + destType +
                '}';
    }
}
