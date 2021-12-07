package views;

import models.Property;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;
import javax.swing.table.*;

import java.util.ArrayList;

import java.awt.*;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.HashMap;

public class TablePanel extends JPanel {
    JTable table;
    JButton submitButton;
    int width;
    int height;
    DefaultTableModel tableModel;

    public TablePanel(int width, int height, String buttonText) {
        super();
        this.setLayout(null);
        submitButton = new JButton(buttonText);
        this.width = width;
        this.height = height;

    }

    public void displayTable(String[] colNames) {

        // model used to make table
        tableModel = new DefaultTableModel(colNames, 0);
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // prevents table entries from being edited
        table.setDefaultEditor(Object.class, null);
        JScrollPane scroll = new JScrollPane(table);
        JLabel label = new JLabel("Properties");
        label.setBounds((int) (width * 0.1), 0, (int) (width * 0.1), (int) (height * 0.05));
        scroll.setBounds(0, (int) (height * 0.035), (int) (width * 0.7), (int) (height * 0.5));
        submitButton.setBounds((int) (width * 0.1), (int) (height * 0.55), (int) (width * 0.2),
                (int) (height * 0.030));

        table.setColumnSelectionAllowed(false);

        this.add(label);
        this.add(submitButton);
        this.add(scroll);

    }

    public void displayPropertyTableRenter(ArrayList<Property> properties) {
        this.removeAll();
        String[] colNames = { "Property ID", "Address", "Number of bedrooms", "Number of bathrooms", "City quadrant",
                "Furnished", "Property type" };
        // model used to make table
        tableModel = new DefaultTableModel(colNames, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setColumnSelectionAllowed(false);
        TableColumnModel columns = table.getColumnModel();
        columns.getColumn(2).setPreferredWidth(160);
        columns.getColumn(3).setPreferredWidth(160);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // prevents table entries from being edited
        table.setDefaultEditor(Object.class, null);
        for (Property p : properties) {
            Object[] entry = { p.getPropertyId(), p.getAddress(), p.getNumOfBed(), p.getNumOfBath(),
                    p.getCityQuadrant(), p.getIsFurnished(), p.getPropertyType() };
            tableModel.addRow(entry);
        }

        JScrollPane scroll = new JScrollPane(table);
        JLabel label = new JLabel("Properties");
        label.setBounds((int) (width * 0.1), 0, (int) (width * 0.1), (int) (height * 0.05));
        scroll.setBounds(0, (int) (height * 0.035), (int) (width * 0.7), (int) (height * 0.5));
        submitButton.setBounds((int) (width * 0.1), (int) (height * 0.55), (int) (width * 0.2),
                (int) (height * 0.030));

        this.add(label);
        this.add(submitButton);
        this.add(scroll);
        this.repaint();

    }

    // options represents the states the properties can be changed to
    public void displayPropertyTableChangeListing(ArrayList<Property> properties) {
        this.removeAll();
        String[] colNames = { "Property ID", "Address", "Number of bedrooms", "Number of bathrooms", "City quadrant",
                "Furnished", "Property type", "Status", "Listing period" };
        // options for state changes
        String options[] = { "Suspended", "Active", "Cancelled", "Rented" };
        // model used to make table
        tableModel = new DefaultTableModel(colNames, 0);
        table = new JTable(tableModel);
        table.setSelectionModel(new TableListSelection());
        table.setColumnSelectionAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableColumnModel columns = table.getColumnModel();
        columns.getColumn(2).setPreferredWidth(160);
        columns.getColumn(3).setPreferredWidth(160);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn statusColumn = table.getColumnModel().getColumn(7);

        // adds combobox to status column
        JComboBox<String> comboBox = new JComboBox<String>(options);
        statusColumn.setCellEditor(new DefaultCellEditor(comboBox));
        // prevents table from being edited
        table.setDefaultEditor(Object.class, null);
        // adds data to table
        for (Property p : properties) {
            Object[] entry = { p.getPropertyId(), p.getAddress(), p.getNumOfBed(), p.getNumOfBath(),
                    p.getCityQuadrant(), p.getIsFurnished(), p.getPropertyType(), p.getPropertyStatus(),
                    p.getListingPeriod() };
            tableModel.addRow(entry);
        }

        JScrollPane scroll = new JScrollPane(table);
        JLabel label = new JLabel("Properties");
        label.setBounds((int) (width * 0.1), 0, (int) (width * 0.1), (int) (height * 0.05));
        scroll.setBounds(0, (int) (height * 0.035), (int) (width * 0.7), (int) (height * 0.5));
        submitButton.setBounds((int) (width * 0.1), (int) (height * 0.55), (int) (width * 0.1),
                (int) (height * 0.030));

        this.add(label);
        this.add(submitButton);
        this.add(scroll);
        this.repaint();

    }

    //
    public void displayPropertyPayFees(ArrayList<Property> properties) {
        this.removeAll();
        String[] colNames = { "Property ID", "Address", "Number of bedrooms", "Number of bathrooms", "City quadrant",
                "Furnished", "Property type", "Status", "Listing period" };
        // options for state changes

        // model used to make table
        tableModel = new DefaultTableModel(colNames, 0);
        table = new JTable(tableModel);
        table.setSelectionModel(new TableListSelection());
        table.setColumnSelectionAllowed(false);
        TableColumnModel columns = table.getColumnModel();
        columns.getColumn(2).setPreferredWidth(160);
        columns.getColumn(3).setPreferredWidth(160);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // prevents table from being edited
        table.setDefaultEditor(Object.class, null);
        for (Property p : properties) {
            Object[] entry = { p.getPropertyId(), p.getAddress(), p.getNumOfBed(), p.getNumOfBath(),
                    p.getCityQuadrant(), p.getIsFurnished(), p.getPropertyType(), p.getPropertyStatus(),
                    p.getListingPeriod() };
            tableModel.addRow(entry);
        }

        JScrollPane scroll = new JScrollPane(table);
        JLabel label = new JLabel("Properties");
        label.setBounds((int) (width * 0.1), 0, (int) (width * 0.1), (int) (height * 0.05));
        scroll.setBounds(0, (int) (height * 0.035), (int) (width * 0.7), (int) (height * 0.5));
        submitButton.setBounds((int) (width * 0.1), (int) (height * 0.55), (int) (width * 0.1),
                (int) (height * 0.030));

        this.add(label);
        this.add(submitButton);
        this.add(scroll);
        this.repaint();

    }

    // sends back multiple selected properties, integer represents the index number
    // of the property in the list
    // string represents the data in the col column of the table
    public HashMap<Integer, String> getSelectedPropertiesMap(int col) {

        if (!table.getSelectionModel().isSelectionEmpty()) {
            HashMap<Integer, String> map = new HashMap<Integer, String>();
            int[] selectedrows = table.getSelectedRows();
            for (int index : selectedrows) {
                map.put(index, table.getValueAt(index, col).toString());
            }
            return map;

        } else {
            // System.out.println("nothing");
            return null;
        }

    }

    // gets the selected properties and returns their IDs in an int array
    public int[] getSelectedPropertiesID() {

        if (!table.getSelectionModel().isSelectionEmpty()) {

            int[] selectedrows = table.getSelectedRows();
            int[] IDarray = new int[selectedrows.length];
            for (int i = 0; i < selectedrows.length; i++) {
                IDarray[i] = (int) table.getValueAt(selectedrows[i], 0);
            }
            return IDarray;

        } else {
            // System.out.println("nothing");
            return null;
        }

    }

}
