package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import org.mindswap.pellet.jena.PelletInfGraph;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import play.Play;
import play.vfs.VirtualFile;

public class VinManager {

    private static final String MODEL_PREFIX = Play.configuration.getProperty("ontology.prefix");

    public static List<Vin> getVins() {
        List<Vin> vins = new ArrayList<Vin>();
        OntClass vinClass = OntManager.getModel().getOntClass(OntManager.appendPrefix("#Vin"));
        for (ExtendedIterator<? extends OntResource> it= vinClass.listInstances(); it.hasNext(); ) {
            Individual vinIndividual = (Individual) it.next();
            vins.add(new Vin(vinIndividual));
        }

        return vins;
    }

    public static Vin findVin(String name) {
        Individual vinIndividual = OntManager.getModel().getIndividual(OntManager.appendPrefix("#" + name));
        if(vinIndividual != null) {
            return new Vin(vinIndividual);
        }
        return null;
    }
}
