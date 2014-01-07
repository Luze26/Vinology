package models;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;

public abstract class ResourceClass {

    protected Individual individual;

    public ResourceClass(Individual individual) {
        this.individual = individual;
    }

    public String getName() {
        return individual.getLocalName();
    }

    protected String getProperty(String property, String prefix) {
        if(property == null) {
            Property prop = new PropertyImpl(OntManager.appendPrefix("#" + prefix));
            RDFNode node = individual.getPropertyValue(prop);

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
