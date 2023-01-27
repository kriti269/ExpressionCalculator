package src;


import src.exceptions.InvalidExpressionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Enum to store types of operations that can be
 * performed in the expression.
 */
enum Operations {
    MULTIPLY,
    ADD
}

/**
 * Calculator class that stores expression as a property.
 * It has some reusable static methods (add, multiply) and
 * a calculate method to evaluate the expressions.
 */
public class Calculator {
    private String expression;

    public Calculator(String expression) {
        this.expression = expression;
    }

    public static int add(Integer... values) {
        int sum = 0;
        for(int i : values) {
            sum+= i;
        }
        return sum;
    }

    public static int multiply(Integer... values) {
        int product = 1;
        for(int i : values) {
            product*= i;
        }
        return product;
    }

    /**
     * @return value of calculated expression string
     * @throws InvalidExpressionException if expression is empty or does not match
     * the language.
     */
    public int calculate() throws InvalidExpressionException{
        Stack<String> expressionStack = new Stack<>();
        if(expression==null || expression.isEmpty()) {
            throw new InvalidExpressionException("Empty expression!");
        }
        expression = expression.replaceAll("\\(", " ( ");
        expression = expression.replaceAll("\\)", " ) ");

        List<String> expressionList = Arrays.asList(expression.split("\\s"));
        expressionList = expressionList.stream().filter(x -> x != null && !x.isEmpty())
                .collect(Collectors.toList());

        for(int i=expressionList.size()-1; i>=0; i--) {
            String currentExp = expressionList.get(i);

            if(currentExp.equals("(")) continue;
            else if(currentExp.equals(")") || currentExp.matches("[0-9]+"))
                expressionStack.push(currentExp);
            else if(Arrays.stream(Operations.values()).filter(x -> x.name()
                    .equalsIgnoreCase(currentExp)).count()!=0) {

                List<Integer> values = new ArrayList<>();

                int result;
                while (!expressionStack.peek().equals(")")) {
                    values.add(Integer.parseInt(expressionStack.pop()));
                }
                expressionStack.pop();

                if(currentExp.equalsIgnoreCase(Operations.ADD.name()))
                    result = add(values.toArray(new Integer[0]));

                else result = multiply(values.toArray(new Integer[0]));

                expressionStack.push(String.valueOf(result));
            }
            else throw new InvalidExpressionException("Invalid sub-expression found in the expression: "
                        + currentExp);
        }
        return Integer.parseInt(expressionStack.pop());
    }
}
