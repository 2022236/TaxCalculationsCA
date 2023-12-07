package taxcalculationsca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import ioutils.IOUtils;

public class DatabaseWriter extends Database {
    public boolean addUserDatabase(String userName, String role, String email, String password) throws SQLException {
        IOUtils scan = new IOUtils ();
        try (
            Connection conn = DriverManager.getConnection(db_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();  
        ) {
            String sql = String.format("INSERT INTO " + tableName + " (userName, role, email, password) VALUES ('%s', '%s', '%s', '%s');",
     userName, role, email, password);

            stmt.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }    
    }
    
}
    