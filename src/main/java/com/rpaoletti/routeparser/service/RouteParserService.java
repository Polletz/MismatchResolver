package com.rpaoletti.routeparser.service;

import com.rpaoletti.routeparser.RouteParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteParserService {

    private RouteParser routeParser;

    @Autowired
    public RouteParserService() {
    }

    public void addRoute(String route){
        routeParser = new RouteParser(route);
    }

    public String getRoute(){
        return routeParser.getRoute();
    }
}
