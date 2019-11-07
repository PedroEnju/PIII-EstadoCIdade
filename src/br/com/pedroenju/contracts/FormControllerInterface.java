package br.com.pedroenju.contracts;

import javafx.scene.Parent;

/**
 *
 * @author Pedro Enju
 */
public interface FormControllerInterface {
    
    /**
     * Retorna o layout do formulário
     * @return 
     */
    public Parent getLayout();
    
    /**
     * Atribui um model ao formulário
     * @param model 
     */
    public void setModel(Object model);
    
    /**
     * Retorna o model do formulário
     * @return 
     */
    public Object getModel();
    
    public void startForm();
}
