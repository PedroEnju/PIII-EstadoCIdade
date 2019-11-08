package br.com.pedroenju.contracts;

import javafx.beans.property.BooleanProperty;

/**
 *
 * @author lukas
 */
public interface LookupControllerInterface extends FormControllerInterface {
    
    public void setLookUp(LookupControllerInterface lkp);
    
    public BooleanProperty hasActiveLookup();
    
}
