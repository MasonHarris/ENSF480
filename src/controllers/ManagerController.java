package controllers;

import models.Database;
import views.ManagerView;

public class ManagerController extends GUIcontroller {
    private ManagerView view;
    private LoginController login;

    public ManagerController(Database model, ManagerView view, LoginController login) {
        super(model);
        this.view = view;
        this.login = login;
    }

    public void start() {
        view.intializeDashboard();
        view.displayDashboard();
        // logouts by destroying current user frame and recreating intial login frame
        view.addLogoutListener(e -> {
            view.dispose();
            login.start();
        });

    }

}
