package models;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import models.concrete.Vin;

import java.util.List;

public abstract class ResourceClass {

    protected Individual individual;
    private String propertyName = null;

    public ResourceClass() {}

    public ResourceClass(Individual individual, String propertyName) {
        this.individual = individual;
        this.propertyName = propertyName;
    }

    public String getName() {
        return individual.getLocalName();
    }

    public List<Vin> getVins() {
        return OntManager.getResourcesFromQuery(new Vin(), getVinsQuery(), "vin");
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

    protected Boolean isOfClass(Boolean property, String className) {
        if(property == null) {
            for (ExtendedIterator<Resource> it= individual.listRDFTypes(false); it.hasNext(); ) {
                Resource r = it.next();
                if(r.getLocalName().equals(className)) {
                    property = true;
                    return property;
                }
            }
        }
        return property;
    }

    private String getVinsQuery() {
        return  "prefix vin: <http://www.vin.com/ontologies/vin.owl#>\n"+
                "select ?vin\n"+
                "where { ?vin vin:" + propertyName + " vin:" + this.getName() + "}";
    }
}
