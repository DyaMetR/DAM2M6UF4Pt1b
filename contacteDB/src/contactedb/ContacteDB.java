/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        
        if (db.isConnectionCreated()) {
            Contacte[] contactes = db.getContactes().select();
            for(int i=0; i<contactes.length; i++) {
                System.out.println(contactes[i].getNom());
            }
            db.getContactes().insert(new Contacte());
        }
    }
    
}
