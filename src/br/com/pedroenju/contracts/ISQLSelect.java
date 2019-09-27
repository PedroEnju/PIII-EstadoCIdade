package br.com.pedroenju.contracts;

import java.util.ArrayList;

/**
 *
 * @author Pedro Enju
 */
public class ISQLSelect extends ISQLInstruction {
    
    private ArrayList<String> cols;
    
    public ISQLSelect(String tableName) {
        super(tableName);
        this.cols = new ArrayList<>();
    }

    @Override
    public String getSQL() {
        this.sql.append("SELECT ");
        
        if (this.cols.size() > 0) {
            this.sql.append(String.join(", ", cols));
        } else {
            this.sql.append("*");
        }
        
        this.sql.append(" FROM ");
        this.sql.append(this.tableName);
        
        if (this.criterion != null && this.criterion.hasExpression()) {
            this.sql.append(" WHERE ");
            this.sql.append(this.criterion.dump());
        }
        
        if (this.criterion != null) {
            if (this.criterion.getProperties().containsKey("order")) {
                this.sql.append(" ORDER BY ");
                this.sql.append(this.criterion.getProperties().get("order"));
            }
            if (this.criterion.getProperties().containsKey("limit")) {
                this.sql.append(" limit ");
                this.sql.append(this.criterion.getProperties().get("limit"));
            }
            if (this.criterion.getProperties().containsKey("offset")) {
                this.sql.append(" offset ");
                this.sql.append(this.criterion.getProperties().get("offset"));
            }
        }
        
        return this.sql.toString();
    }

    public ArrayList<String> getCols() {
        return cols;
    }

    public void setCols(ArrayList<String> cols) {
        this.cols = cols;
    }

}
