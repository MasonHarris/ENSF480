package views;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import models.Landlord;
import models.Property;
import models.RegisteredRenter;

public class ViewTablePanel extends JPanel {
    int width;
    int height;

    public ViewTablePanel(int width, int height) {
        setLayout(null);
        this.width = width;
        this.height = height;
    }

    public void displayProperties(ArrayList<Property> properties) {
        this.removeAll();
        String[] colNames = { "Property ID", "Landlord name", "Address ", "Number of bedrooms", "Number of bathrooms",
                "Property type", "Property status" };
        DefaultTableModel model = new DefaultTableModel(colNames, 0);
        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // prevents table from being edited
        table.setDefaultEditor(Object.class, null);

        TableColumnModel columns = table.getColumnModel();
        columns.getColumn(1).setPreferredWidth(160);
        columns.getColumn(4).setPreferredWidth(160);
        columns.getColumn(5).setPreferredWidth(160);
        for (Property p : properties) {
            Object[] entry = { p.getPropertyId(), p.getLandlordUsername(), p.getAddress(), p.getNumOfBath(),
                    p.getNumOfBed(), p.getPropertyType(), p.getPropertyStatus() };
            model.addRow(entry);
        }

        JScrollPane scroll = new JScrollPane(table);
        JLabel label = new JLabel("PROPERTIES");
        label.setBounds((int) (width * 0.1), (int) (height * 0.01), (int) (width * 0.1), (int) (height * 0.05));
        scroll.setBounds(0, (int) (height * 0.03), (int) (width * 0.7), (int) (height * 0.5));
        add(scroll);
        add(label);
        repaint();

    }

    public void displayLandlords(ArrayList<Landlord> landlords) {
        this.removeAll();
        String[] colNames = { "Landlord ID ", "Landlord name", "Landlord password", "Landlord email" };
        DefaultTableModel model = new DefaultTableModel(colNames, 0);
        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // prevents table from being edited
        table.setDefaultEditor(Object.class, null);

        TableColumnModel columns = table.getColumnModel();
        columns.getColumn(0).setPreferredWidth(160);
        columns.getColumn(1).setPreferredWidth(160);
        columns.getColumn(2).setPreferredWidth(160);
        columns.getColumn(3).setPreferredWidth(160);

        for (Landlord p : landlords) {
            Object[] entry = { p.getID(), p.getUsername(), p.getPassword(), p.getEmailAddress() };
            model.addRow(entry);
        }

        JScrollPane scroll = new JScrollPane(table);
        JLabel label = new JLabel("Landlords");
        label.setBounds((int) (width * 0.1), (int) (height * 0.01), (int) (width * 0.1), (int) (height * 0.05));
        scroll.setBounds(0, (int) (height * 0.03), (int) (width * 0.6), (int) (height * 0.5));
        add(scroll);
        add(label);
        repaint();

    }

    public void displayRenters(ArrayList<RegisteredRenter> renters) {
        this.removeAll();
        String[] colNames = { "Renter ID ", "Renter name", "Renter password" };
        DefaultTableModel model = new DefaultTableModel(colNames, 0);
        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // prevents table from being edited
        table.setDefaultEditor(Object.class, null);

        TableColumnModel columns = table.getColumnModel();
        columns.getColumn(0).setPreferredWidth(160);
        columns.getColumn(1).setPreferredWidth(160);
        columns.getColumn(2).setPreferredWidth(160);

        for (RegisteredRenter p : renters) {
            Object[] entry = { p.getID(), p.getUsername(), p.getPassword() };
            model.addRow(entry);
        }

        JScrollPane scroll = new JScrollPane(table);
        JLabel label = new JLabel("Renters");
        label.setBounds((int) (width * 0.1), (int) (height * 0.01), (int) (width * 0.1), (int) (height * 0.05));
        scroll.setBounds(0, (int) (height * 0.03), (int) (width * 0.7), (int) (height * 0.5));
        add(scroll);
        add(label);
        repaint();

    }

}
