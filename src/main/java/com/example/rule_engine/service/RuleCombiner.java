package com.example.rule_engine.service;



import org.springframework.stereotype.Service;

import com.example.rule_engine.Node;
@Service
public class RuleCombiner {

    public Node combineRules(Node rule1, Node rule2, String operator) {
        Node rootNode = new Node("operator", operator);
        rootNode.setLeft(rule1);
        rootNode.setRight(rule2);
        return rootNode;
    }
}
