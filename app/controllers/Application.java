package controllers;

import com.hp.hpl.jena.query.QueryParseException;
import models.concrete.Cepage;
import models.concrete.Vin;
import play.mvc.*;

import models.*;

import java.util.List;

public class Application extends Controller {

    public static void index() {
        List<Vin> vins = ClassManager.getOntClassResources(new Vin(), "Vin");
        List<Cepage> cepages = ClassManager.getOntClassResources(new Cepage(), "VinCepage");
        render(vins, cepages);
    }

    public static void vin() {
        String name = params.get("name");
        Vin vin = ClassManager.findEntity(new Vin(), name);
        render(vin);
    }

    public static void cepage() {
        String name = params.get("name");
        Cepage cepage = ClassManager.findEntity(new Cepage(), name);
        render(cepage);
    }

    public static void query() {
        render();
    }

    public static void executeQuery() {
        String query = params.get("body");

        try {
            OntManager.executeQuery(query, response.out);
        }
        catch(QueryParseException ex) {
            response.status = 400;
            renderJSON("{\"error\": \"Parse Error\"}");
        }
    }
}