package models.concrete;

import com.hp.hpl.jena.ontology.Individual;
import models.ResourceClass;
import models.factory.Factory;

public class Cuvee extends ResourceClass implements Factory<Cuvee> {

    private String year = null;
    private Boolean old = null;

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

    public String getYear() {
        return this.getProperty(year, "hasYear");
    }

    public Boolean isOld() {
        return this.isOfClass(old, "OldCuvee");
    }

    public String toJson() {
        return "{\"name\":\"" + this.getName() + "\", \"year\":" + this.getYear() + "}";
    }
}
