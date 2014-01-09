package models;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import models.factory.Factory;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import play.Play;
import play.vfs.VirtualFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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


    public static <C extends ResourceClass> List<C> getOntClassResources(Factory<C> factory, String prefix) {
        List<C> resources = new ArrayList<C>();
        OntClass ontClass = OntManager.getModel().getOntClass(OntManager.appendPrefix("#" + prefix));
        for (ExtendedIterator<? extends OntResource> it= ontClass.listInstances(); it.hasNext(); ) {
            Individual individual = (Individual) it.next();
            resources.add(factory.factory(individual));
        }

        return resources;
    }

    public static <C extends ResourceClass> C findEntity(Factory<C> factory, String name) {
        Individual individual = OntManager.getModel().getIndividual(OntManager.appendPrefix("#" + name));
        if(individual != null) {
            return factory.factory(individual);
        }
        return null;
    }

    public static <C extends ResourceClass> List<C> getResourcesFromQuery(Factory<C> factory, String queryString, String var) {
        getModel();

        List<C> resources = new ArrayList<C>();

        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        while(results.hasNext()) {
            Resource resource = results.next().getResource(var);

            if(resource != null) {
                C entity = findEntity(factory, resource.getLocalName());
                if(entity != null) {
                    resources.add(entity);
                }
            }
        }

        return resources;
    }

    public static OntResource getOntResource(String uri) {
        OntResource resource = OntManager.getModel().getOntResource(uri);
        return resource;
    }

    public static String getRedirection(String uri) {            System.out.println(uri);
        OntResource resource = OntManager.getModel().getOntResource(uri);
        if(resource != null) {
            for(ExtendedIterator<Resource> it = resource.listRDFTypes(false); it.hasNext();) {
                Resource r =  it.next();
                String name = r.getLocalName();
                if (name.equals("Class")) {
                    return null;
                }
                else if(name.equals("Vin")) {
                    return "/vin/" + resource.getLocalName();
                }
                else if(name.equals("VinCru")) {
                    return "/cru/" + resource.getLocalName();
                }
                else if(name.equals("VinCepage")) {
                    return "/cepage/" + resource.getLocalName();
                }
                else if(name.equals("VinCuvee")) {
                    return "/cuvee/" + resource.getLocalName();
                }
                else if(name.equals("VinProducteur")) {
                    return "/producteur/" + resource.getLocalName();
                }
                else if(name.equals("VinSol")) {
                    return "/sol/" + resource.getLocalName();
                }
            }
        }
        return null;
    }
}
