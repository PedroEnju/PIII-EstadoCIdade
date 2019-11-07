package br.com.pedroenju.contracts;

import java.util.HashMap;

/**
 *
 * @author Pedro Enju
 */
public class ISQLUpdate extends ISQLInstruction {

    private HashMap rowData;

    public ISQLUpdate(String tableName) {
        super(tableName);
        this.rowData = new HashMap<String, String>();
    }

    @Override
    public String getSQL() {
        if (this.criterion == null || !this.criterion.hasExpression()) {
            throw new RuntimeException("Não é permitido UPDATE sem WHERE");
        }
        
        if (this.rowData.isEmpty()) {
            throw new RuntimeException("Não foi passado dados!");
        }
        
        this.sql.append("UPDATE ");
        this.sql.append(this.tableName);
        this.sql.append(" SET ");
        for (Object k : rowData.keySet()) {
            this.sql.append("`");
            this.sql.append(k.toString());
            this.sql.append("`");
            this.sql.append(" = ");
            this.sql.append("'");
            this.sql.append(rowData.get(k).toString());
            this.sql.append("', ");
        }
        this.sql.delete(this.sql.lastIndexOf(","), this.sql.length() - 1);
        this.sql.append("WHERE ");
        this.sql.append(this.criterion.dump());
        
        String str = this.sql.toString().replace("'NULL'", "NULL");
        return str.replace("'null'", "NULL");
    }

    public HashMap getRowData() {
        return rowData;
    }

    public void setRowData(HashMap rowData) {
        this.rowData = rowData;
    }

    public ISQLUpdate addRowData(String col, Object val) {
        if (val == null) {
            this.rowData.put(col, "NULL");
        } else {
            this.rowData.put(col, val.toString());
        }

        return this;
    }
}
