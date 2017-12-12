package contacteLib;

import static contacteLib.ContacteEntities.getConnection;
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
public class Contactes extends Table implements I_Table<Contacte> {
    
    public Contactes(Connection connection) {
        super(connection);
        TABLE = "contacte";
    }

    @Override
    public Contacte[] select() {
        Contacte[] contactes;
        ArrayList<Contacte> buffer = new ArrayList<Contacte>();
        
        try {
            Statement statement = getConnection().createStatement();
            ResultSet result = statement.executeQuery("select * from "+TABLE);
            while(result.next()) {
                Contacte c = new Contacte(result.getInt(1), result.getString(2));
                buffer.add(c);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Couldn't retreive the '"+TABLE+"' table.");
            e.printStackTrace();
        }
        
        contactes = new Contacte[buffer.size()];
        for (int i=0; i<contactes.length; i++) {
            contactes[i] = buffer.get(i);
        }
        
        return contactes;
    }
    
    @Override
    public void insert(Contacte contacte) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO "+ TABLE +" VALUES (?, ?)");
            int id;
            if (contacte.getId() == null) {
                id = lastId()+1;
            } else {
                id = contacte.getId();
            }
            statement.setInt(1, id);
            statement.setString(2, contacte.getNom());
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
