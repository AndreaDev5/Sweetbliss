package view;

import model.Product;
import model.User;
import utils.DBConnection;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class RequestForm extends JFrame {

    private User user;
    private Product product;

    JTextArea txtMessage;
    JButton btnSend, btnBack;

    public RequestForm(User user, Product product) {
        this.user = user;
        this.product = product;

        setTitle("Enviar solicitud");
        setSize(400, 350);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Producto: " + product.getName());
        label.setBounds(20, 20, 300, 25);
        add(label);

        JLabel labelMsg = new JLabel("Mensaje:");
        labelMsg.setBounds(20, 60, 300, 25);
        add(labelMsg);

        txtMessage = new JTextArea();
        txtMessage.setBounds(20, 90, 340, 120);
        add(txtMessage);

        btnSend = new JButton("Enviar Solicitud");
        btnSend.setBounds(20, 230, 150, 30);
        add(btnSend);

        btnBack = new JButton("Volver");
        btnBack.setBounds(200, 230, 150, 30);
        add(btnBack);

        btnSend.addActionListener(e -> sendRequest());
        btnBack.addActionListener(e -> {
            new ProductCatalog(user).setVisible(true);
            dispose();
        });
    }

    private void sendRequest() {
        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO requests (user_id, product_id, message, request_date) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setInt(2, product.getProductId());
            ps.setString(3, txtMessage.getText());
            ps.setString(4, LocalDate.now().toString());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Solicitud enviada con éxito");

            new ClientView(user).setVisible(true);
            dispose();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "ERROR enviando solicitud: " + e.getMessage());
        }
    }
}
