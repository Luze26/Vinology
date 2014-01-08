package controllers;

import models.OntManager;
import models.concrete.Cepage;
import models.concrete.Vin;
import play.mvc.Controller;

public class Resource extends Controller {

    public static void vin() {
        String name = params.get("name");
        Vin vin = OntManager.findEntity(new Vin(), name);
        render(vin);
    }

    public static void cepage() {
        String name = params.get("name");
        Cepage cepage = OntManager.findEntity(new Cepage(), name);
        render(cepage);
    }
}
