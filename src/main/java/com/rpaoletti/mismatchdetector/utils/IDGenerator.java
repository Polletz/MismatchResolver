package com.rpaoletti.mismatchdetector.utils;

public class IDGenerator {
    int uniqueId;

    public IDGenerator(){
        uniqueId = 0;
    }

    public int getUniqueId()
    {
        return uniqueId++;
    }
}
