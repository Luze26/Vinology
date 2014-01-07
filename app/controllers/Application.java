package controllers;

import com.hp.hpl.jena.query.QueryParseException;
import play.mvc.*;

import models.*;

import java.util.List;

public class Application extends Controller {

    public static void index() {
        List<Vin> vins = VinManager.getVins();
        render(vins);
    }

    public static void vin() {
        String name = params.get("name");
        Vin vin = VinManager.findVin(name);
        render(vin);
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