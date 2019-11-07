package br.com.pedroenju.contracts;

import br.com.pedroenju.controller.PesquisaController;
import javafx.scene.Parent;

/**
 *
 * @author Pedro Enju
 */
public interface CrudInterface {
    
    /**
     * Configurar tela do CRUD
     * @param dao
     * @param pc
     * @param fc
     * @param titulo 
     */
    public void config(DaoInterface dao, PesquisaController pc, FormControllerInterface fc, String titulo);
    
    /**
     * Retorna o layout do CRUD
     * @return 
     */
    public Parent getLayout();
    
}
