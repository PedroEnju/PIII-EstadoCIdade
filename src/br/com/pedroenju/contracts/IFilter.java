package br.com.pedroenju.contracts;

/**
 *
 * @author Pedro Enju
 */
public class IFilter extends IExpression {
    
    private final String column;
    private final String operator;
    private final String value;

    public IFilter(String column, String operator, String value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }
    
    @Override
    public String dump() {
        return "`" + column + "` " + operator + " '" + value + "'";
    }
}
