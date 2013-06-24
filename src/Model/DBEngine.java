/*
 * This java class contains methods to create mysql jdbc connection and to add, update, retrieve 
 * and delete data from mysql table 
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
/**
 *
 * @author User
 */
public class DBEngine {
    //Define connection interface used to specifies connection with specific database like
    Connection con = null;
/*===============================================================================================
 * Method for mysql jdbc connection 
 */       
    public Connection createConnection()
    {
    try{//Try to create connection
        Class.forName("com.mysql.jdbc.Driver"); //To load the class and returns class instance and takes string type value (driver) after that matches class with given string.
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/novel_con","root","root");// Driver manager controls a set of JDBC drivers and getConnection method establishes a connection to specified database url. It takes three string types of arguments(URL, User name and Password)        
        return con;//To return the connection to the method where it was call from
        
    }
    //If the connection is not established then exception is thrown and print the error message.
    catch(Exception e)
            {
            e.printStackTrace();    
            con= null;
            return con;
            }
    }
/*===============================================================================================
 * Method for add, update and delete data from mysql database based on given query 
 */     
    	public boolean save(String sql) throws Exception {
	boolean rsult=false;
            try{    
            createConnection();
	int res = con.createStatement().executeUpdate(sql);
                       if(res >0)
                        {
                             rsult=true;
                        }

               return rsult;
        }catch(Exception e)
            {
            e.printStackTrace();    
            return rsult;   
            }
        }
/*===============================================================================================
 * Method for retrieve data from mysql database based on given query 
 */
	public ResultSet retrieve(String sql) throws Exception {
        ResultSet rs=null;
        try{      
		createConnection();
		rs=con.createStatement().executeQuery(sql);
                return rs;
        }catch(Exception e)
            {
            e.printStackTrace();    
            return rs;
            }
        }
}
