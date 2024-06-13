package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Layout extends JFrame {
    public void guiInitilize(int width, int height) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Rent a Car");
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows) {
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setMaxWidth(75);

        DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);

        if (rows == null) {
            rows = new ArrayList<>();
        }

        for (Object[] row : rows) {
            model.addRow(row);
        }
    }

    public int getTableSelectedRow(JTable table, int index) {
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(), index).toString());
    }
}