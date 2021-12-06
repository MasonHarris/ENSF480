package controllers;

import models.Database;
import views.ManagerView;

public class ManagerController extends GUIcontroller {
    private ManagerView view;
    private LoginController login;
    private String username;

    public ManagerController(Database model, ManagerView view, LoginController login, String username) {
        super(model);
        this.view = view;
        this.login = login;
        this.username = username;
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
