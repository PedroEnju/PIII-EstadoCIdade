package br.com.pedroenju.contracts;

/**
 *
 * @author Pedro Enju
 */
public abstract class IExpression {
    
    public static final String AND_OPERATOR = " AND ";
    public static final String OR_OPERATOR = " OR ";
    
    public abstract String dump();
    
}
