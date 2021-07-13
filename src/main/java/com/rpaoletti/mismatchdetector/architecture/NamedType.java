package com.rpaoletti.mismatchdetector.architecture;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface NamedType {

    public String getName();
    public boolean isSimple();
    public String toString();

}
