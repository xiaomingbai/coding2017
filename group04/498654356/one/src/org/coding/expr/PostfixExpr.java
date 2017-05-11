package org.coding.expr;

import java.util.List;

import org.coding.one.Stack;

/**
 * 后序表达式
 */
public class PostfixExpr {
	private String expr;
	
	public PostfixExpr(String expr) {
		super();
		this.expr = expr;
	}

	public float evaluate() {
		TokenParse parse = new TokenParse();
		List<Token> tokens = parse.parse(this.expr);
		Stack numberStack = new Stack();
		for (Token token : tokens) {
			if(token.isNumber()) {
				numberStack.push(Float.valueOf(token.toString()));
			} else if (token.isOperation()) {
				float result = doEvalute(numberStack, token);
				numberStack.push(result);
			}
		}
		return (float) numberStack.pop();
	}

	/** 操作数顺序*/
	private float doEvalute(Stack numberStack, Token token) {
		float num2 = (float) numberStack.pop();
		float num1 = (float) numberStack.pop();
		float result = 0;
		if("+".equals(token.toString())) {
			result = num1 + num2;
		} else if ("-".equals(token.toString())) {
			result = num1 - num2;
		} else if ("*".equals(token.toString())) {
			result = num1 * num2;
		} else if ("/".equals(token.toString())) {
			result = num1 / num2;
		} 
		return result;
	}
}
