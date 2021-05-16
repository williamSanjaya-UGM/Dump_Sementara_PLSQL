package firstConnect;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;

import javax.sql.PooledConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static final String jdbUri="jdbc:oracle:thin:@127.0.0.1:1521/XEPDB1";
    static final String user="williams";
    static final String pass="welcome123";

    public static void main(String[] args) {
        try{
            // Step 1 : Prepare datasource object
            OracleConnectionPoolDataSource ds = new OracleConnectionPoolDataSource();

            // Step 2 : set the required jdbc parameters to the datasource
            ds.setURL(jdbUri);
            ds.setUser(user);
            ds.setPassword(pass);

            // Step 3: Get pooled connection object
            PooledConnection pc = ds.getPooledConnection();

            // Step 4 : Get connection object from pooled connection
            Connection con =pc.getConnection();

            // Step 5: After getting connection object, prepare statement to perform operation on db
            Statement statement=con.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT * FROM FOOD_BEVERAGES_INFO");
//            while (rs.next()) {
//                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)
//                        +" "+rs.getDouble(4));
//            }

            CallableStatement cs = con.prepareCall("{call JAVA_TEST_CALL_CHECK_INFO_LOC(?,?,?)}");
            cs.setString(1,"BLOCK A");
            cs.setString(2,"13-MAY-2021");
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(3);
            List<InventoryInfo> infos =new ArrayList<>();
            while (rs.next()) {
//                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
                InventoryInfo ii = new InventoryInfo();
                ii.setId(rs.getInt(1));
                ii.setName(rs.getString(2));
                ii.setSectorName(rs.getString(3));
                infos.add(ii);
            }
            System.out.println(infos);
            // Connect to the database without pooling
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection con = DriverManager.getConnection(jdbUri,user,pass);
//            Statement statement=con.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT * FROM FOOD_BEVERAGES_INFO");
//            while (rs.next()) {
//                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)
//                        +" "+rs.getDouble(4));
//            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
