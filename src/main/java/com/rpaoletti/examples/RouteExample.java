package com.rpaoletti.examples;

import org.apache.camel.builder.RouteBuilder;

public class RouteExample extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("a")
            .process("b")
            .choice()
                .when("a")
                    .multicast()
                    .to("a1", "a2").endChoice()
                .when("b")
                    .process("b").endChoice()
                .otherwise()
                    .filter("c")
                    .process("c").endChoice()
            .end()
            .to("d");
    }
}