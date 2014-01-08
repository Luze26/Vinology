package models.concrete;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import models.OntManager;
import models.ResourceClass;
import models.factory.Factory;

import java.util.ArrayList;
import java.util.List;

public class Cepage extends ResourceClass implements Factory<Cepage> {

    public Cepage() {
        super();
    }

    public Cepage(Individual individual) {
        super(individual, "hasCepage");
    }

    @Override
    public Cepage factory(Individual individual) {
        return new Cepage(individual);
    }

    public String toJson() {
        return "{\"name\":\"" + this.getName() + "\"}";
    }
}
