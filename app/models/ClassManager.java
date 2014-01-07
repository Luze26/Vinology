package models;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import models.factory.Factory;
import play.Play;

import java.util.ArrayList;
import java.util.List;

public class ClassManager {
    private static final String MODEL_PREFIX = Play.configuration.getProperty("ontology.prefix");

    public static <C extends ResourceClass> List<C> getOntClassResources(Factory<C> factory, String prefix) {
        List<C> resources = new ArrayList<C>();
        OntClass ontClass = OntManager.getModel().getOntClass(OntManager.appendPrefix("#" + prefix));
        for (ExtendedIterator<? extends OntResource> it= ontClass.listInstances(); it.hasNext(); ) {
            Individual individual = (Individual) it.next();
            resources.add(factory.factory(individual));
        }

        return resources;
    }

    public static <C extends ResourceClass> C findEntity(Factory<C> factory, String name) {
        Individual individual = OntManager.getModel().getIndividual(OntManager.appendPrefix("#" + name));
        if(individual != null) {
            return factory.factory(individual);
        }
        return null;
    }
}
