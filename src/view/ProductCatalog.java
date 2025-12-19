package view;

import model.Product;
import model.User;
import utils.DBConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ProductCatalog extends JFrame {

    private User user;
    private JList<Product> listProducts;
    private DefaultListModel<Product> modelProducts;

    public ProductCatalog(User user) {
        this.user = user;

        setTitle("Productos disponibles");
        setSize(400, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Catálogo de productos");
        label.setBounds(20, 20, 250, 25);
        add(label);

        modelProducts = new DefaultListModel<>();
        listProducts = new JList<>(modelProducts);
        JScrollPane scroll = new JScrollPane(listProducts);
        scroll.setBounds(20, 60, 340, 230);
        add(scroll);

        JButton btnRequest = new JButton("Solicitar producto");
        btnRequest.setBounds(20, 310, 160, 30);
        add(btnRequest);

        JButton btnBack = new JButton("Volver");
        btnBack.setBounds(200, 310, 160, 30);
        add(btnBack);

        loadProducts();

        btnRequest.addActionListener(e -> {
            Product p = listProducts.getSelectedValue();
            if (p == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un producto");
                return;
            }

            new RequestForm(user, p).setVisible(true);
            dispose();
        });

        btnBack.addActionListener(e -> {
            new ClientView(user).setVisible(true);
            dispose();
        });
    }

    private void loadProducts() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM products";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setImage(rs.getString("image"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));

                modelProducts.addElement(p);
            }

        } catch (SQLException e) {
            System.out.println("ERROR loadProducts: " + e.getMessage());
        }
    }
}
