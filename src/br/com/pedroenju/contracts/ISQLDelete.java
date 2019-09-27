package br.com.pedroenju.contracts;

/**
 *
 * @author Pedro Enju
 */
public class ISQLDelete extends ISQLInstruction {

    public ISQLDelete(String tableName) {
        super(tableName);
    }

    @Override
    public String getSQL() {
        if(this.criterion == null || !this.criterion.hasExpression()) {
            throw new RuntimeException("Não é permitido DELETE sem WHERE");
        }
        
        this.sql.append("DELETE FROM ");
        this.sql.append(this.tableName);
        this.sql.append(" WHERE ");
        this.sql.append(this.criterion.dump());
        
        return this.sql.toString();
    }
    
}
