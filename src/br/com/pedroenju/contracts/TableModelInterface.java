package br.com.pedroenju.contracts;

import java.util.ArrayList;
import javafx.scene.control.TableColumn;

/**
 *
 * @author Pedro Enju
 */
public interface TableModelInterface {
    
    /**
     * Retorna uma lista contendo as colunas que deseja ser exibido na TABLE
     * @return 
     */
    public ArrayList<TableColumn<Object, Object>> getCols();
    
    /**
     * Retorna uma lista de objetos utilizando um Search
     * @param search
     * @return 
     */
    public ArrayList<Object> search(String search);
    
}
