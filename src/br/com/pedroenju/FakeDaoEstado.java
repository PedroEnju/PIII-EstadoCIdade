package br.com.pedroenju;

import br.com.pedroenju.contracts.TableModelInterface;
import br.com.pedroenju.model.Estado;
import java.util.ArrayList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Pedro Enju
 */
public class FakeDaoEstado implements TableModelInterface {

    @Override
    public ArrayList<TableColumn<Object, Object>> getCols() {
        ArrayList<TableColumn<Object, Object>> cols = new ArrayList();
        
        TableColumn<Object, Object> colName = new TableColumn<>("Nome Estado");
        colName.setCellValueFactory(new PropertyValueFactory<>("nomeEstado"));
        cols.add(colName);
        
        TableColumn<Object, Object> colUF = new TableColumn<>("UF");
        colUF.setCellValueFactory(new PropertyValueFactory<>("uf"));
        cols.add(colUF);
        
        return cols;
    }

    @Override
    public ArrayList<Object> search(String search) {
        ArrayList<Object> items = new ArrayList();
        
        Estado e = new Estado();
        e.setIdEstado(1);
        e.setNomeEstado("Paraná");
        e.setUf("PR");
        items.add(e);
        
        Estado ee = new Estado();
        ee.setIdEstado(2);
        ee.setNomeEstado("São Paulo");
        ee.setUf("SP");
        items.add(ee);
        
        Estado eee = new Estado();
        eee.setIdEstado(3);
        eee.setNomeEstado("Minas Gerais");
        eee.setUf("MG");
        items.add(eee);
        
        return items;
    }

}
