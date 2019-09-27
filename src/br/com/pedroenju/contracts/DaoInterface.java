package br.com.pedroenju.contracts;

import java.util.ArrayList;

/**
 *
 * @author Pedro Enju
 */
public interface DaoInterface {
    
    /**
     * Salva o objeto no Banco de Dados
     * @param o 
     */
    public void save(Object o);
    
    /**
     * Retorna uma lista de todos os dados no banco utilizando um ByCriterios
     * @param criterion
     * @return 
     */
    public ArrayList<Object> getAll(ICriterion criterion);
    
    /**
     * Retorna os dados de um ID
     * @param id
     * @return 
     */
    public Object getById(Long id);
    
    /**
     * Deleta do banco o objeto
     * @param o 
     */
    public void delete(Object o);
    
}
