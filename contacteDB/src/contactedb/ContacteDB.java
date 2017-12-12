package contactedb;
import contacteLib.*;
import java.util.ArrayList;

/**
 *
 * @author DyaMetR
 */
public class ContacteDB {
    private static final String URL = "jdbc:mysql://localhost/contactes";
    private static final String USER = "root";
    private static final String PWD = "d4t4b4se!"; // idgaf si me robais la contra :v
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ContacteEntities db = new ContacteEntities(URL, USER, PWD, DRIVER);
        db.createConnection();
        
        String table = getArg(args, 0);
        String command = getArg(args, 1);
        int id = Integer.parseInt(getArg(args, 2));
        
        switch(table) {
            case "contacte":
                if (db.isConnectionCreated()) {
                    Contacte[] contactes = db.getContactes().select();
                    for(int i=0; i<contactes.length; i++) {
                        System.out.println(contactes[i].getId()+" "+contactes[i].getNom());
                    }
                }
                break;
            case "telefon":
                if (db.isConnectionCreated()) {
                    Telefon[] telefons = db.getTelefons().select();
                    for(int i=0; i<telefons.length; i++) {
                        System.out.println(telefons[i].getId()+" "+telefons[i].getTelefon()+" "+telefons[i].getDescripcio()+" "+telefons[i].getIdcontacte());
                    }
                }
                break;
        }
    }
    
    public static String getArg(String[] args, int i) {
        try {
            return args[i];
        } catch (Exception e) {}
        return "";
    }
    
}
