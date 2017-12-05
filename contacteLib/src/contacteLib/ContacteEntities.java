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
    private boolean createConnection;
    
    public ContacteEntities(String url, String user, String pwd, String driver) {
        this.url = url;
        this.user = user;
        this.pwd = pwd;
        this.driver = driver;
    }
    
    /**
     * Returns the current connection
     * @return 
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Sets the new connection
     * @param connection 
     */
    public static void setConnection(Connection connection) {
        ContacteEntities.connection = connection;
    }
    
    /**
     * Returns whether the connection was successful
     * @return 
     */
    public boolean isConnectionCreated() {
        return createConnection;
    }
    
    /**
     * Connects to the server
     */
    public void createConnection() {
        this.createConnection = false;
        try {
            Class.forName(driver);
            setConnection(DriverManager.getConnection(url, user, pwd));
            initTables();
            this.createConnection = true;
        } catch(Exception e) {
            System.out.println("Couldn't connect to the database.");
            e.printStackTrace();
        }
    }
    
    /**
     * Closes the current connection
     */
    public void closeConnection() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Tables">
    private Contactes contactes;
    private Telefons telefons;
    
    public void initTables() {
        setContactes(new Contactes(getConnection()));
        setTelefons(new Telefons(getConnection()));
    }
    
    public Contactes getContactes() {
        return contactes;
    }

    public void setContactes(Contactes contactes) {
        this.contactes = contactes;
    }

    public Telefons getTelefons() {
        return telefons;
    }

    public void setTelefons(Telefons telefons) {
        this.telefons = telefons;
    }
    // </editor-fold>
}
