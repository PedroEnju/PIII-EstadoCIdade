package br.com.pedroenju.contracts;

import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Pedro Enju
 */
public class ICriterion extends IExpression {

    private Properties properties;
    private ArrayList<IExpression> expressions;
    private ArrayList<String> operators;

    public ICriterion() {
        this.properties = new Properties();
        this.expressions = new ArrayList();
        this.operators = new ArrayList();
    }

    @Override
    public String dump() {
        String query = "";

        for (int i = 0; i < expressions.size(); i++) {
            query += operators.get(i) + expressions.get(i).dump();
        }

        return "(" + query + ")";
    }

    public boolean hasExpression() {
        return !expressions.isEmpty();
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void addExpression(IExpression expression) {
        this.addExpression(expression, AND_OPERATOR);
    }

    public void addExpression(IExpression expression, String operator) {
        if (this.expressions.isEmpty()) {
            this.operators.add("");
        } else {
            this.operators.add(operator);
        }
        
        this.expressions.add(expression);
    }

}
