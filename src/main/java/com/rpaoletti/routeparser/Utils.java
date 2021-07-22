package com.rpaoletti.routeparser;

import com.rpaoletti.mismatchresolver.utils.NODE_TYPE;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static final List<String> NodeCommands = List.of(
            "from",
            "to",
            "choice",
            "bean",
            "aggregate",
            "split",
            "process",
            "filter",
            "convertBodyTo",
            "enrich",
            "wireTap",
            "setHeader",
            "setProperty",
            "endChoice",
            "end",
            "when",
            "otherwise"
    );

    public static String matchCommand(String command){
        switch(command){
            case "from":
                return NODE_TYPE.ENTRYPOINT.toString();
            case "wireTap":
            case "to":
                return NODE_TYPE.ENDPOINT.toString();
            case "choice":
                return NODE_TYPE.ROUTER.toString();
            case "bean":
                return NODE_TYPE.COMPONENT.toString();
            case "aggregate":
                return NODE_TYPE.AGGREGATOR.toString();
            case "split":
                return NODE_TYPE.SPLITTER.toString();
            case "process":
                return NODE_TYPE.PROCESSOR.toString();
            case "filter":
                return NODE_TYPE.FILTER.toString();
            case "convertBodyTo":
            case "setHeader":
            case "setProperty":
                return NODE_TYPE.MESSAGE_TRANSFORMER.toString();
            case "enrich":
                return NODE_TYPE.CONTENT_ENRICHER.toString();
            default:
                return null;
        }
    }
}

class ChoiceStruct {
    int lastID;
    int lastChoiceID;
    List<Integer> leaves;

    public ChoiceStruct(int lastID) {
        this.lastID = lastID;
        this.lastChoiceID = lastID;
        leaves = new ArrayList<>();
    }
}