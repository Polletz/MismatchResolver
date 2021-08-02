package com.rpaoletti.routeparser.api;

import com.rpaoletti.routeparser.RouteParser;
import com.rpaoletti.routeparser.model.Channel;
import com.rpaoletti.routeparser.model.IntegrationNode;
import com.rpaoletti.routeparser.service.RouteParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api")
public class RouteParserController {

    //TODO creare api che restituisce nodi e canali creati dalla route in input

    private final RouteParserService routeParserService;

    @Autowired
    public RouteParserController(RouteParserService routeParserService) {
        this.routeParserService = routeParserService;
    }

    @PostMapping(path = "parser/route")
    public void addRoute(@RequestBody RouteParser parser){
        routeParserService.addRouteParser(parser);
        routeParserService.parseRoute();
    }

    @GetMapping(path = "parser/route")
    public String getCurrentRoute(){
        return routeParserService.getRoute();
    }

    @GetMapping(path = "integration/nodes")
    public List<IntegrationNode> getNodes(){
        return routeParserService.getNodes();
    }

    @GetMapping(path = "integration/channels")
    public List<Channel> getChannels() {
        return routeParserService.getChannels();
    }

    @PostMapping(path = "integration/addNode")
    public void insertNode(@RequestBody IntegrationNode node){
        this.routeParserService.addNode(node);
    }

    @PostMapping(path = "integration/addChannel")
    public void insertChannel(@RequestBody Channel c){
        this.routeParserService.addChannel(c);
    }

    @GetMapping(path = "parser/integration")
    public String getIntegration(){
        return routeParserService.getIntegration();
    }

}
