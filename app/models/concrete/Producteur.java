package models.concrete;

import com.hp.hpl.jena.ontology.Individual;
import models.ResourceClass;
import models.factory.Factory;

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

    public String toJson() {
        return "{\"name\":\"" + this.getName() + "\", \"realName\":\"" + this.getRealName() + "\"}";
    }
}
