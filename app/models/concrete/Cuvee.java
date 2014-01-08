package models.concrete;

import com.hp.hpl.jena.ontology.Individual;
import models.ResourceClass;
import models.factory.Factory;

public class Cuvee extends ResourceClass implements Factory<Cuvee> {

    public Cuvee() {
        super();
    }

    public Cuvee(Individual individual) {
        super(individual, "hasCuvee");
    }

    @Override
    public Cuvee factory(Individual individual) {
        return new Cuvee(individual);
    }

    public String toJson() {
        return "{\"name\":\"" + this.getName() + "\"}";
    }
}
