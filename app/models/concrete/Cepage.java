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
        super(null);
    }

    public Cepage(Individual individual) {
        super(individual);
    }

    @Override
    public Cepage factory(Individual individual) {
        return new Cepage(individual);
    }

    public List<Vin> getVins() {
        List<Vin> vins = new ArrayList<Vin>();
        Property prop = new PropertyImpl(OntManager.appendPrefix("#Vin"));
        individual.getOntClass().listIsDefinedBy();
        System.out.println("AAAA");

        for (ExtendedIterator<? extends RDFNode> it= individual.getOntClass().listIsDefinedBy(); it.hasNext(); ) {
            System.out.println("LLLLLL");
            it.next();
        }

        return vins;
    }
}
