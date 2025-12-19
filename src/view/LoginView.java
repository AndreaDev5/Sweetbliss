package view;

import controller.UserController;
import model.User;

import javax.swing.*;

public class LoginView extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnGoToRegister;

    public LoginView() {
        setTitle("Login");
        setSize(340, 260);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel l1 = new JLabel("Email:");
        l1.setBounds(20, 20, 120, 25);
        add(l1);
        txtEmail = new JTextField();
        txtEmail.setBounds(20, 45, 290, 25);
        add(txtEmail);

        JLabel l2 = new JLabel("Contraseña:");
        l2.setBounds(20, 80, 120, 25);
        add(l2);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(20, 105, 290, 25);
        add(txtPassword);

        btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(20, 150, 135, 30);
        add(btnLogin);

        btnGoToRegister = new JButton("Registrar");
        btnGoToRegister.setBounds(175, 150, 135, 30);
        add(btnGoToRegister);

        btnLogin.addActionListener(e -> login());
        btnGoToRegister.addActionListener(e -> {
            new RegisterView().setVisible(true);
            dispose();
        });
    }

    private void login() {
        UserController controller = new UserController();

        User user = controller.login(txtEmail.getText().trim(), new String(txtPassword.getPassword()).trim());

        if (user == null) {
            JOptionPane.showMessageDialog(this, "Credenciales inválidas");
            return;
        }

        if ("admin".equalsIgnoreCase(user.getRol())) {
            JOptionPane.showMessageDialog(this, "Bienvenido administrador");
            new AdminView(user).setVisible(true);   // enviar usuario al admin
        } else {
            JOptionPane.showMessageDialog(this, "Bienvenido usuario");
            new ClientView(user).setVisible(true);  // enviar usuario al cliente
        }

        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
