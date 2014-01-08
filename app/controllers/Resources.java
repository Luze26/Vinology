package controllers;

import models.OntManager;
import models.concrete.Cepage;
import models.concrete.Producteur;
import models.concrete.Vin;
import play.mvc.*;

import java.util.List;

public class Resources extends Controller {

    public static void vins() {
        render();
    }

    public static void vinsApi() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        List<Vin> vins = OntManager.getOntClassResources(new Vin(), "Vin");
        for(Vin vin: vins) {
            sb.append(vin.toJson());
            sb.append(',');
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(']');
        renderJSON(sb.toString());
    }

    public static void cepages() {
        render();
    }

    public static void cepagesApi() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        List<Cepage> cepages = OntManager.getOntClassResources(new Cepage(), "VinCepage");
        for(Cepage cepage: cepages) {
            sb.append(cepage.toJson());
            sb.append(',');
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(']');
        renderJSON(sb.toString());
    }

    public static void producteurs() {
        render();
    }

    public static void producteursApi() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        List<Producteur> producteurs = OntManager.getOntClassResources(new Producteur(), "VinProducteur");
        for(Producteur producteur: producteurs) {
            sb.append(producteur.toJson());
            sb.append(',');
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(']');
        renderJSON(sb.toString());
    }
}
