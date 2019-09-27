package br.com.pedroenju.contracts;

import java.util.HashMap;

/**
 *
 * @author Pedro Enju
 */
public class ISQLInsert extends ISQLInstruction {

    private HashMap<String, String> rowData;
    
    public ISQLInsert(String tableName) {
        super(tableName);
        this.rowData = new HashMap();
    }

    @Override
    public String getSQL() {
        this.sql.append("INSERT INTO ");
        this.sql.append(this.tableName);
        this.sql.append(" (`");
        this.sql.append(String.join("`, `", this.rowData.keySet()));
        this.sql.append("`) VALUES ('");
        this.sql.append(String.join("', '", this.rowData.values()));
        this.sql.append("')");
        
        return this.sql.toString().replace("'NULL'", "NULL");
    }

    public HashMap<String, String> getRowData() {
        return rowData;
    }

    public void setRowData(HashMap<String, String> rowData) {
        this.rowData = rowData;
    }

    @Override
    public void setCriterion(ICriterion criterion) {
        throw new RuntimeException("Não é necessário critérios para INSERT");
    }
    
}
