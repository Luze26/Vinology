package models.concrete;

import com.hp.hpl.jena.ontology.Individual;
import models.ResourceClass;
import models.factory.Factory;

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
