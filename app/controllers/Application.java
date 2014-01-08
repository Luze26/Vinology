package controllers;

import com.hp.hpl.jena.query.QueryParseException;
import models.concrete.Cepage;
import models.concrete.Vin;
import play.mvc.*;

import models.*;

import java.util.List;

public class Application extends Controller {

    public static void index() {
        render();
    }
}