package com.epam.rd.autotasks.arithmeticexpressions;

import java.util.StringJoiner;

public class Expressions {

    public static Variable var(String name, int value) {
        return new Variable(name, value);
    }

    private static String joinExpressions(String sign, Expression... expressions) {
        StringJoiner stringJoiner = new StringJoiner(" " + sign + " ", "(", ")");
        for(Expression expression : expressions) {
            stringJoiner.add(expression.toExpressionString());
        }
        return stringJoiner.toString();
    }

    public static Expression val(int value) {
        return new Expression() {
            @Override
            public int evaluate() {
                return value;
            }

            @Override
            public String toExpressionString() {
                return value > 0 ? value + "" : "("+ value + ")";
            }
        };
    }

    public static Expression sum(Expression... members){
        return new Expression() {
            @Override
            public int evaluate() {
                int sum = 0;
                for(Expression expression : members) {
                    sum += expression.evaluate();
                }
                return sum;
            }

            @Override
            public String toExpressionString() {
                return joinExpressions("+", members);
            }
        };
    }

    public static Expression product(Expression... members){
        return new Expression() {
            @Override
            public int evaluate() {
                int sum = 1;
                for(Expression expression : members) {
                    sum *= expression.evaluate();
                }
                return sum;
            }

            @Override
            public String toExpressionString() {
                return joinExpressions("*", members);
            }
        };
    }

    public static Expression difference(Expression minuend, Expression subtrahend){
        return new Expression() {
            @Override
            public int evaluate() {
                return minuend.evaluate() - subtrahend.evaluate();
            }

            @Override
            public String toExpressionString() {
                return joinExpressions("-", minuend, subtrahend);
            }
        };
    }

    public static Expression fraction(Expression dividend, Expression divisor){
        return new Expression() {
            @Override
            public int evaluate() {
                return dividend.evaluate() / divisor.evaluate();
            }

            @Override
            public String toExpressionString() {
                return joinExpressions("/", dividend, divisor);
            }
        };
    }

}
