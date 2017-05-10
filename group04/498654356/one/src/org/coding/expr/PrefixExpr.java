package org.coding.expr;

import java.util.List;

import org.coding.one.Stack;

/**
 * 前序表达式
 */
public class PrefixExpr {
	private String expr;
	public PrefixExpr(String expr) {
		super();
		this.expr = expr;
	}

	public float evaluate() {
		TokenParse parse = new TokenParse();
		List<Token> tokens = parse.parse(this.expr);
		Stack numberStack = new Stack();
		
		//从后向前遍历，遇到一个操作符进行一次计算
		for (int index = tokens.size() - 1; index >= 0 ; index--) {
			Token token = tokens.get(index);
			if (token.isNumber()) {
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
		float num1 = (float) numberStack.pop();
		float num2 = (float) numberStack.pop();
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
