package models.concrete;

import com.hp.hpl.jena.ontology.Individual;
import models.ResourceClass;
import models.factory.Factory;

public class Cru extends ResourceClass implements Factory<Cru> {

    public Cru() {
        super();
    }

    public Cru(Individual individual) {
        super(individual, "hasCru");
    }

    @Override
    public Cru factory(Individual individual) {
        return new Cru(individual);
    }

    public String toJson() {
        return "{\"name\":\"" + this.getName() + "\"}";
    }
}
