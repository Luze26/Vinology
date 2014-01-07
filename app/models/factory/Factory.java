package models.factory;

import com.hp.hpl.jena.ontology.Individual;

public interface Factory<T> {
    T factory(Individual individual);
}