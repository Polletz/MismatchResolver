package com.rpaoletti.mismatchresolver.service;

import com.rpaoletti.mismatchresolver.model.Channel;
import com.rpaoletti.mismatchresolver.model.IntegrationArchitecture;
import com.rpaoletti.mismatchresolver.model.IntegrationNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegrationService {

    private final IntegrationArchitecture architecture;

    @Autowired
    public IntegrationService() {
        this.architecture = new IntegrationArchitecture();
    }

    public List<IntegrationNode> getNodes(){
        return architecture.getNodes();
    }

    public List<Channel> getChannels(){
        return architecture.getChannels();
    }

    public void addNode(IntegrationNode n){
        this.architecture.insertNode(n);
    }

    public void addChannel(Channel c){
        this.architecture.insertChannel(c);
    }
}
