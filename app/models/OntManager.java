package models;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import play.Play;
import play.vfs.VirtualFile;

import java.io.*;

public class OntManager {

    private static final String MODEL_PREFIX = Play.configuration.getProperty("ontology.prefix");

    private static OntModel model = null;

    private static void initModel() {
        model = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC);
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadData() throws IOException {
        VirtualFile vf = VirtualFile.fromRelativePath(Play.configuration.getProperty("ontology.file"));
        File realFile = vf.getRealFile();
        FileReader fileReader = new FileReader(realFile);
        model.read(fileReader, MODEL_PREFIX, "TURTLE");
    }

    public static OntModel getModel() {
        if(model == null) {
            initModel();
        }
        return model;
    }

    public static String appendPrefix(String suffix) {
        return MODEL_PREFIX + suffix;
    }

    public static void executeQuery(String queryString, OutputStream out) throws QueryParseException {
        getModel();

        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        ResultSetFormatter.outputAsJSON(out, results);
        qe.close();
    }
}
