package models;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import play.Play;

public class Vin {

    private Individual vinIndividual;
    private String cepage = null;
    private String cru = null;
    private String cuvee = null;
    private String sol = null;
    private String degre = null;


    public Vin(Individual vinIndividual) {
        this.vinIndividual = vinIndividual;
    }

    public String getName() {
        return vinIndividual.getLocalName();
    }

    public String getCepage() {
        return this.getProperty(cepage, "hasCepage");
    }

    public String getCru() {
        return this.getProperty(cru, "hasCru");
    }

    public String getCuvee() {
        return this.getProperty(cuvee, "hasCuvee");
    }

    public String getSol() {
        return this.getProperty(sol, "hasSol");
    }

    public String getDegre() {
        return this.getProperty(degre, "hasDegreValue");
    }

    private String getProperty(String property, String prefix) {
        if(property == null) {
            Property prop = new PropertyImpl(OntManager.appendPrefix("#" + prefix));
            RDFNode node = vinIndividual.getPropertyValue(prop);

            if(node != null) {
                if(node.isResource()) {
                    property = node.asResource().getLocalName();
                }
                else if(node.isLiteral()) {
                    property = node.asLiteral().getString();
                }
                else if(node.isURIResource()) {
                    property = node.toString();
                }
            }
        }
        return property;
    }
}
