package view;

import model.User;
import javax.swing.*;

public class ClientView extends JFrame {

    private User user;

    public ClientView(User user) {
        this.user = user;

        setTitle("Panel de Usuario - " + user.getName());
        setSize(350, 220);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Bienvenido " + user.getName());
        label.setBounds(20, 20, 250, 25);
        add(label);

        JButton btnProducts = new JButton("Ver Productos");
        btnProducts.setBounds(20, 60, 160, 30);
        add(btnProducts);

        JButton btnLogout = new JButton("Cerrar Sesión");
        btnLogout.setBounds(20, 110, 160, 30);
        add(btnLogout);

        // Acciones
        btnProducts.addActionListener(e -> {
            new ProductCatalog(user).setVisible(true);
            dispose();
        });

        btnLogout.addActionListener(e -> {
            new LoginView().setVisible(true);
            dispose();
        });
    }
}
