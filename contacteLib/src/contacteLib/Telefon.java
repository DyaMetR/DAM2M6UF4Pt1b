/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacteLib;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author Victor
 */
public class Telefon implements Serializable, PropertyChangeListener {
    
    private PropertyChangeSupport propertySupport;
    
    public static final String PROP_ID = "id";
    public static final String PROP_TELEFON = "telefon";
    public static final String PROP_DESCRIPCIO = "descripcio";
    public static final String PROP_IDCONTACTE = "idcontacte";
    
    private int id;
    private String telefon;
    private String descripcio;
    private int idcontacte;
    
    public Telefon() {
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public Telefon (int id, String telefon, String descripcio, int idcontacte){
        propertySupport = new PropertyChangeSupport(this);
        this.id = id;
        this.telefon = telefon;
        this.descripcio = descripcio;
        this.idcontacte = idcontacte;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="G/S">
    public PropertyChangeSupport getPropertySupport() {
        return propertySupport;
    }

    public void setPropertySupport(PropertyChangeSupport propertySupport) {
        this.propertySupport = propertySupport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        int oldValue = this.id;
        this.id = id;
        propertySupport.firePropertyChange(PROP_ID, oldValue, this.id);
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        String oldValue = this.telefon;
        this.telefon = telefon;
        propertySupport.firePropertyChange(PROP_TELEFON, oldValue, this.telefon);
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        String oldValue = this.descripcio;
        this.descripcio = descripcio;
        propertySupport.firePropertyChange(PROP_DESCRIPCIO, oldValue, this.descripcio);
    }

    public int getIdcontacte() {
        return idcontacte;
    }

    public void setIdcontacte(int idcontacte) {
        int oldValue = this.idcontacte;
        this.idcontacte = idcontacte;
        propertySupport.firePropertyChange(PROP_IDCONTACTE, oldValue, this.idcontacte);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    // </editor-fold>
}
