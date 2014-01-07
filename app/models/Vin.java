package models;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import play.Play;

public class Vin {

    private Individual vinIndividual;

    public Vin(Individual vinIndividual) {
        this.vinIndividual = vinIndividual;
    }

    public String getName() {
        return vinIndividual.getLocalName();
    }

    public String getCepage() {
        Property prop = new PropertyImpl(OntManager.appendPrefix("#hasCepage"));
        RDFNode node = vinIndividual.getPropertyValue(prop);
        return node.toString();
    }
}
