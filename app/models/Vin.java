package models;

import com.hp.hpl.jena.ontology.Individual;

public class Vin {

    public String name;

    public Vin(Individual vinIndividual) {
        name = vinIndividual.getLocalName();
    }
}
