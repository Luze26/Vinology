package controllers;

import com.hp.hpl.jena.query.QueryParseException;
import models.OntManager;
import play.mvc.*;

public class Query extends Controller {

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
