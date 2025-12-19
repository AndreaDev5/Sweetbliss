package view;

import controller.RequestController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class RequestAdminView extends JFrame {

    JTable table;
    DefaultTableModel model;
    RequestController controller = new RequestController();

    public RequestAdminView() {
        setTitle("Administrar Solicitudes");
        setSize(700, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Tabla
        model = new DefaultTableModel(new String[]{"ID", "Usuario", "Producto", "Mensaje", "Fecha"}, 0);
        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 20, 640, 250);
        add(scroll);

        // Botones
        JButton btnRefresh = new JButton("Actualizar");
        btnRefresh.setBounds(20, 300, 120, 30);
        add(btnRefresh);

        JButton btnDelete = new JButton("Eliminar");
        btnDelete.setBounds(160, 300, 120, 30);
        add(btnDelete);

        JButton btnCSV = new JButton("Exportar CSV");
        btnCSV.setBounds(300, 300, 140, 30);
        add(btnCSV);

        JButton btnBack = new JButton("Volver");
        btnBack.setBounds(460, 300, 120, 30);
        add(btnBack);

        // Cargar solicitudes inicial
        loadRequests();

        // Acciones
        btnRefresh.addActionListener(e -> loadRequests());
        btnDelete.addActionListener(e -> deleteSelected());
        btnCSV.addActionListener(e -> exportToCSV());
        btnBack.addActionListener(e -> {
            new AdminView().setVisible(true);
            dispose();
        });
    }

    // Cargar solicitudes desde la base de datos
    private void loadRequests() {
        model.setRowCount(0);
        List<Object[]> data = controller.getAllRequests();
        for (Object[] row : data) {
            model.addRow(row);
        }
    }

    // Eliminar solicitud seleccionada
    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila");
            return;
        }

        int id = (int) model.getValueAt(row, 0);

        if (controller.deleteRequest(id)) {
            JOptionPane.showMessageDialog(this, "Solicitud eliminada");
            loadRequests();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar");
        }
    }

    // Exportar a CSV
    private void exportToCSV() {
        try {
            StringBuilder sb = new StringBuilder();

            // Encabezados
            for (int i = 0; i < model.getColumnCount(); i++) {
                sb.append(model.getColumnName(i));
                if (i != model.getColumnCount() - 1) sb.append(",");
            }
            sb.append("\n");

            // Filas
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Object value = model.getValueAt(i, j);
                    sb.append(value != null ? value.toString() : "");
                    if (j != model.getColumnCount() - 1) sb.append(",");
                }
                sb.append("\n");
            }

            // Guardar archivo CSV
            java.nio.file.Files.write(java.nio.file.Paths.get("solicitudes.csv"), sb.toString().getBytes());

            JOptionPane.showMessageDialog(this, "Archivo CSV generado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al exportar CSV: " + e.getMessage());
        }
    }
}
