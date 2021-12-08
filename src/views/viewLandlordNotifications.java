package views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.util.HashMap;

public class viewLandlordNotifications extends JPanel {
    int width;
    int height;

    public viewLandlordNotifications(int width, int height) {
        this.width = width;
        this.height = height;
        setLayout(null);
    }

    public void displayNotifications(HashMap<String, Integer> notifications) {
        this.removeAll();
        String[] colNames = { "Property ID", "Renter email" };
        DefaultTableModel model = new DefaultTableModel(colNames, 0);
        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // prevents table from being edited
        table.setDefaultEditor(Object.class, null);

        TableColumnModel columns = table.getColumnModel();

        columns.getColumn(1).setPreferredWidth(160);

        for (var n : notifications.entrySet()) {
            Object[] entry = { n.getValue(), n.getKey() };
            model.addRow(entry);
        }

        JScrollPane scroll = new JScrollPane(table);
        JLabel label = new JLabel("NOTIFICIATIONS");
        label.setBounds((int) (width * 0.1), (int) (height * 0.01), (int) (width * 0.1), (int) (height * 0.05));
        scroll.setBounds(0, (int) (height * 0.03), (int) (width * 0.7), (int) (height * 0.5));
        add(scroll);
        add(label);
        repaint();

    }

}
