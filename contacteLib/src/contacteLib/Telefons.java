package contacteLib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DyaMetR
 */
public class Telefons extends Table implements I_Table<Telefon> {
    
    public Telefons(Connection connection) {
        super(connection);
        TABLE = "telefon";
    }

    @Override
    public Telefon[] select() {
        Telefon[] telefons;
        ArrayList<Telefon> buffer = new ArrayList<Telefon>();
        
        try {
            Statement statement = getConnection().createStatement();
            ResultSet result = statement.executeQuery("select * from "+TABLE);
            while(result.next()) {
                Telefon t = new Telefon(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4));
                buffer.add(t);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Couldn't retreive the '"+TABLE+"' table.");
            e.printStackTrace();
        }
        
        telefons = new Telefon[buffer.size()];
        for (int i=0; i<telefons.length; i++) {
            telefons[i] = buffer.get(i);
        }
        
        return telefons;
    }
    
    @Override
    public void insert(Telefon telefon) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO "+ TABLE +" VALUES (?, ?, ?, ?)");
            int id;
            if (telefon.getId() == null) {
                id = lastId()+1;
            } else {
                id = telefon.getId();
            }
            statement.setInt(1, id);
            statement.setString(2, telefon.getTelefon());
            statement.setString(3, telefon.getDescripcio());
            statement.setInt(4, telefon.getIdcontacte());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Couldn't insert in the '"+TABLE+"' table.");
            e.printStackTrace();
        }
    }
    
    @Override
    public void delete(Integer id) {
        try {
            if (id == null) {
                System.err.println("Invalid ID");
            } else {
                PreparedStatement statement = getConnection().prepareStatement("DELETE FROM "+ TABLE +" WHERE id=?");
                statement.setInt(1, id);
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't insert in the '"+TABLE+"' table.");
            e.printStackTrace();
        }
    }
    
}
