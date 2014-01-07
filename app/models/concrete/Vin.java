package models.concrete;

import com.hp.hpl.jena.ontology.Individual;
import models.ResourceClass;
import models.factory.Factory;

public class Vin extends ResourceClass implements Factory<Vin> {

    private String cepage = null;
    private String cru = null;
    private String cuvee = null;
    private String sol = null;
    private String degre = null;

    public Vin() {
        super(null);
    }

    public Vin(Individual individual) {
        super(individual);
    }

    @Override
    public Vin factory(Individual individual) {
        return new Vin(individual);
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
}
