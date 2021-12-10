package views;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import models.Property;
import models.SummaryReport;

public class SummaryReportView extends JPanel {

    public SummaryReportView(int width, int height) {
        setLayout(null);
    }

    public void displayReport(int width, int height, SummaryReport report) {
        this.removeAll();
        JLabel title = new JLabel("PERIODICAL REPORT");
        title.setBounds(10, 0, (int) (width * 0.3), (int) (height * 0.05));
        add(title);
        JLabel listed = new JLabel("Number of houses listed in period: ");

        JLabel listedValue = new JLabel(Integer.toString(report.getTotalNoHouses()));

        int distance = (int) (width * 0.19);

        listedValue.setBounds(distance, (int) (height * 0.01), (int) (width * 0.1), (int) (height * 0.05));
        listed.setBounds(10, (int) (height * 0.01), (int) (width * 0.3), (int) (height * 0.05));

        add(listed);
        add(listedValue);

        JLabel active = new JLabel("Number of houses active in period: ");

        JLabel activeValue = new JLabel(Integer.toString(report.getTotalHousesActive()));

        active.setBounds(10, (int) (height * 0.02), (int) (width * 0.3), (int) (height * 0.05));
        activeValue.setBounds(distance, (int) (height * 0.02), (int) (width * 0.1), (int) (height * 0.05));

        add(active);
        add(activeValue);

        JLabel rented = new JLabel("Number of houses rented in period: ");

        JLabel rentedValue = new JLabel(Integer.toString(report.getTotalHousesRented()));

        rented.setBounds(10, (int) (height * 0.03), (int) (width * 0.3), (int) (height * 0.05));
        rentedValue.setBounds(distance, (int) (height * 0.03), (int) (width * 0.1), (int) (height * 0.05));

        rented.setLabelFor(rentedValue);
        add(rentedValue);
        add(rented);
        createTable(report.getRentedHouses(), width, height);

    }
    //displays rented property table for manager
    public void createTable(ArrayList<Property> properties, int width, int height) {

        String[] colNames = { "Landlord name ", "Address", "Property ID" };
        DefaultTableModel model = new DefaultTableModel(colNames, 0);
        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // prevents table from being edited
        table.setDefaultEditor(Object.class, null);

        TableColumnModel columns = table.getColumnModel();
        columns.getColumn(0).setPreferredWidth(160);
        columns.getColumn(1).setPreferredWidth(200);

        for (Property p : properties) {
            Object[] entry = { p.getLandlordUsername(), p.getAddress(), p.getPropertyId() };
            model.addRow(entry);
        }

        JScrollPane scroll = new JScrollPane(table);
        JLabel label = new JLabel("Properties rented");
        label.setBounds((int) (width * 0.1), (int) (height * 0.07), (int) (width * 0.1), (int) (height * 0.05));
        scroll.setBounds(10, (int) (height * 0.1), (int) (width * 0.6), (int) (height * 0.5));
        add(scroll);
        add(label);
        repaint();

    }

}
