package controller;

import model.Product;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Product p = new Product();

                p.setId(rs.getInt("product_id"));
                p.setImage(rs.getString("image"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price")); // <-- cambiar aquí si se llama "precio"

                list.add(p);
            }

        } catch (Exception e) {
            System.out.println("ERROR getAllProducts: " + e.getMessage());
        }

        return list;
    }
}

