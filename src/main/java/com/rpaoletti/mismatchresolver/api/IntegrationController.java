package com.rpaoletti.mismatchresolver.api;

import com.rpaoletti.mismatchresolver.architecture.Channel;
import com.rpaoletti.mismatchresolver.architecture.IntegrationNode;
import com.rpaoletti.mismatchresolver.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/integration")
public class IntegrationController {

    private final IntegrationService intService;
    private String route;

    @Autowired
    public IntegrationController(IntegrationService intService) {
        this.intService = intService;
    }

    @GetMapping(path = "nodes")
    public List<IntegrationNode> getNodes(){
        return intService.getNodes();
    }

    @GetMapping(path = "channels")
    public List<Channel> getChannels() {
        return intService.getChannels();
    }

    @PostMapping(path = "addNode")
    public void insertNode(@RequestBody IntegrationNode node){
        this.intService.addNode(node);
    }

    @PostMapping(path = "addChannel")
    public void insertChannel(@RequestBody Channel c){
        this.intService.addChannel(c);
    }

    @PostMapping(path = "insertRoute")
    public void insertRoute(@RequestBody String route){
        this.route = route;
        System.out.println("new route : " + this.route);
    }
}
