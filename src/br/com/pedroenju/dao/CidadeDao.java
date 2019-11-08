package br.com.pedroenju.dao;

import br.com.pedroenju.contracts.ICriterion;
import br.com.pedroenju.contracts.IFilter;
import br.com.pedroenju.contracts.ISQLInsert;
import br.com.pedroenju.contracts.ISQLInstruction;
import br.com.pedroenju.contracts.ISQLUpdate;
import br.com.pedroenju.contracts.TableModelInterface;
import br.com.pedroenju.model.Cidade;
import br.com.pedroenju.model.Estado;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Pedro Enju
 */
public class CidadeDao extends AbstractDao implements TableModelInterface {

    private EstadoDao daoEstado;

    public CidadeDao(Connection conn) {
        this.connect = conn;
        this.daoEstado = new EstadoDao(conn);
    }

    @Override
    protected String getTableName() {
        return "cidade";
    }

    @Override
    public void save(Object o) {
        Cidade city = (Cidade) o;

        ISQLInstruction sql = this.newInstruction(ISQLInstruction.QueryType.INSERT);
        if (city.getIdCidade() > 0) {
            sql = this.newInstruction(ISQLInstruction.QueryType.UPDATE);
        }

        if (sql instanceof ISQLUpdate) {
            ((ISQLUpdate) sql).addRowData("nomeCidade", city.getNomeCidade());
            ICriterion criterion = new ICriterion();
            criterion.addExpression(new IFilter("idCidade", "=", Long.toString(city.getIdCidade())));
            sql.setCriterion(criterion);
        } else if (sql instanceof ISQLInsert) {
            ((ISQLInsert) sql).getRowData().put("idCidade", null);
            ((ISQLInsert) sql).getRowData().put("nomeCidade", city.getNomeCidade());
            ((ISQLInsert) sql).getRowData().put("idEstado", Long.toString(city.getEstado().getIdEstado()));
        }

        try {
            Object response = this.executeSQL(sql);
            if (sql instanceof ISQLInsert && response instanceof Long) {
                city.setIdCidade((Long) response);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Object> getAll(ICriterion criterion) {
        ISQLInstruction sql = this.newInstruction(ISQLInstruction.QueryType.SELECT);
        sql.setCriterion(criterion);
        try {
            ArrayList<HashMap<String, Object>> list = this.executeSQL(sql);
            if (!list.isEmpty()) {
                ArrayList<Cidade> cities = new ArrayList();
                for (HashMap<String, Object> row : list) {
                    Cidade city = new Cidade();
                    city.setIdCidade((int) row.get("idCidade"));
                    city.setNomeCidade((String) row.get("nomeCidade"));

                    if (((int) row.get("idEstado")) > 0) {
                        city.setEstado(
                                ((ArrayList<Estado>) daoEstado.getById(
                                        ((Integer) row.get("idEstado")).longValue())).get(0)
                        );
                    }

                    cities.add(city);
                }

                return (ArrayList) cities;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Object getById(Long id) {
        ICriterion criterion = new ICriterion();
        criterion.addExpression(new IFilter("idCidade", "=", Long.toString(id)));
        return this.getAll(criterion);
    }

    @Override
    public void delete(Object o) {
        Cidade city = (Cidade) o;

        if (city.getIdCidade() > 0) {
            ISQLInstruction sql = this.newInstruction(ISQLInstruction.QueryType.DELETE);
            ICriterion criterion = new ICriterion();
            criterion.addExpression(new IFilter("idCidade", "=", Long.toString(city.getIdCidade())));
            sql.setCriterion(criterion);

            try {
                this.executeSQL(sql);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public ArrayList<TableColumn<Object, Object>> getCols() {
        ArrayList<TableColumn<Object, Object>> cols = new ArrayList();

        TableColumn<Object, Object> colName = new TableColumn<>("Nome Cidade");
        colName.setCellValueFactory(new PropertyValueFactory<>("nomeCidade"));
        cols.add(colName);
        
        TableColumn<Object, Object> estadoUF = new TableColumn<>("Estado");
        estadoUF.setCellValueFactory(new PropertyValueFactory<>("estado"));
        cols.add(estadoUF);

        return cols;
    }

    @Override
    public ArrayList<Object> search(String search) {
        ICriterion criterion = new ICriterion();
        criterion.addExpression(new IFilter("nomeCidade", "like", "%" + search + "%"));
        return this.getAll(criterion);
    }

}
