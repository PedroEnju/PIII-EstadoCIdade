package br.com.pedroenju.dao;

import br.com.pedroenju.contracts.ICriterion;
import br.com.pedroenju.contracts.IFilter;
import br.com.pedroenju.contracts.ISQLInsert;
import br.com.pedroenju.contracts.ISQLInstruction;
import br.com.pedroenju.contracts.ISQLUpdate;
import br.com.pedroenju.contracts.TableModelInterface;
import br.com.pedroenju.model.Cidade;
import br.com.pedroenju.model.Cliente;
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
public class ClienteDao extends AbstractDao implements TableModelInterface {

    private CidadeDao daoCidade;
    
    public ClienteDao(Connection conn) {
        this.connect = conn;
        this.daoCidade = new CidadeDao(conn);
    }
    
    @Override
    protected String getTableName() {
        return "cliente";
    }

    @Override
    public void save(Object o) {
        Cliente client = (Cliente) o;

        ISQLInstruction sql = this.newInstruction(ISQLInstruction.QueryType.INSERT);
        if (client.getIdCliente()> 0) {
            sql = this.newInstruction(ISQLInstruction.QueryType.UPDATE);
        }

        if (sql instanceof ISQLUpdate) {
            ((ISQLUpdate) sql).addRowData("nomeCliente", client.getNomeCliente());
            ICriterion criterion = new ICriterion();
            criterion.addExpression(new IFilter("idCliente", "=", Long.toString(client.getIdCliente())));
            sql.setCriterion(criterion);
        } else if (sql instanceof ISQLInsert) {
            ((ISQLInsert) sql).getRowData().put("idCliente", null);
            ((ISQLInsert) sql).getRowData().put("nomeCliente", client.getNomeCliente());
            ((ISQLInsert) sql).getRowData().put("idCidade", Long.toString(client.getCidade().getIdCidade()));
        }

        try {
            Object response = this.executeSQL(sql);
            if (sql instanceof ISQLInsert && response instanceof Long) {
                client.setIdCliente((Long) response);
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
                ArrayList<Cliente> clients = new ArrayList();
                for (HashMap<String, Object> row : list) {
                    Cliente client = new Cliente();
                    client.setIdCliente((int) row.get("idCliente"));
                    client.setNomeCliente((String) row.get("nomeCliente"));
                    
                    if (((int) row.get("idCidade")) > 0) {
                        client.setCidade(
                                ((ArrayList<Cidade>) daoCidade.getById(
                                        ((Integer) row.get("idCidade")).longValue())).get(0)
                        );
                    }

                    clients.add(client);
                }

                return (ArrayList) clients;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Object getById(Long id) {
        ICriterion criterion = new ICriterion();
        criterion.addExpression(new IFilter("idCliente", "=", Long.toString(id)));
        return this.getAll(criterion);
    }

    @Override
    public void delete(Object o) {
        Cliente client = (Cliente) o;

        if (client.getIdCliente()> 0) {
            ISQLInstruction sql = this.newInstruction(ISQLInstruction.QueryType.DELETE);
            ICriterion criterion = new ICriterion();
            criterion.addExpression(new IFilter("idCliente", "=", Long.toString(client.getIdCliente())));
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

        TableColumn<Object, Object> colName = new TableColumn<>("Nome Cliente");
        colName.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        cols.add(colName);
        
        TableColumn<Object, Object> cidade = new TableColumn<>("Cidade");
        cidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        cols.add(cidade);

        return cols;
    }

    @Override
    public ArrayList<Object> search(String search) {
        ICriterion criterion = new ICriterion();
        criterion.addExpression(new IFilter("nomeCliente", "like", "%" + search + "%"));
        return this.getAll(criterion);
    }
    
}
