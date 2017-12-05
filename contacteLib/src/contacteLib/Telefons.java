package contacteLib;

import java.sql.Connection;
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
        
    }
    
    @Override
    public void delete(Telefon telefon) {
        
    }
    
}
