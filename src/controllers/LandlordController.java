package controllers;

import models.Database;
import views.LandlordView;

public class LandlordController extends GUIcontroller {

    private LandlordView view;
    private LoginController login;

    public LandlordController(Database model, LandlordView view, LoginController login) {
        super(model);
        this.view = view;
        this.login = login;

    }

    public void start() {
        view.intializeDashboard();
        view.displayDashboard();
        view.addLogoutListener(e -> {
            view.dispose();
            login.start();

        });
    }

}
