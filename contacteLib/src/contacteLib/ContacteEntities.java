package contacteLib;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DyaMetR
 */
public class ContacteEntities {
    
    // <editor-fold defaultstate="collapsed" desc="DB Connection">
    private static Connection connection;
    private String url;
    private String user;
    private String pwd;
    private String driver;
    private String contacteTable;
    private String telefonTable;
    private boolean createConnection;
    
    public ContacteEntities(String url, String user, String pwd, String driver, 
                 String contacteTable, String telefonTable) {
        this.url = url;
        this.user = user;
        this.pwd = pwd;
        this.driver = driver;
        this.contacteTable = contacteTable;
        this.telefonTable = telefonTable;
    }
    
    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        ContacteEntities.connection = connection;
    }
    
    public boolean isConnectionCreated() {
        return createConnection;
    }
    
    public void createConnection() {
        this.createConnection = false;
        try {
            Class.forName(driver);
            setConnection(DriverManager.getConnection(url, user, pwd));
            this.createConnection = true;
        } catch(Exception e) {
            System.out.println("Couldn't connect to the database.");
            e.printStackTrace();
        }
    }
    
    public void closeConnection() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Data retreiving">
    // Contacte Table
    public ArrayList<Contacte> contacteQuery(String query) {
        ArrayList<Contacte> list = new ArrayList<Contacte>();
        try {
            Statement instruc = getConnection().createStatement();
            ResultSet result = instruc.executeQuery(query);
            while (result.next()) {
                Contacte c = new Contacte(result.getInt(1), result.getString(2));
                list.add(c);
            }
            result.close();
            instruc.close();
        } catch (SQLException e) {
            System.out.println("Couldn't retreive the 'contactes' table");
            e.printStackTrace();
        }
        return list;
    }
    
    // Telefon Table
    public ArrayList<Telefon> telefonQuery(String query) {
        ArrayList<Telefon> list = new ArrayList<Telefon>();
        try {
            Statement instruc = getConnection().createStatement();
            ResultSet result = instruc.executeQuery(query);
            while (result.next()) {
                Telefon t = new Telefon(result.getInt(1), result.getString(2), 
                                        result.getString(3), result.getInt(4));
                list.add(t);
            }
            result.close();
            instruc.close();
        } catch (SQLException e) {
            System.out.println("Couldn't retreive the 'contactes' table");
            e.printStackTrace();
        }
        return list;
    }
    
    // Obtain last ID of a table
    public int getLastID(String table) {
        int id = 0;
        String consulta="SELECT MAX(ID) FROM "+table;
        try {
            Statement instruc = getConnection().createStatement();
            ResultSet result = instruc.executeQuery(consulta);
            result.next();
            id = result.getInt(1)+1;
            result.close();
            instruc.close();
        }
        catch (SQLException e) {
            System.out.println("Couldn't retreive the last id of table '"+table+"'");
            e.printStackTrace();
        }
        return id;
    }
    // </editor-fold>
}
