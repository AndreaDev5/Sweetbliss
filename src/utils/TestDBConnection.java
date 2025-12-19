import utils.DBConnection;
import java.sql.Connection;

public class TestDBConnection {
    public static void main(String[] args) {
        Connection cn = DBConnection.getConnection();

        if (cn != null) {
            System.out.println("✅ Conexión exitosa a MySQL");
        } else {
            System.out.println("❌ Error en la conexión");
        }
    }
}
