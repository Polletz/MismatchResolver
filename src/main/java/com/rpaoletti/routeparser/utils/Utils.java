package com.rpaoletti.routeparser.utils;

import com.rpaoletti.routeparser.model.CompositeNamedType;
import com.rpaoletti.routeparser.model.NamedType;
import com.rpaoletti.routeparser.model.SimpleNamedType;

import java.util.*;

public class Utils {

    public static boolean isCastable(SimpleNamedType t1, SimpleNamedType t2){
        return t1.getName().equals(t2.getName());
    }

    public static boolean isSemanticallySimilar(SimpleNamedType t1, SimpleNamedType t2){
        return t1.getXMLType().equals(t2.getXMLType());
    }

    public static <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<>(set);
    }

    public static List<SimpleNamedType> leaves(CompositeNamedType t){
        List<SimpleNamedType> leaves = new ArrayList<>();
        for(NamedType u : t.getTypeSet()){
            if(u.isSimple()) leaves.add((SimpleNamedType) u);
            else leaves = union(leaves, leaves((CompositeNamedType) u));
        }
        return leaves;
    }

    public static List<SimpleNamedType> similarSet(SimpleNamedType t1, NamedType t2){
        List<SimpleNamedType> set = new ArrayList<>();

        if(t2.isSimple()){
            if(Utils.isSemanticallySimilar(t1,(SimpleNamedType) t2) && Utils.isCastable(t1,(SimpleNamedType) t2)){
                set.add((SimpleNamedType) t2);
            }
            return set;
        }else{
            for (NamedType t : ((CompositeNamedType) t2).getTypeSet()){
                set = Utils.union(set, similarSet(t1, t));
            }
        }
        return set;
    }

    public static Map<SimpleNamedType, List<SimpleNamedType>> similarSets(NamedType t1, CompositeNamedType t2){
        Map<SimpleNamedType, List<SimpleNamedType>> simSets = new HashMap<>();
        if(t1.isSimple()){
            simSets.put((SimpleNamedType) t1, similarSet((SimpleNamedType) t1, t2));
        }else{
            for (NamedType t : ((CompositeNamedType) t1).getTypeSet()){
                simSets.putAll(similarSets(t, t2));
            }
        }
        return simSets;
    }

    public static boolean isAdaptable(NamedType t1, NamedType t2){
        if(t2.isSimple()){
            if(!similarSet((SimpleNamedType) t2,t1).isEmpty()) return true;
            else return false;
        }else{
            for (var e : similarSets(t2,(CompositeNamedType) t1).entrySet()){
                if(e.getValue().isEmpty()) return false;
            }
            return true;
        }
    }

    public static boolean isCompatible(NamedType t1, NamedType t2) {
        if (t1.isSimple() && t2.isSimple()) {
            if (t1.getName().equals(t2.getName()) && ((SimpleNamedType) t1).getXMLType().equals(((SimpleNamedType) t2).getXMLType()))
                return true;
            else return false;
        } else if ((t1.isSimple() && !t2.isSimple()) || (!t1.isSimple() && t2.isSimple())) {
            return false;
        } else {
            if (!t1.getName().equals(t2.getName())) return false;
            boolean found = false;
            for (NamedType t : ((CompositeNamedType) t2).getTypeSet()) {
                found = false;
                for (NamedType u : ((CompositeNamedType) t1).getTypeSet()) {
                    if (isCompatible(t, u)) {
                        found = true;
                        break;
                    }
                }
                if (!found) return false;
            }
            return true;
        }
    }

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

