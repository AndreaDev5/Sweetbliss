package view;

import controller.UserController;
import model.User;
import javax.swing.*;

public class RegisterView extends JFrame {

    JTextField txtName, txtLastName, txtEmail;
    JPasswordField txtPassword;
    JButton btnRegister, btnGoToLogin;

    public RegisterView() {
        setTitle("Registro");
        setSize(300, 350);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Nombre:");
        l1.setBounds(20, 20, 120, 25);
        add(l1);
        txtName = new JTextField();
        txtName.setBounds(20, 45, 240, 25);
        add(txtName);

        JLabel l2 = new JLabel("Apellido:");
        l2.setBounds(20, 75, 120, 25);
        add(l2);
        txtLastName = new JTextField();
        txtLastName.setBounds(20, 100, 240, 25);
        add(txtLastName);

        JLabel l3 = new JLabel("Email:");
        l3.setBounds(20, 130, 120, 25);
        add(l3);
        txtEmail = new JTextField();
        txtEmail.setBounds(20, 155, 240, 25);
        add(txtEmail);

        JLabel l4 = new JLabel("Contraseña:");
        l4.setBounds(20, 185, 120, 25);
        add(l4);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(20, 210, 240, 25);
        add(txtPassword);

        btnRegister = new JButton("Registrar");
        btnRegister.setBounds(20, 250, 110, 30);
        add(btnRegister);

        btnGoToLogin = new JButton("Login");
        btnGoToLogin.setBounds(150, 250, 110, 30);
        add(btnGoToLogin);

        btnRegister.addActionListener(e -> registerUser());
        btnGoToLogin.addActionListener(e -> {
            new LoginView().setVisible(true);
            dispose();
        });
    }

    private void registerUser() {
        User u = new User(
                txtName.getText(),
                txtLastName.getText(),
                txtEmail.getText(),
                new String(txtPassword.getPassword()),
                "user"
        );

        UserController controller = new UserController();

        if (controller.register(u)) {
            JOptionPane.showMessageDialog(this, "Usuario registrado");
            new LoginView().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar");
        }
    }

    public static void main(String[] args) {
        new RegisterView().setVisible(true);
    }
}

