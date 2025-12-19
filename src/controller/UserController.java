package controller;

import java.sql.*;
import model.User;
import utils.DBConnection;
import utils.PasswordUtils;

public class UserController {

    // REGISTRAR USUARIO
    public boolean register(User user) {
        String sql = "INSERT INTO usuarios(name, lastName, email, password, rol) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, user.getName());
            pst.setString(2, user.getLastName());
            pst.setString(3, user.getEmail());
            pst.setString(4, PasswordUtils.hashPassword(user.getPassword()));
            pst.setString(5, "user"); // rol por defecto

            pst.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("ERROR register: " + e.getMessage());
            return false;
        }
    }

    // LOGIN
    public User login(String email, String password) {

        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                String dbPass = rs.getString("password");
                String hashInput = PasswordUtils.hashPassword(password);

                if (dbPass.equals(hashInput)) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setName(rs.getString("name"));
                    u.setLastName(rs.getString("lastName"));
                    u.setEmail(rs.getString("email"));
                    u.setRol(rs.getString("rol"));
                    return u;
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR login: " + e.getMessage());
        }

        return null;
    }
}
