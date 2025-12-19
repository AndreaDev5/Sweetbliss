package view;

import model.User;
import javax.swing.*;

public class AdminView extends JFrame {

    private User user;

    public AdminView(User user) {
        this.user = user;

        setTitle("Panel de Administrador - " + user.getName());
        setSize(350, 250);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Bienvenido Administrador " + user.getName());
        label.setBounds(20, 20, 300, 25);
        add(label);

        JButton btnRequests = new JButton("Ver Solicitudes");
        btnRequests.setBounds(20, 60, 150, 30);
        add(btnRequests);

        JButton btnLogout = new JButton("Cerrar Sesión");
        btnLogout.setBounds(20, 120, 150, 30);
        add(btnLogout);

        // ACCIONES
        btnRequests.addActionListener(e -> {
            new RequestAdminView().setVisible(true);
            dispose();
        });

        btnLogout.addActionListener(e -> {
            new LoginView().setVisible(true);
            dispose();
        });
    }
}
