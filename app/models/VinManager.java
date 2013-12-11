package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import org.mindswap.pellet.jena.PelletInfGraph;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import play.vfs.VirtualFile;

public class VinManager {

    private static VinManager instance = null;

    private PelletInfGraph pellet;
    private final String MODEL_PREFIX = "http://www.vin.com/ontologies/vin.owl";
    protected final OntModel model = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC);

    private VinManager() {
        try {
            loadData("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData(String path) throws IOException {
        pellet = (PelletInfGraph) model.getGraph();

        VirtualFile vf = VirtualFile.fromRelativePath("/data/vin.owl");
        File realFile = vf.getRealFile();
        FileReader fileReader = new FileReader(realFile);
        model.read(fileReader, MODEL_PREFIX, "TURTLE");
    }

    public List<Vin> getVins() {
        List<Vin> vins = new ArrayList<Vin>();
        OntClass vinClass = model.getOntClass(MODEL_PREFIX + "#Vin");
        for (ExtendedIterator<? extends OntResource> it= vinClass.listInstances(); it.hasNext(); ) {
            Individual vinIndividual = (Individual) it.next();
            vins.add(new Vin(vinIndividual));
        }

        return vins;
    }

    public static VinManager getInstance() {
        if(instance == null) {
            instance = new VinManager();
        }
        return instance;
    }

    public Vin findVin(String name) {
        Individual vinIndividual = model.getIndividual(MODEL_PREFIX + "#" + name);
        if(vinIndividual != null) {
            return new Vin(vinIndividual);
        }
        return null;
    }
}
