package views;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import models.Property;

public class PropertyStateTable extends TablePanel {

    public PropertyStateTable(int width, int height, String buttonText) {
        super(width, height, buttonText);

    }

    public void displayTable(ArrayList<Property> properties) {
        String[] colNames = { "Property ID", "Address", "Number of bedrooms", "Number of bathrooms", "City quadrant",
                "Furnished", "Property type" };
        String options[] = { "Suspended", "Active", "Cancelled", "Rented" };
        super.displayTable(colNames);
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
        for (Property p : properties) {
            Object[] entry = { p.getPropertyId(), p.getAddress(), p.getNumOfBed(), p.getNumOfBath(),
                    p.getCityQuadrant(), p.getIsFurnished(), p.getPropertyType(), p.getPropertyStatus(),
                    p.getListingPeriod() };
            tableModel.addRow(entry);
        }
        this.repaint();

    }

    // sends back multiple selected properties, integer represents the index number
    // of the property in the list
    // string represents the data in the col column of the table
    public HashMap<Integer, String> getSelectedProperties(int col) {

        if (!table.getSelectionModel().isSelectionEmpty()) {
            HashMap<Integer, String> map = new HashMap<Integer, String>();
            int[] selectedrows = table.getSelectedRows();
            for (int index : selectedrows) {
                map.put(index, table.getValueAt(index, col).toString());
            }
            return map;

        } else {
            return null;
        }

    }

}
