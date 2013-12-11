package controllers;

import play.mvc.*;

import models.*;

import java.util.List;

public class Application extends Controller {

    private static final VinManager vinManager = VinManager.getInstance();

    public static void index() {
        List<Vin> vins = vinManager.getVins();
        render(vins);
    }

    public static void vin() {
        String name = params.get("name");
        Vin vin = vinManager.findVin(name);
        render(vin);
    }
}