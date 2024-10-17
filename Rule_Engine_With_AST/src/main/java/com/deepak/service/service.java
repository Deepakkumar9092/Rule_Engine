package com.deepak.service;

import java.util.Map;

import ch.qos.logback.core.pattern.parser.Node;

public class service {

	public boolean evaluateRule(Node node, Map<String, Object> userData) {
	    if (node.type.equals("operand")) {
	        return evaluateCondition(node.condition, userData); // Evaluate condition
	    } else if (node.type.equals("operator")) {
	        boolean leftResult = evaluateRule(node.left, userData);
	        boolean rightResult = evaluateRule(node.right, userData);
	        return node.operator.equals("AND") ? leftResult && rightResult : leftResult || rightResult;
	    }
	    return false;
	}

}
