package com.rpaoletti.routeparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpaoletti.routeparser.utils.IDGenerator;
import com.rpaoletti.routeparser.utils.Utils;

import java.util.*;

public class IntegrationArchitecture {

    private @JsonProperty List<IntegrationNode> nodes;
    private @JsonProperty List<Channel> channels;

    private IDGenerator idGenerator;

    public IntegrationArchitecture() {
        nodes = new ArrayList<>();
        channels = new ArrayList<>();
        idGenerator = new IDGenerator();
    }

    public void insertNode(IntegrationNode node){
        node.setId(idGenerator.getUniqueId());
        nodes.add(node);
    }

    public void insertChannel(Channel c){
        channels.add(c);
    }

    public List<IntegrationNode> getNodes() {
        return nodes;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels){
        this.channels = channels;
    }

    private void adaptToSimple(Channel c, NamedType ts){
        List<IntegrationNode> newNodes = new ArrayList<>();
        List<Channel> newChannels = new ArrayList<>();

        IntegrationNode t = new IntegrationNode(
                "translator",
                List.of(ts),
                List.of(c.getDestType())
        );
        t.setId(idGenerator.getUniqueId());

        newNodes.add(t);
        newChannels.add(new Channel(
                t.getId(),
                c.getDestType(),
                c.getDest(),
                c.getDestType()
        ));
        if(c.getSourceType().isSimple()){
            newChannels.add(
              new Channel(
                      c.getSource(),
                      c.getDestType(),
                      c.getDest(),
                      c.getDestType()
              )
            );
        }else{
            IntegrationNode cf = new IntegrationNode(
                    "contentFilter",
                    List.of(c.getSourceType()),
                    List.of(ts)
            );
            cf.setId(idGenerator.getUniqueId());

            newNodes.add(cf);
            newChannels.add(
                    new Channel(
                            c.getSource(),
                            c.getSourceType(),
                            cf.getId(),
                            c.getSourceType()
                    )
            );
            newChannels.add(
                    new Channel(
                            cf.getId(),
                            ts,
                            t.getId(),
                            ts
            ));
        }
        nodes = Utils.union(nodes, newNodes);
        //channels.remove(c);
        channels = Utils.union(channels, newChannels);
    }

    private void adaptToComposite(Channel c, Map<SimpleNamedType, SimpleNamedType> chosenSimilarSet){
        List<IntegrationNode> newNodes = new ArrayList<>();
        List<Channel> newChannels = new ArrayList<>();

        CompositeNamedType filterSet = new CompositeNamedType("filterSet", new ArrayList<>());
        CompositeNamedType translatedFilterSet = new CompositeNamedType("translatedFilterSet", new ArrayList<>());

        for(SimpleNamedType t : Utils.leaves((CompositeNamedType) c.getDestType())){
            filterSet.getTypeSet().add(chosenSimilarSet.get(t));
            translatedFilterSet.getTypeSet().add(t);
        }

        IntegrationNode cf = new IntegrationNode(
                "contentFilter",
                List.of(c.getSourceType()),
                filterSet.getTypeSet()
        );
        cf.setId(idGenerator.getUniqueId());

        IntegrationNode t = new IntegrationNode(
                "translator",
                filterSet.getTypeSet(),
                translatedFilterSet.getTypeSet()
        );
        t.setId(idGenerator.getUniqueId());

        IntegrationNode w = new IntegrationNode(
                "translator",
                translatedFilterSet.getTypeSet(),
                List.of(c.getDestType())
        );
        w.setId(idGenerator.getUniqueId());

        newNodes.addAll(List.of(cf, t, w));
        newChannels.addAll(List.of(
                new Channel(c.getSource(),c.getSourceType(),cf.getId(),c.getSourceType()),
                new Channel(cf.getId(),filterSet,t.getId(),filterSet),
                new Channel(t.getId(),translatedFilterSet,w.getId(),translatedFilterSet),
                new Channel(w.getId(),c.getDestType(),c.getDest(),c.getDestType())
        ));

        nodes = Utils.union(nodes, newNodes);
        //channels.remove(c);
        channels = Utils.union(channels, newChannels);
    }

    public void mismatchResolver(){
        List<Channel> channelsToRemove = new ArrayList<>();
        for (Channel c : channels){
            if (!Utils.isCompatible(c.getSourceType(),c.getDestType()) && Utils.isAdaptable(c.getSourceType(), c.getDestType())) {
                channelsToRemove.add(c);
                if (c.getDestType().isSimple()){
                    SimpleNamedType ts;
                    if (c.getSourceType().isSimple()) ts = (SimpleNamedType) c.getSourceType();
                    else ts = Utils.similarSet((SimpleNamedType) c.getDestType(), c.getSourceType()).get(0);
                    adaptToSimple(c, ts);
                }else{
                    var simsets = Utils.similarSets(c.getSourceType(),(CompositeNamedType) c.getDestType());
                    Map<SimpleNamedType, SimpleNamedType> chosenSimilarSet = new HashMap<>();
                    for (var e : simsets.entrySet())
                        chosenSimilarSet.put(e.getKey(), e.getValue().get(0));

                    adaptToComposite(c, chosenSimilarSet);
                }
            }
        }
        for (Channel c : channelsToRemove)
            channels.remove(c);
    }

    @Override
    public String toString() {
        return "IntegrationArchitecture{" +
                "nodes=" + nodes +
                ", channels=" + channels +
                '}';
    }
}
