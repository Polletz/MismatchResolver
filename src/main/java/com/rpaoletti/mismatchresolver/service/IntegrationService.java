package com.rpaoletti.mismatchresolver.service;

import com.rpaoletti.mismatchresolver.architecture.Channel;
import com.rpaoletti.mismatchresolver.architecture.IntegrationArchitecture;
import com.rpaoletti.mismatchresolver.architecture.IntegrationNode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegrationService {

    private final IntegrationArchitecture architecture;

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
