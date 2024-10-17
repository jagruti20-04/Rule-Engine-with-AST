package com.example.rule_engine.service;

import com.example.rule_engine.Node;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class RuleEvaluator {

    public boolean evaluateCondition(Node conditionNode, Map<String, Object> userData) {
        // Extract the condition value (e.g., "age > 30")
        String condition = (String) conditionNode.getValue();
        String[] parts = condition.split(" "); // Split the condition into parts

        // Check if the parts array has at least 3 elements
        if (parts.length < 3) {
            throw new IllegalArgumentException("Condition must have at least 3 parts: <attribute> <operator> <value>");
        }

        String userAttribute = parts[0]; // e.g., "age"
        String operator = parts[1]; // e.g., ">"
        String valueStr = parts[2]; // e.g., "30"

        // Get the attribute value from userData
        Object userValue = userData.get(userAttribute);
        System.out.println("Evaluating condition: " + conditionNode.getValue());
        System.out.println("User Data: " + userData);
        System.out.println("Attribute: " + userAttribute);
        System.out.println("Operator: " + operator);
        System.out.println("Value: " + valueStr);
        System.out.println("User's Attribute Value: " + userValue);
        
        // Evaluate condition based on the type of userValue
        if (userValue instanceof Number) {
            return evaluateNumericCondition(operator, ((Number) userValue).doubleValue(), Double.parseDouble(valueStr));
        } else if (userValue instanceof String) {
            // Handle string comparisons
            return userValue.equals(valueStr.replace("'", "").trim());
        }
       


        return false; // Default return if userValue type is unsupported
    }

    private boolean evaluateNumericCondition(String operator, double userValueNum, double conditionValue) {
        switch (operator) {
            case ">":
                return userValueNum > conditionValue;
            case "<":
                return userValueNum < conditionValue;
            case "=":
                return userValueNum == conditionValue;
            case ">=":
                return userValueNum >= conditionValue;
            case "<=":
                return userValueNum <= conditionValue;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }

    public boolean evaluateRule(Node ast, Map<String, Object> userData) {
        if (ast == null) {
            return false; // Handle null AST
        }

        // Evaluate based on the node type
        if ("operand".equals(ast.getType())) {
            return evaluateCondition(ast, userData);
        } else if ("operator".equals(ast.getType())) {
            // Recursively evaluate left and right nodes
            boolean leftResult = evaluateRule(ast.getLeft(), userData);
            boolean rightResult = evaluateRule(ast.getRight(), userData);

            // Combine results based on operator type
            return evaluateLogicalOperator(ast.getValue(), leftResult, rightResult);
        }
        

        return false; // Default return for unsupported node types
    }

    private boolean evaluateLogicalOperator(Object operator, boolean leftResult, boolean rightResult) {
        // Ensure the operator is cast to String for evaluation
        String operatorStr = (String) operator; 
        switch (operatorStr) {
            case "AND":
                return leftResult && rightResult;
            case "OR":
                return leftResult || rightResult;
            default:
                throw new IllegalArgumentException("Unsupported logical operator: " + operatorStr);
        }
    }
}
