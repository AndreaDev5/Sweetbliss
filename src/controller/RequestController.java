package controller;

import utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestController {

    // Obtener todas las solicitudes
    public List<Object[]> getAllRequests() {
        List<Object[]> list = new ArrayList<>();

        String sql = "SELECT r.request_id, u.name AS user_name, p.name AS product_name, r.message, r.request_date "
                   + "FROM requests r "
                   + "INNER JOIN usuarios u ON r.user_id = u.id "
                   + "INNER JOIN products p ON r.product_id = p.product_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                list.add(new Object[]{
                        rs.getInt("request_id"),
                        rs.getString("user_name"),
                        rs.getString("product_name"),
                        rs.getString("message"),
                        rs.getString("request_date")
                });
            }

        } catch (Exception e) {
            System.out.println("ERROR getAllRequests: " + e.getMessage());
        }

        return list;
    }

    // Eliminar solicitud
    public boolean deleteRequest(int id) {
        String sql = "DELETE FROM requests WHERE request_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("ERROR deleteRequest: " + e.getMessage());
            return false;
        }
    }
}
