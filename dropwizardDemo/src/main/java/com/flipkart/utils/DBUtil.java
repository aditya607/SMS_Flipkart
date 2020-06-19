package com.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	
	private static Connection connection = null; // creating a connection object
	
	public static Connection getConnection() {  // will return a connection whenever this method is called......
		
        if (connection != null)
            return connection;
        else {
            try {
            	//Properties prop = new Properties();
               // InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("config.properties");
               // prop.load(inputStream);
                String driver = "com.mysql.cj.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/flipkartdb";
                String user ="root";
                String password = "rajababu";
                Class.forName(driver);   // registering with the driver
                connection = DriverManager.getConnection(url, user, password); // creating the connection
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }


}


