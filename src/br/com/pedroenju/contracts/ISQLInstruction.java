package br.com.pedroenju.contracts;

/**
 *
 * @author Pedro Enju
 */
public abstract class ISQLInstruction {
    
    public enum QueryType {
        SELECT, INSERT, UPDATE, DELETE
    }
    
    protected StringBuilder sql;
    protected ICriterion criterion;
    protected String tableName;
    
    public abstract String getSQL();

    public ISQLInstruction(String tableName) {
        this.tableName = tableName;
        this.sql = new StringBuilder();
    }

    public ICriterion getCriterion() {
        return criterion;
    }

    public void setCriterion(ICriterion criterion) {
        this.criterion = criterion;
    }
    
}
