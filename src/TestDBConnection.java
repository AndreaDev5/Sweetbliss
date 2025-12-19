import utils.DBConnection;
import java.sql.Connection;

public class TestDBConnection {
    public static void main(String[] args) {
        // Intentamos conectarnos a la base de datos
        Connection connection = DBConnection.getConnection();

        if (connection != null) {
            System.out.println("La conexión a la base de datos funciona correctamente 😊");
        } else {
            System.out.println("Hubo un error en la conexión 😢");
        }
    }
}
