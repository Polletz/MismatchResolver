package com.rpaoletti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MismatchResolverApplication {

    public static void main(String[] args) {
/*
        CompositeNamedType ct1 = new CompositeNamedType("book", List.of(
                new SimpleNamedType("title", "string"),
                new SimpleNamedType("year", "int"),
                new CompositeNamedType("author", List.of(
                        new SimpleNamedType("name", "string"),
                        new SimpleNamedType("nationality", "string")
                )),
                new SimpleNamedType("price", "int")
        ));

        CompositeNamedType ct2 = new CompositeNamedType("novel", List.of(
                new SimpleNamedType("title", "string"),
                new SimpleNamedType("year", "int"),
                new CompositeNamedType("author", List.of(
                        new SimpleNamedType("name", "string"),
                        new SimpleNamedType("nationality", "string")
                )),
                new SimpleNamedType("price", "int")
        ));

        SimpleNamedType st1 = new SimpleNamedType("book", "string");

        IntegrationArchitecture A = new IntegrationArchitecture();

        IntegrationNode source = new IntegrationNode(
                "source",
                new ArrayList<>(),
                List.of(ct1)
        );
        IntegrationNode dest = new IntegrationNode(
                "dest",
                List.of(ct2),
                new ArrayList<>()
        );

        A.insertNode(source);
        A.insertNode(dest);

        A.insertChannel(new Channel(
                source.getId(),
                ct1,
                dest.getId(),
                ct2
        ));

        System.out.println("A before adaptation");
        for (IntegrationNode n : A.getNodes())
            System.out.println(n.getSort() + ", " + n.getId());

        for (Channel c : A.getChannels())
            System.out.println(c.getSource() + ", " + c.getDest());

        //SimpleNamedType ts = new SimpleNamedType("title", "string");
        Map<SimpleNamedType, SimpleNamedType> chosenSimilarSet = new HashMap<>();
        chosenSimilarSet.put(new SimpleNamedType("title", "string"), new SimpleNamedType("title", "string"));
        chosenSimilarSet.put(new SimpleNamedType("year", "string"), new SimpleNamedType("year", "string"));
        chosenSimilarSet.put(new SimpleNamedType("name", "string"), new SimpleNamedType("name", "string"));
        chosenSimilarSet.put(new SimpleNamedType("nationality", "string"), new SimpleNamedType("nationality", "string"));
        chosenSimilarSet.put(new SimpleNamedType("price", "int"), new SimpleNamedType("price", "int"));

        A.adaptToComposite(A.getChannels().get(0), chosenSimilarSet);

        A.mismatchResolver();

        System.out.println("A after adaptation");
        for (IntegrationNode n : A.getNodes())
            System.out.println(n.getSort() + ", " + n.getId());

        for (Channel c : A.getChannels())
            System.out.println(c.getSource() + ", " + c.getDest());

        for (SimpleNamedType s : Utils.leaves(ct1))
            System.out.println(s.getName() + ", " + s.getXMLType());

        try {
            String route = new String(Files.readAllBytes(Paths.get("src\\main\\java\\com\\rpaoletti\\examples\\RouteExample.java")), StandardCharsets.UTF_8);
            RouteParser r = new RouteParser(route);
            r.parseRoute();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        try {
            SpringApplication.run(MismatchResolverApplication.class, args);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
}
