package com.example.rule_engine;
import lombok.Data;

@Data
public class Node {
    private String type; // "operator" (AND/OR) or "operand" (conditions)
    private Node left;   // Left child
    private Node right;  // Right child
    private String value; // Value for operand nodes (like age, salary, etc.)

    // Constructors
    public Node(String type, Node left, Node right,String value) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.value = value;
    }
    public Node() {
    }

    public Node(String type, String value) {
        this.type = type;
        this.value = value;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

