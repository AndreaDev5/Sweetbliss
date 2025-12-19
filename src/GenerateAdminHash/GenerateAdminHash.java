import utils.PasswordUtils;

public class GenerateAdminHash {
    public static void main(String[] args) {
        String plainPassword = "adminSweet01"; // La contraseña del admin
        String hashed = PasswordUtils.hashPassword(plainPassword);
        System.out.println("Hashed password: " + hashed);
    }
}
