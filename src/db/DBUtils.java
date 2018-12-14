package db;

import java.sql.*;

public class DBUtils {
    private static Connection con;
    private static final String drivername="com.mysql.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost:3306/netdisk?characterEncoding=utf-8";

    public static synchronized Connection getCon() throws Exception {
        try {
        	Class.forName(drivername); 
        	String username="root";
        	String password="502717";
        	con=DriverManager.getConnection(url,username,password);
            return con;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }
}