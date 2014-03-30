package lib.Connection;

import config.DbConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yuvielle
 * Date: 10.08.13
 * Time: 20:08
 * To change this template use File | Settings | File Templates.
 */
public class MyDbConnection {

    private DbConfig config = new DbConfig();
    private Connection conn;
    private static MyDbConnection dbConn;

    public static synchronized MyDbConnection init() throws Exception {
        if(dbConn == null){
            dbConn = new MyDbConnection();
        }
        return dbConn;
    }

    private MyDbConnection() throws Exception {
         String url = this.config.url;
         String dbName = this.config.dbName;
         String driver = this.config.driver;
         String userName = this.config.userName;
         String password = this.config.password;
         try {
              Class.forName(driver);

         } catch (ClassNotFoundException e) {
             throw new Exception("db driver not found " + e.getMessage());
         }
         try{
             this.conn = DriverManager.getConnection(url + dbName, userName, password);
         }
         catch (SQLException se) {
             throw se;
         }
        catch (Exception e){
            throw e;
        }
    }

    public Connection getConnection(){
        return this.conn;
    }

    public void closeConnection() throws SQLException {
        this.conn.close();
    }

    @Override
    protected void finalize ( ) throws Throwable {
        this.conn.close();
        super.finalize();
    }

}
