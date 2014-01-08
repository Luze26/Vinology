package models.concrete;

import com.hp.hpl.jena.ontology.Individual;
import models.ResourceClass;
import models.factory.Factory;

public class Sol extends ResourceClass implements Factory<Sol> {

    public Sol() {
        super();
    }

    public Sol(Individual individual) {
        super(individual, "hasSol");
    }

    @Override
    public Sol factory(Individual individual) {
        return new Sol(individual);
    }

    public String toJson() {
        return "{\"name\":\"" + this.getName() + "\"}";
    }
}
