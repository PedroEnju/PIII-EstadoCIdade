package br.com.pedroenju;

import br.com.pedroenju.contracts.DaoInterface;
import br.com.pedroenju.contracts.ICriterion;
import br.com.pedroenju.contracts.TableModelInterface;
import br.com.pedroenju.model.Cidade;
import java.util.ArrayList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Pedro Enju
 */
public class FakeDaoCidade implements TableModelInterface, DaoInterface {

    @Override
    public ArrayList<TableColumn<Object, Object>> getCols() {
        ArrayList<TableColumn<Object, Object>> cols = new ArrayList();
        
        TableColumn<Object, Object> colName = new TableColumn<>("Nome Cidade");
        colName.setCellValueFactory(new PropertyValueFactory<>("nomeCidade"));
        cols.add(colName);
        
        return cols;
    }

    @Override
    public ArrayList<Object> search(String search) {
        ArrayList<Object> items = new ArrayList();
        
        Cidade c = new Cidade();
        c.setIdCidade(1);
        c.setNomeCidade("Terra Roxa");
        items.add(c);   
        
        Cidade cc = new Cidade();
        cc.setIdCidade(2);
        cc.setNomeCidade("Curitiba");
        items.add(cc);
        
        Cidade ccc = new Cidade();
        ccc.setIdCidade(3);
        ccc.setNomeCidade("Maringá");
        items.add(ccc);
        
        return items;
    }

    @Override
    public void save(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Object> getAll(ICriterion criterion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
