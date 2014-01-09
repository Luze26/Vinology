package models.concrete;

import com.hp.hpl.jena.ontology.Individual;
import models.OntManager;
import models.ResourceClass;
import models.factory.Factory;

import java.util.List;

public class Producteur extends ResourceClass implements Factory<Producteur> {

    private String realName = null;

    public Producteur() {
        super();
    }

    public Producteur(Individual individual) {
        super(individual, "hasProducteur");
    }

    @Override
    public Producteur factory(Individual individual) {
        return new Producteur(individual);
    }

    public String getRealName() {
        return this.getProperty(realName, "hasName");
    }

    public List<Cepage> getCepages() {
        return OntManager.getResourcesFromQuery(new Cepage(), getCepagesQuery(), "cepage");
    }

    public String toJson() {
        return "{\"name\":\"" + this.getName() + "\", \"realName\":\"" + this.getRealName() + "\"}";
    }

    private String getCepagesQuery() {
        return  "prefix vin: <http://www.vin.com/ontologies/vin.owl#>\n"+
                "select distinct ?cepage\n"+
                "where { ?vin vin:hasProducteur vin:" + this.getName() + " .\n" +
                "?vin vin:hasCepage ?cepage . }";
    }
}
