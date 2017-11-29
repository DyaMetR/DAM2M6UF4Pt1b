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
 * @author DyaMetR
 */
public class Contacte implements Serializable, PropertyChangeListener {
    
    public static final String PROP_ID = "id";
    public static final String PROP_NOM = "nom";
    
    private int id;
    private String nom;
    
    private PropertyChangeSupport propertySupport;
    
    public Contacte() {
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public Contacte(int id, String nom) {
        propertySupport = new PropertyChangeSupport(this);
        setId(id);
        setNom(nom);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="G/S">
    public int getId() {
        return this.id;
    }
    
    public void setId(int value) {
        int oldValue = this.id;
        this.id = value;
        propertySupport.firePropertyChange(PROP_ID, oldValue, this.id);
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String value) {
        String oldValue = this.nom;
        this.nom = value;
        propertySupport.firePropertyChange(PROP_NOM, oldValue, this.nom);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    //</editor-fold>
    
}
