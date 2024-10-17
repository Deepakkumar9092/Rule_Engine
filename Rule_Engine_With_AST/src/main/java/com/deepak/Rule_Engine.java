package com.deepak;

import ch.qos.logback.core.pattern.parser.Node;

public class Rule_Engine {
	
	
	    String type; // "operator" or "operand"
	    Node left;   // left child node
	    Node right;  // right child node
	    String operator; // AND/OR for operator nodes
	    String condition; // for operand nodes (e.g., age > 30)
	    Object value;  // value for operand nodes (e.g., number for comparisons)
	    
	    // Constructor for operand node
	    public void Node(String type, String condition, Object value) {
	        this.type = type;
	        this.condition = condition;
	        this.value = value;
	    }

	    // Constructor for operator node
	    public void Node(String type, String operator, Node left, Node right) {
	        this.type = type;
	        this.operator = operator;
	        this.left = left;
	        this.right = right;
	    }
	}



