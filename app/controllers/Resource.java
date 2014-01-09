package controllers;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import models.OntManager;
import models.concrete.*;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

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

    public static void cru() {
        String name = params.get("name");
        Cru cru = OntManager.findEntity(new Cru(), name);
        render(cru);
    }

    public static void cuvee() {
        String name = params.get("name");
        Cuvee cuvee = OntManager.findEntity(new Cuvee(), name);
        render(cuvee);
    }

    public static void producteur() {
        String name = params.get("name");
        Producteur producteur = OntManager.findEntity(new Producteur(), name);
        render(producteur);
    }

    public static void sol() {
        String name = params.get("name");
        Sol sol = OntManager.findEntity(new Sol(), name);
        render(sol);
    }

    public static void resource() {
        String uri = params.get("uri");
        String redirection = OntManager.getRedirection(uri);
        if(redirection != null) {
            redirect(redirection);
        }
        OntResource resource = OntManager.getOntResource(uri);

        List<OntClass> subClass = new ArrayList<OntClass>();
        for(ExtendedIterator<OntClass> it = resource.asClass().listSubClasses(true); it.hasNext();) {
            subClass.add(it.next());
        }

        List<OntResource> instances = new ArrayList<OntResource>();
        for(ExtendedIterator<? extends OntResource> it = resource.asClass().listInstances(false); it.hasNext();) {
            instances.add(it.next());
        }

        render(resource, subClass, instances);
    }
}
