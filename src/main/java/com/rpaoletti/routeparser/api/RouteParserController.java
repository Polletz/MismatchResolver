package com.rpaoletti.routeparser.api;

import com.rpaoletti.routeparser.service.RouteParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/parser")
public class RouteParserController {

    private final RouteParserService routeParserService;

    @Autowired
    public RouteParserController(RouteParserService routeParserService) {
        this.routeParserService = routeParserService;
    }

    @PostMapping(path = "route")
    public void addRoute(@RequestBody String rawRoute){
        routeParserService.addRoute(rawRoute);
    }

    @GetMapping(path = "route")
    public String getCurrentRoute(){
        return routeParserService.getRoute();
    }

}
