package br.com.pedroenju.dao;

import br.com.pedroenju.contracts.DaoInterface;
import br.com.pedroenju.contracts.ISQLDelete;
import br.com.pedroenju.contracts.ISQLInsert;
import br.com.pedroenju.contracts.ISQLInstruction;
import br.com.pedroenju.contracts.ISQLSelect;
import br.com.pedroenju.contracts.ISQLUpdate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Pedro Enju
 */
public abstract class AbstractDao implements DaoInterface {

    protected Connection connect;
    
    protected abstract String getTableName();
    
    /**
     * 
     * @param type
     * @return 
     */
    public ISQLInstruction newInstruction(ISQLInstruction.QueryType type) {
        switch(type) {
            case INSERT: return new ISQLInsert(this.getTableName());
            case UPDATE: return new ISQLUpdate(this.getTableName());
            case DELETE: return new ISQLDelete(this.getTableName());
            default: return new ISQLSelect(this.getTableName());
        }
    }
    
    /**
     * 
     * @param <T> Generics
     * @param iSQL
     * @return
     * @throws SQLException 
     */
    protected <T> T executeSQL(ISQLInstruction iSQL) throws SQLException {
        PreparedStatement ps = this.connect.prepareStatement(iSQL.getSQL(), Statement.RETURN_GENERATED_KEYS);

        if(iSQL instanceof ISQLSelect) {
            ResultSet rs = ps.executeQuery();
            return (T) this.resultSetToArrayList(rs);
        } 
        
        if(iSQL instanceof ISQLInsert) {
            int affectedRows = ps.executeUpdate();
            if(affectedRows > 0) {
                ResultSet rsKey = ps.getGeneratedKeys();
                if(rsKey.next()) {
                    return (T) rsKey.getObject(1, Long.class);
                }
            }
        }
        
        Integer affected = ps.executeUpdate();
        return (T) affected;
    }
    
    private List resultSetToArrayList(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int numCols = meta.getColumnCount();
        
        ArrayList list = new ArrayList();
        while(rs.next()) {
            HashMap row = new HashMap(numCols);
            
            for (int i = 1; i < numCols; i++) {
                row.put(meta.getColumnName(i), rs.getObject(i));
            }
            
            list.add(row);
        }
        
        return list;
    }
}
