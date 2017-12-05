/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacteLib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DyaMetR
 */
public class Table<T> {
    protected String TABLE;
    private Connection connection;
    
    public Table(Connection connection) {
        setConnection(connection);
    }
    
    public int lastId() {
        int id = 0;
        String query = "SELECT MAX(ID) FROM "+TABLE;
        try {
            Statement statement = getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            
            result.next();
            id=result.getInt(1)+1;
            
            result.close();
            statement.close();
        }
        catch (SQLException e) {
            System.out.println("Couldn't retreive last ID from '"+TABLE+"'");
            e.printStackTrace();
        }
        return id;
    }
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
